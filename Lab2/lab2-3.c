//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#define N 10

int *search(int array[], int size, int num);  /* prototype */

int main(void)
{
	/* Arxikopoiw tous pinakes */
	int A[N] = {0, 4, 8, 2, -2, 100, 45, -12, 45, 31};
	int *res;
	
	res = search(A, N, 100);
	if(res != NULL)
	{
		printf("Number=%d, position(0-%d)=%d\n", *res, N-1, res-A);
	}
	else
	{
		printf("Number not found!\n");
	}
	return;
}

int *search(int array[], int size, int num)
{
	int i;
	
	for(i=0; i<size; i++)
	{
		if(array[i] == num)
		{
			return &(array[i]); /* Otan to vrw epistrefw tin dieuthinsi tou */
		}
	}
	return NULL;
}
