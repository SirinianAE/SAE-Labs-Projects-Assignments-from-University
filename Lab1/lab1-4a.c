//Author: Sirinian Aram Emmanouil
#include <stdio.h>

int main(void)
{
	int A[10];
	int N = 0;
	int i;
	
	/* Diavasmsa arithmou apo xristi */
	while(N<1)
	{
		printf("Parakalw dwse ton arithmo N: ");
		scanf("%d", &N);
	}
	
	/* Ypologismos propaidias */
	for(i=0; i<10; i++)
		A[i] = (i+1)*N;
	
	/* Ektipwsi apotelesmatos */
	/* Gia na emfanizetai kalitera vazw ena keno
	 * stin arxi gia ta 9 prwta apotelesmata.
	 */
	printf("****** H PROPAIDEIA TOY %d ******\n", N);
	for(i=0; i<10; i++)
		if(i != 9)
			printf("\t %i X %d = %d\n", i+1, N, A[i]);
		else
			printf("\t%i X %d = %d\n", i+1, N, A[i]);
		
	return 0;
}
