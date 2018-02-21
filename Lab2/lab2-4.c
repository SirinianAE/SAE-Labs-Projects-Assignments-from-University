//Author: Sirinian Aram Emmanouil
#include <stdio.h>

void mumicr(char *s);    /* prototypo */


int main(void)
{
	/* Arxikopoiw tin simvoloseira s
	 * Den mporw na xrisimopoiisw tin dilwsi
	 * char *s = "This course is called MYY502";
	 * dioti to string se aytin tin periptwsi den
	 * mporei na allaksei. Me dilwsi pou akolouthei
	 * dilwnw enan pinaka xaraktirwn kai antigrafw
	 * ta stoixeia tou string "This course is called MYY502"
	 * mesa ston pinaka ayton.
	 */

	char s[] = "This course is called MYY502";

	printf("Before mumicr: %s\n", s);
	mumicr(s);
	printf("After  mumicr: %s\n", s);
	return 0;
}


void mumicr(char *s)
{
	/* Psaxnw tin simvoloseira */
	while(*s != '\0')
	{
		if(*s>='0' && *s<='8')  /* An einai kefalaio */
		{
			*s += 1; /* Pigainw ston epomeno xaraktira ASCII */
		}
		s++;                    /* Proxoraw ton deikti */
	}
}
