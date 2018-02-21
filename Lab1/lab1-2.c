//Author: Sirinian Aram Emmanouil
#include <stdio.h>

int main(void) {
	char chr, capital, small;
	int given_chars = 0;
	int given_nums = 0;
	int i;
	int NUMS[30];

	do {
		printf("Dwste charaktira: ");
		scanf(" %c", &chr);

		/* Elegxoume an einai mikro gramma */
		if (chr >= 'a' && chr <= 'z') {
			/* Gia na paroume to kefalaio enos mikrou grammatos arkei
			 * na vroume ti thesi toy sto alfavito (- 'a') kai na tin prosthetoume
			 * sto mikro 'A'.
			 */
			capital = (chr - 'a') + 'A';
			printf("To kefalaio tou '%c' einai '%c'\n", chr, capital);
		}
		/* Elegxoume an einai kefalaio gramma */
		else if (chr >= 'A' && chr <= 'Z') {
			/* Gia na paroume to mikro enos kefalaiou grammatos arkei
			 * na vroume ti thesi toy sto alfavito (- 'A') kai na tin prosthetoume
			 * sto mikro 'a'.
			 */
			small = (chr - 'A') + 'a';
			printf("To mikro tou '%c' einai '%c'\n", chr, small);
		}
		/* Elegxoume an einai noumero */
		else if (chr >= '0' && chr <= '9') {
			/* Sthn periptwsi pou einai noumero tote apothikeuoume ston
			 * pinaka NUMS tin aksia pou tha eixe an itan akeraios.
			 * Gia na vroume tin aksia tou arkei na afairesoume tin
			 * thesi tou xaraktira '0'
			 */
			NUMS[given_nums++] = chr - '0';
		}


		/* O xristis edwse ena xaraktira */
		given_chars++;
	} while (chr != '$' && given_chars < 30);

	printf("Arithmoi: ");
	for(i=0; i<given_nums; i++)
		printf("%d ", NUMS[i]);
	printf("\n");

	return 0;
}
