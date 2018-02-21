//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <stdlib.h>


int main()
{
	int i, n1 = 0, n2 = 0;
	int *a1;

	/* Get original array size (n1) */
	printf("Enter array size: ");
	scanf("%d", &n1); 

	/* Create dynamically a new array of n1 ints */
	a1 = (int *)malloc(n1*sizeof(int));
	for(i = 0; i < n1; i++)
	{
		/* Set each element of a1 equal to 50 */
		a1[i] = 50;
	}

	/* Ask the user specifies for the new array size (n2) */
	printf("Enter new array size: ");
	scanf("%d", &n2);

	/* Change the a1 array size to n2 */
	a1 = (int *)realloc(a1, n2*sizeof(int));

	/* If n2 > n1, set all new elements to 0 */
	for(i=n1; i<n2; i++)
	  a1[i] = 0;
	
	/* Print */
	for(i=0; i<n2;i++)
		printf("%d ",  a1[i]);
	printf("\n");
	
	/* Free an allocated memory */
	free(a1);
	
	return 0;
}
