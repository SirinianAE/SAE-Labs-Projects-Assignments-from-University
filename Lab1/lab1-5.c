//Author: Sirinian Aram Emmanouil
#include <stdio.h>

int main(void) {
	char input;
	int num = 0;
	
	/* Ksekiname atermona vroxo */
	while (1) {
		printf("Type a number, to exit type a character: ");
		scanf(" %c", &input);
		/* Elegxoume an einai arithmos */
		if (input >= '0' && input <= '9') {
			/* Metatrepoume ton ASCII kodiko se arithmo metaksi 0 kai 9
			 * afairontas apo ton xaraktira input ton xaraktira 0
			 */
			num = num*10 + (input-'0');
		}
		else
			/* An o xristis den edwse arithmo tote bgainoume apo ton vroxo */
			break;
	}
	
	/* Ektypwsi apotelesmatos */
	printf("Number = %d\n", num);
	
	return 0;
}
