//Author: Sirinian Aram Emmanouil
/* Simple program to plot using gnuplot */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <sys/wait.h>


int  fd[2];   /* Pipe for communication with child */


void parent()
{
  char cmd[50]; /* Buffer to put the gnuplot commands */

  close(fd[0]);   /* Not needed */
  printf("I am the parent (%d) - sending commands to gnuplot\n", getpid());

  printf("Type \"exit\" to finish.\n");   /* Wait for user */
  while (1)
	{
		fgets(cmd, 50, stdin);

		write(fd[1], cmd, strlen(cmd));

		if(strcmp(cmd, "exit;\n") == 0)
		{
			close(fd[1]);            /* Close pipe for writing */
			break;
		}
	}

	waitpid(-1, NULL, 0);           /* Wait the child to end */
	printf("bye.\n");
}


void child()
{
  printf("I am the child (%d) - about to exec gnuplot\n", getpid());
  close(fd[1]);   /* Not needed */

  if (dup2(fd[0], STDIN_FILENO) < 0)  /* Force pipe to flow to stdin */
  {
    perror("dup2()");
    exit(1);
  }

  execl("/usr/bin/gnuplot", "gnuplot", NULL);    /* exec gnuplot */
  printf("execl() fialed; gnuplot could not be run.\n");
}


int main()
{
  if (pipe(fd) < 0)
  {
    perror("pipe()");
    exit(1);
  }

  if (fork() == 0)   /* Create child process */
    child();
  else
    parent();

  return 0;
}
