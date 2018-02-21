//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>

int pfd1[2], pfd2[2];        /* Pipes */

void parent()
{
	int n, num;
	int sum;
	double average;

	close(pfd1[0]);
	close(pfd2[1]);
	printf("(%d) - I am the parent.\n", getpid());
	printf("       Type integers (give -1 to finish):\n");

	while(1)
	{
		scanf("%d", &num);
		if(num == -1)
		{
			close(pfd1[1]);
			break;
		}
		write(pfd1[1], &num, sizeof(int));  /* Write to pipe */
	}

	read(pfd2[0], &sum, sizeof(int)); /* Read sum from pipe */
	read(pfd2[0], &average, sizeof(double)); /* Read average from pipe */
	printf("Sum=%d, Average=%lf\n", sum, average);

	close(pfd2[0]);
	waitpid(-1, NULL, 0);   /* Wait my child process */
}

void child()
{
	int n, num;
	int sum = 0, count = 0;
	double average;

	close(pfd1[1]);
	close(pfd2[0]);
	printf("(%d) - I am the child.\n", getpid());
	printf("       Waiting for nums to calculate sum and average.\n");

	while ((n = read(pfd1[0], &num, sizeof(int))) > 0)
	{
		sum += num;
		count ++;
	}

	if (n < 0) {                       /* Error */
		perror("read()");
		exit (1);
	}
	average = (double)sum/(double)count;

	close(pfd1[0]);                    /* Close pipe for reading */

	write(pfd2[1], &sum, sizeof(int)); /* Write sum to pipe */
	write(pfd2[1], &average, sizeof(double));  /* Write average to pipe */

	close(pfd2[1]);                   /* Close pipe for writing */
}

int main()
{
	if (pipe(pfd1) < 0) {
		perror("pipe(pfd1)");
		exit(1);
	}

	if (pipe(pfd2) < 0) {
		perror("pipe(pfd2)");
		exit(1);
	}

	if (fork() != 0)
		parent();
	else
		child();
	return 0;
}
