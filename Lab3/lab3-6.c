//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <string.h>

/* Nameday calendar  */

#define N 5

char *dates[N] = {
	"12/12",
	"6/12",
	"30/11",
	"12/6",
	"13/5"
};
char *names[N] = {
	"Spiros, Spiridoula",
	"Nikolaos, Nikoletta, Niki",
	"Andreas, Andriana, Andromaxi, Maxi",
	"Onoufrios",
	"Glykeria"
};

/* Usage: ./a.out -d date  or  ./a.out -n name
 */
int main(int argc, char *argv[])
{
	int found = 0;
	int i, j;

	if (argc != 3)    /* Xreiazomaste 3 orismata */
		return 1;

	/* Sigkrinw to prwto orisma me ta epitrepomena modes -d (date) -n (name) */
	if(strcmp(argv[1], "-d") == 0) /* O xristis edwse ws orisma imerominia */
	{
		/* Psaxnw ton pinaka me tis imerominies */
		for(i=0; i<N; i++)
		{
			if(strcmp(argv[2], dates[i]) == 0)
			{
				/* An i imerominia iparxei ektipwnw ta antistoixa onomata */
				found = 1;
				printf("%s is the nameday of: %s\n", dates[i], names[i]);
			}
		}
		if(found == 0)
			printf("This date is not included in the calendar.\n");
	}
	else
		if(strcmp(argv[1], "-n") == 0) /* O xristis edwse ws orisma onoma */
		{
			/* Psaxnw ton pinaka me ta onomata */
			for(i=0; i<N; i++)
			{
				if(strstr(names[i], argv[2]) != NULL)
				{
					/* An to onoma iparxei ektipwnw tin antistoixi imerominia */
					found = 1;
					printf("%s nameday is on: %s\n", names[i], dates[i]);
				}
			}
			if(found == 0)
				printf("This name is not included in the calendar.\n");
		}
		else
		{
			/* An den dothike to swsta input */
			printf("Error. Usage: ./a.out -d 30/11 or ./a.out -n spiros\n");
			return 1;
		}

	return 0;
}
