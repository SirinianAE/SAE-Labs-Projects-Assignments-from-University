//Author: Sirinian Aram Emmanouil
/* Lab11, Problem #2 -- Typing game */
#include <stdio.h>
#include <stdlib.h>   /* For srand() */
#include <unistd.h>   /* for _exit() */
#include <time.h>
#include <sys/time.h>
#include <signal.h>


double totaltime = 0.0;  /* Add all typing times */
int    ntrials = 0;      /* # rounds played */


void alarm_handler(int ignore)
{
	if (ntrials > 0)
		printf("Average typing delay: %lf msec.\n", totaltime/ntrials);
	printf("Bye!\n");
	_exit(1);    /* don't call the usual exit() from a signal handler */
}


int main()
{
	struct timeval t1, t2;
	char c, newc;

	if (signal(SIGALRM, alarm_handler) == SIG_ERR) /* Set SIALRM handler */
	{
		perror("Unable to catch SIGALRM");    
		return (1);  
	}

	srand(time(NULL));
	printf("Type as fast as you can (and press ENTER).\n\n");
	
	while (1)
	{
		newc = '!' + ( rand() % (127 - '!') ); /* Random, from '!' to last ascii */
		printf("type  --->  %c\n", newc);
		
		gettimeofday(&t1, NULL);         /* Measure real time */
		do
		{
			alarm(5);                      /* Timeout at 5 sec */
			scanf("%c", &c);
			alarm(0);                      /* Cancel alarm */
		} while (c != newc);
		gettimeofday(&t2, NULL);
		
		totaltime += (t2.tv_sec - t1.tv_sec)*1000.0 +
		             (t2.tv_usec - t1.tv_usec)/1000.0;    /* msec */
		ntrials++;
	}
	
	return (1);
}
