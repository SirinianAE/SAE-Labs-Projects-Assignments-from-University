/*
* Author
* Name:	Sirinian Aram Emmanouil
*/

/* server.c

   Sample code of
   Assignment L1: Simple multi-threaded key-value server
   for the course MYY601 Operating Systems, University of Ioannina

   (c) S. Anastasiadis, G. Kappes 2016

*/


#include <stdbool.h>
#include <stdlib.h>
#include <sys/time.h>
#include <stdio.h>
#include <pthread.h>
#include <signal.h>
#include <sys/stat.h>
#include "utils.h"
#include "kissdb.h"

#define NUMBER_OF_THREADS	4
#define QUEUE_SIZE		8
#define MY_PORT                 6767
#define BUF_SIZE                1160
#define KEY_SIZE                 128
#define HASH_SIZE               1024
#define VALUE_SIZE              1024
#define MAX_PENDING_CONNECTIONS   10

struct fdStartTimeCouple_s{
	int socket_fd;
	struct timeval tv;
};
typedef struct fdStartTimeCouple_s fdStartTimeCouple_t;

struct myQueue_s{
	fdStartTimeCouple_t cells[QUEUE_SIZE];
	int head;
	int tail;
	int item_count;
};
typedef struct myQueue_s myQueue_t;
myQueue_t queue;

// Definition of the operation type.
typedef enum operation {
  PUT,
  GET
} Operation;

// Definition of the request.
typedef struct request {
  Operation operation;
  char key[KEY_SIZE];
  char value[VALUE_SIZE];
} Request;

// Definition of the database.
KISSDB *db = NULL;

double sum_of_total_waiting_time;
double sum_of_total_service_time;
double total_waiting_time;
double total_service_time;
long completed_requests;
bool non_empty_queue;
bool non_full_queue;
bool kill_all_consumer_threads;
int reader_count;
int writer_count;
pthread_cond_t empty_queue_condition = PTHREAD_COND_INITIALIZER;
pthread_cond_t full_queue_condition = PTHREAD_COND_INITIALIZER;
pthread_cond_t reader_count_based_condition = PTHREAD_COND_INITIALIZER;
pthread_cond_t writer_count_based_condition = PTHREAD_COND_INITIALIZER;
pthread_mutex_t queue_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t empty_queue_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t full_queue_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t completed_requests_and_service_time_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t reader_count_based_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t writer_count_based_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t database_writer_mutex = PTHREAD_MUTEX_INITIALIZER;

void *consumer_thread_function();
void producer_thread_function(int new_fd);
void enqueue(fdStartTimeCouple_t fd_start_time_couple);
void update_queue_management_variables(void);
fdStartTimeCouple_t dequeue(void);
bool is_empty(void);
bool is_full(void);
void initialize_queue(void);
void initialize_global_variables(void);
double compute_total_waiting_time(fdStartTimeCouple_t dequeued_item);
double compute_total_service_time(struct timeval start_of_service_time);
void sigint_handler(int sig);
void sigstp_handler(int sig);

/**
 * @name parse_request - Parses a received message and generates a new request.
 * @param buffer: A pointer to the received message.
 *
 * @return Initialized request on Success. NULL on Error.
 */
Request *parse_request(char *buffer) {
  char *token = NULL;
  Request *req = NULL;

  // Check arguments.
  if (!buffer)
    return NULL;

  // Prepare the request.
  req = (Request *) malloc(sizeof(Request));
  memset(req->key, 0, KEY_SIZE);
  memset(req->value, 0, VALUE_SIZE);

  // Extract the operation type.
  token = strtok(buffer, ":");
  if (!strcmp(token, "PUT")) {
    req->operation = PUT;
  } else if (!strcmp(token, "GET")) {
    req->operation = GET;
  } else {
    free(req);
    return NULL;
  }

  // Extract the key.
  token = strtok(NULL, ":");
  if (token) {
    strncpy(req->key, token, KEY_SIZE);
  } else {
    free(req);
    return NULL;
  }

  // Extract the value.
  token = strtok(NULL, ":");
  if (token) {
    strncpy(req->value, token, VALUE_SIZE);
  } else if (req->operation == PUT) {
    free(req);
    return NULL;
  }
  return req;
}

/*
 * @name process_request - Process a client request.
 * @param socket_fd: The accept descriptor.
 *
 * @return
 */
void process_request(const int socket_fd) {
    char response_str[BUF_SIZE], request_str[BUF_SIZE];
    int numbytes = 0;
    Request *request = NULL;

    // Clean buffers.
    memset(response_str, 0, BUF_SIZE);
    memset(request_str, 0, BUF_SIZE);

    // receive message.
    numbytes = read_str_from_socket(socket_fd, request_str, BUF_SIZE);

    // parse the request.
    if (numbytes) {
      request = parse_request(request_str);
      if (request) {
        switch (request->operation) {
          case GET:
            // Read the given key from the database.
            pthread_mutex_lock(&writer_count_based_mutex);
            while(writer_count > 0){
              pthread_cond_wait(&writer_count_based_condition, &writer_count_based_mutex);
            }
            reader_count++;
            pthread_mutex_unlock(&writer_count_based_mutex);


            if (KISSDB_get(db, request->key, request->value))
              sprintf(response_str, "GET ERROR\n");
            else
              sprintf(response_str, "GET OK: %s\n", request->value);

            pthread_mutex_lock(&reader_count_based_mutex);
            reader_count--;
            if(reader_count == 0){
              pthread_cond_signal(&reader_count_based_condition);
            }
            pthread_mutex_unlock(&reader_count_based_mutex);
            break;
          case PUT:
            // Write the given key/value pair to the database.
            pthread_mutex_lock(&database_writer_mutex);
            pthread_mutex_lock(&reader_count_based_mutex);
            while(reader_count > 0){
              pthread_cond_wait(&reader_count_based_condition ,&reader_count_based_mutex);
            }
            writer_count++;
            pthread_mutex_unlock(&reader_count_based_mutex);

            if (KISSDB_put(db, request->key, request->value))
              sprintf(response_str, "PUT ERROR\n");
            else
              sprintf(response_str, "PUT OK\n");

            pthread_mutex_lock(&writer_count_based_mutex);
            writer_count--;
            if(writer_count == 0){
              pthread_cond_broadcast(&writer_count_based_condition);
            }
            pthread_mutex_unlock(&writer_count_based_mutex);
            pthread_mutex_unlock(&database_writer_mutex);
            break;
          default:
            // Unsupported operation.
            sprintf(response_str, "UNKOWN OPERATION\n");
        }
        // Reply to the client.
        write_str_to_socket(socket_fd, response_str, strlen(response_str));
        if (request)
          free(request);
        request = NULL;
        return;
      }
    }
    // Send an Error reply to the client.
    sprintf(response_str, "FORMAT ERROR\n");
    write_str_to_socket(socket_fd, response_str, strlen(response_str));
}

/*
 * @name main - The main routine.
 *
 * @return 0 on success, 1 on error.
 */
int main() {

  int socket_fd,              // listen on this socket for new connections
      new_fd;                 // use this socket to service a new connection
  socklen_t clen;
  struct sockaddr_in server_addr,  // my address information
                     client_addr;  // connector's address information

  initialize_queue();
  initialize_global_variables();

  // create socket
  if ((socket_fd = socket(AF_INET, SOCK_STREAM, 0)) == -1)
    ERROR("socket()");

  // Ignore the SIGPIPE signal in order to not crash when a
  // client closes the connection unexpectedly.
  signal(SIGPIPE, SIG_IGN);
  signal(SIGINT, sigint_handler);
  signal(SIGTSTP, sigstp_handler);

  // create socket adress of server (type, IP-adress and port number)
  bzero(&server_addr, sizeof(server_addr));
  server_addr.sin_family = AF_INET;
  server_addr.sin_addr.s_addr = htonl(INADDR_ANY);    // any local interface
  server_addr.sin_port = htons(MY_PORT);

  // bind socket to address
  if (bind(socket_fd, (struct sockaddr *) &server_addr, sizeof(server_addr)) == -1)
    ERROR("bind()");

  // start listening to socket for incomming connections
  listen(socket_fd, MAX_PENDING_CONNECTIONS);
  fprintf(stderr, "(Info) main: Listening for new connections on port %d ...\n", MY_PORT);
  clen = sizeof(client_addr);

  // Allocate memory for the database.
  if (!(db = (KISSDB *)malloc(sizeof(KISSDB)))) {
    fprintf(stderr, "(Error) main: Cannot allocate memory for the database.\n");
    return 1;
  }

  // Open the database.
  if (KISSDB_open(db, "mydb.db", KISSDB_OPEN_MODE_RWCREAT, HASH_SIZE, KEY_SIZE, VALUE_SIZE)) {
    fprintf(stderr, "(Error) main: Cannot open the database.\n");
    return 1;
  }

  pthread_t tids[NUMBER_OF_THREADS];
  int i;
  for(i = 0; i < NUMBER_OF_THREADS; i++){
    pthread_create(&tids[i], NULL, consumer_thread_function, NULL);
    pthread_detach(tids[i]);
  }

  while (1) {
    // wait for incomming connection
    if ((new_fd = accept(socket_fd, (struct sockaddr *)&client_addr, &clen)) == -1) {
      ERROR("accept()");
    }

    // got connection, serve request
    fprintf(stderr, "(Info) main: Got connection from '%s'\n", inet_ntoa(client_addr.sin_addr));
    producer_thread_function(new_fd);
  }

  // Destroy the database.
  // Close the database.
  KISSDB_close(db);

  // Free memory.
  if (db)
    free(db);
  db = NULL;

  return 0;
}

void producer_thread_function(int new_fd){
	fdStartTimeCouple_t new_fd_start_time_couple;

	new_fd_start_time_couple.socket_fd = new_fd;
	gettimeofday(&new_fd_start_time_couple.tv, NULL);

	pthread_mutex_lock(&full_queue_mutex);
	while(non_full_queue == false){
		pthread_cond_wait(&full_queue_condition, &full_queue_mutex);
	}
	pthread_mutex_unlock(&full_queue_mutex);

	pthread_mutex_lock(&queue_mutex);
	enqueue(new_fd_start_time_couple);
	pthread_mutex_unlock(&queue_mutex);

	pthread_mutex_lock(&empty_queue_mutex);
	if(non_empty_queue == true){
		pthread_cond_signal(&empty_queue_condition);
	}
	pthread_mutex_unlock(&empty_queue_mutex);
}

void *consumer_thread_function(){
	struct timeval start_of_service_time;
	while(!kill_all_consumer_threads){
		pthread_mutex_lock(&empty_queue_mutex);
		while(non_empty_queue == false){
			pthread_cond_wait(&empty_queue_condition, &empty_queue_mutex);
		}

		pthread_mutex_lock(&queue_mutex);
		fdStartTimeCouple_t dequeued_item = dequeue();
		total_waiting_time = compute_total_waiting_time(dequeued_item);
		sum_of_total_waiting_time += total_waiting_time;
		pthread_mutex_unlock(&queue_mutex);

		pthread_mutex_unlock(&empty_queue_mutex);

		pthread_mutex_lock(&full_queue_mutex);
		if(non_full_queue == true){
			pthread_cond_signal(&full_queue_condition);
		}
		pthread_mutex_unlock(&full_queue_mutex);

		gettimeofday(&start_of_service_time, NULL);
		process_request(dequeued_item.socket_fd);
        	close(dequeued_item.socket_fd);
		pthread_mutex_lock(&completed_requests_and_service_time_mutex);
		completed_requests += 1;
		total_service_time = compute_total_service_time(start_of_service_time);
		sum_of_total_service_time += total_service_time;
		pthread_mutex_unlock(&completed_requests_and_service_time_mutex);
	}
	return NULL;
}

double compute_total_waiting_time(fdStartTimeCouple_t dequeued_item){
	struct timeval end_of_waiting_time;
	gettimeofday(&end_of_waiting_time, NULL);

	return ((end_of_waiting_time.tv_sec - dequeued_item.tv.tv_sec) + (end_of_waiting_time.tv_usec - dequeued_item.tv.tv_usec)*1.0E-6);
}

double compute_total_service_time(struct timeval start_of_service_time){
	struct timeval end_of_service_time;
	gettimeofday(&end_of_service_time, NULL);

	return ((end_of_service_time.tv_sec - start_of_service_time.tv_sec) + (end_of_service_time.tv_usec - start_of_service_time.tv_usec)*1.0E-6);
}

void enqueue(fdStartTimeCouple_t fd_start_time_couple){
	if(!is_full()){
		if(queue.tail == QUEUE_SIZE-1){
			queue.tail = -1;
		}

		queue.cells[++queue.tail] = fd_start_time_couple;
		queue.item_count++;
		update_queue_management_variables();
	}
}

fdStartTimeCouple_t dequeue(void){
	fdStartTimeCouple_t dequeued_item;
	dequeued_item = queue.cells[queue.head++];
	if(queue.head == QUEUE_SIZE){
		queue.head = 0;
	}
	queue.item_count--;
	update_queue_management_variables();
	return dequeued_item;
}

bool is_empty(void){
	return queue.item_count == 0;
}

bool is_full(void){
	return queue.item_count == QUEUE_SIZE;
}

void update_queue_management_variables(void){
	non_empty_queue = !is_empty();
	non_full_queue = !is_full();
}

void initialize_queue(void){
	queue.head = 0;
	queue.tail = -1;
	queue.item_count = 0;
}

void initialize_global_variables(void){
	non_empty_queue = false;
	non_full_queue = true;
	kill_all_consumer_threads = false;
	sum_of_total_waiting_time = 0;
	sum_of_total_service_time = 0;
	completed_requests = 0;
	reader_count = 0;
	writer_count = 0;
}

void sigint_handler(int sig){
	printf("\n SIGINT handler \n");
	kill_all_consumer_threads = true;
	exit(1);
}

void sigstp_handler(int sig){
	double average_of_total_waiting_time;
	double average_of_total_service_time;
	printf("\n SIGSTP handler \n");
	average_of_total_waiting_time = sum_of_total_waiting_time/completed_requests;
	average_of_total_service_time = sum_of_total_service_time/completed_requests;
	printf("Number of completed requests:	%ld \n", completed_requests);
	printf("Average waiting time:	%f \n", average_of_total_waiting_time);
	printf("Average service time:	%f \n", average_of_total_service_time);
	kill_all_consumer_threads = true;
	exit(0);
}

