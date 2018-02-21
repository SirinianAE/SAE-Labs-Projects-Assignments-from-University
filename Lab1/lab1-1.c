//Author: Sirinian Aram Emmanouil
#include <stdio.h>

int main(void)
{
	int apousies;
	float A = 0.0, PR1, PR2, BE, TE, GR;

	/* Diavasmsa dedomenwn apo xristi */
	printf("Parakalw dwse ton arithmo apousiwn: ");
	scanf("%d", &apousies);

	printf("Parakalw dwse ton vathmo ptwtis proodou: ");
	scanf("%f", &PR1);

	printf("Parakalw dwse ton vathmo deuteris proodou: ");
	scanf("%f", &PR2);

	printf("Parakalw dwse ton vathmo telikis eksetasis: ");
	scanf("%f", &TE);

	/* Ypologismos vathmou apo parousies
	 * i metavliti A einai arxikopoiimeni
	 * idi sto 0.0.
	 */
	if(apousies == 0)
		A = 10.0;
	else if(apousies == 1)
		A = 5.0;

	/* Ypologismos vathmou ergastiriwn */
	BE = A*0.2 + PR1*0.4 + PR2*0.4;

	/* Elegxos gia teliko vathmo ergastiriwn */
	if(BE < 4.5)
	{
		printf("Apotyxia sta ergastiria!\n");
		return 1;
	}

	/* Elegxos gia vathmo telikis eksetasis */
	if(TE < 4.5)
	{
		printf("Apotyxia stin teliki eksetasi!\n");
		return 1;
	}

	/* Ypologismos telikou vathmou mathimatos */
	GR = BE*0.4 + TE*0.6;

	/* Ektypwsi apotelesmatos */
	printf("Epityxia sto MYY502 me vathmo %.2f\n", GR);

	return 0;
}
