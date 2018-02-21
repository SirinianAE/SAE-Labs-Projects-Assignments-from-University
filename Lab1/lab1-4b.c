//Author: Sirinian Aram Emmanouil
#include <stdio.h>

int main(void)
{
	int A[10][10];
	int N;
	int i, j;
	
	/* Diavasmsa arithmou apo xristi */
	do{
		printf("Parakalw dwse ton arithmo N: ");
		scanf("%d", &N);
	}while(N<1 || N>10);
	
	/* Ypologismos twn propaidiwn */
	/* H prwti diastasi tou pinaka einai oi arithmoi
	 * apo to 1...N
	 */
	for(j=0; j<N; j++)
		for(i=0; i<10; i++)
			A[j][i] = (i+1)*(j+1);
	
	/* Ektipwsi twn apotelesmatwm */
	/* Gia na emfanizetai kalitera vazw ena keno
	 * stin arxi gia ta 9 prwta apotelesmata.
	 */
	for(j=0; j<N; j++)
	{
		printf("****** H PROPAIDEIA TOY %d ******\n", j+1);
		for(i=0; i<10; i++)
			if(i != 9)
				printf("\t %i X %d = %d\n", i+1, j+1, A[j][i]);
			else
				printf("\t%i X %d = %d\n", i+1, j+1, A[j][i]);
	}

	return 0;
}
