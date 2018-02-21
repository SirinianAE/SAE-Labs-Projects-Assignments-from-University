//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <sys/types.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>

int pfd[2];        /* Pipe */

void parent()
{
	FILE *txtfile;
	int  n, num;

	close(pfd[1]); /* No writing by the parent */
	printf("(%d) - I am the parent.\n", getpid());
	printf("       Waiting for nums and writing positive ones to file.\n");

	txtfile = fopen("positive.txt", "w");    /* Open text file for writing */
	if(txtfile == NULL) {
		perror("fopen()");
		exit (1);
	}

	/* Read from pipe and write to file */
	while ((n = read(pfd[0], &num, sizeof(int))) > 0)
	{
		if (num > 0)
			fprintf(txtfile, "%d\n", num);   /* Write to file if > 0 */
	}

	if (n < 0) {                       /* Error */
		perror("read()");
		exit (1);
	}

	waitpid(-1, NULL, 0);              /* Wait my child process */

	close(pfd[0]);                     /* (n is 0): child closed pipe */
	fclose(txtfile);                   /* Close text file */
}

void child()
{
	int num;

	close(pfd[0]); /* No reading by the child */
	printf("(%d) - I am the child.\n", getpid());
	printf("       Type positive/negative integers (give -1 to finish):\n");

	while(1)
	{
		scanf("%d", &num);
		if(num == -1)
		{
			close(pfd[1]);
			return;                          /* Finish */
		}
		write(pfd[1], &num, sizeof(int));  /* Write to pipe */
	}
}

int main()
{
	if (pipe(pfd) < 0) {
		perror("pipe()");
		exit(1);
	}

	if (fork() != 0)
		parent();
	else
		child();
	return 0;
}
