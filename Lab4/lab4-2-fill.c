/* MYY502 - LAB4
 * You must fill the code everywhere FILL HERE is written
 */
 
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
	a1 = /* FILL HERE */
	for(i = 0; i < n1; i++)
	{
		/* Set each element of a1 equal to 50 */
		/* FILL HERE */ = 55;
	}

	/* Ask the user specifies for the new array size (n2) */
	printf("Enter new array size: ");
	scanf("%d", &n2);

	/* Change the a1 array size to n2 */
	a1 = /* FILL HERE */

	/* If n2 > n1, set all new elements to 0 */
	/*
	FILL HERE
	*/
	
	/* Print */
	for(i=0; i<n2;i++)
		printf("%d ",  /* FILL HERE */);
	printf("\n");
	
	/* Free an allocated memory */
	/* FILL HERE */
	
	return 0;
}