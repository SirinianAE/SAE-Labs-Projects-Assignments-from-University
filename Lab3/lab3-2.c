//Author: Sirinian Aram Emmanouil
#include <stdio.h>

/* credit card checker */

#define DIGITS 16
int check_card_number(char *str);

int main(void)
{
	char cc_id[DIGITS+2];
	int res;
	
	/* I am going to use 16 characters to represent a credit card ID.
	 * Function fgets reads and stores the characters and additionaly puts
	 * the character '\n' of the input. Finally it puts the character '\0'
	 * to terminate the string. That's why i need 18 characters for my string.
	 */
	printf("Type the card number: ");
	fgets(cc_id, DIGITS+2, stdin);
	
	res = check_card_number(cc_id);
	
	if(res == 1)
		printf("Card number is valid\n");
	else
		printf("Card number is NOT valid\n");
	
	return 0;
}

int check_card_number(char *str)
{
	int i, pos;
	int sum = 0;
	int num_int;
	
	/* Check the characters starting from the last one */
	for(i=DIGITS-1, pos=1; i>=0; i--, pos++)
	{
		num_int = (int)(*(str+i) - '0');    /* Integer value of character  */
		if(pos % 2 == 0)                    /* If we are in an  even psoition */
		{
			num_int *= 2;                     /* Double the number */
			
			/* Get the sum of its digits:
			 * 5 -> 10 -> 1, 6 -> 12 -> 3, ..., 9 -> 18 -> 9
			 * Basically, just subtract 9 from the double.
			 */
			if(num_int >= 10)
				num_int = num_int - 9;
		}
		sum += num_int;                     /* Sum the digits */
	}
	
	/* Equivallent to: return ( sum % 10 == 0 ); */
	if(sum % 10 == 0)
		return 1;
	else
		return 0;
}
