//Author: Sirinian Aram Emmanouil
/* Pascal triangle 
 * MYY502
 */
#include <stdio.h>
#include <stdlib.h>    /* For malloc(), free() */


int **pascal(int n);   /* Prototype */


void main(int argc, char *argv[])
{
	int n, i, j;
	int **p;

	if (argc < 2) 
		return;
	n = atoi(argv[1]);
	if (n < 1) 
		return;

	p = pascal(n);
	if (p == NULL) 
		return;

	/* Print */
	for (i = 0; i < n; i++)
	{
		for (j = 0; j <= i; j++)
			printf("%2d  ", p[i][j]);
		printf("\n");
	}

	/* Free memory */
	for (i = 0; i < n; i++)
		free(p[i]);         /* Free row i */
	free(p);              /* Free the array of pointers */
}


int **pascal(int n)
{
	int i, j;
	int **array;

	if (n < 1) return(NULL);  /* Cannot do it */

	/* Allocate n pointers to point to the rows */
	array = (int **) malloc(n * sizeof(int *));
	if (array == NULL) 
		return (NULL);

	for (i = 0; i < n; i++)
	{
		/* Allocate a row of size i+1 */
		array[i] = (int *) malloc((i+1) * sizeof(int));
		if (array[i] == NULL) 
			return (NULL);

		/* Calculate */
		array[i][0] = 1;
		for (j = 1; j < i; j++)
			array[i][j] = array[i-1][j-1] + array[i-1][j];
		array[i][i] = 1;
	}
	
	return (array);
}
