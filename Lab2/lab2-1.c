//Author: Sirinian Aram Emmanouil
#include <stdio.h>

/* To megethos toy pinaka */
#define SIZE 10

int compare(int x, int y)
{
	if(x > y) /* An x megalitero apo y */
	{
		return 1;
	}
	else if(x == y) /* An x iso y */
	{
		return 0;
	}

	/* Se diaforetik periptosi... */
	return -1;
}

void swap(int *x, int *y)
{
	int temp;
	
	/* Apothikeuoyme se mia prosorini metavliti
	 * tin timi toy toy x kai epeita kanoume tis
	 * allages
	 */
	temp = *x;
	*x = *y;
	*y = temp;
	
	return;
}

void bubblesort(int A[], int N)
{
	int i, j;
	
	for(i=0; i<N; i++)
	{
		for(j=N-1; j>i; j--)
		{
			if(compare(A[j], A[j-1]) == -1)
			{
				swap(&A[j], &A[j-1]);
			}
		}
	}
	
	return;
}

int main(void)
{
	int A[SIZE];
	int i;
	int N = SIZE;
	
	/* Gemizoyme ton pinaka */
	for(i=0; i<SIZE; i++)
	{
		printf("Dwste to %d/%d stoixeio toy pinaka: ", i+1, N);
		scanf("%d", &A[i]);
	}
	
	/* Taksinomoume ton pinaka */
	bubblesort(A, N);
	
	/* Ektyposi */
	printf("Taksinomimenos pinakas: [ ");
	for(i=0; i<SIZE; i++)
	{
		printf("%d ", A[i]);
	}
	printf("]\n");
	
	return 0;
}
