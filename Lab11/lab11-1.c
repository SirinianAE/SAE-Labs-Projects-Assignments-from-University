//Author: Sirinian Aram Emmanouil
/* Lab 11, Problem 1 */
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <signal.h>
#include <time.h>


void alarm_handler(int ignore)
{
	static int min = 0;
	static time_t curtime;

	time(&curtime);
	printf("%d minutes passed.\n", ++min);
	printf("     current time: %s\n", ctime(&curtime));
}


int main()
{
	struct itimerval it_val;                        /* For setting itimer */

	if (signal(SIGALRM, alarm_handler) == SIG_ERR)  /* Set SIALRM handler */
	{
		perror("Unable to catch SIGALRM");    
		exit(1);  
	}
	it_val.it_value.tv_sec =  60;                   /* Set the timer to 60 sec */
	it_val.it_value.tv_usec = 0;      
	it_val.it_interval = it_val.it_value;           /* Repeat */
	if (setitimer(ITIMER_REAL, &it_val, NULL) == -1) 
	{
		perror("error calling setitimer()");    
		exit(1);  
	}  
	while (1)  /* infinite loop - CTRL-C to finish */
		;
	return (1);
}
