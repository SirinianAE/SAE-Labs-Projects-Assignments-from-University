//Author: Sirinian Aram Emmanouil
#include <stdio.h>

/* H sinartisi epistrefei deikti ston
 * teleytaio xaraktira tis simvoloseiras
 */
char *string_end(char *str)
{
  if (*str == '\0') return (str);
	
	/* Pigainw sto telos tis simvoloseiras */
  while (*(str+1) != '\0')
	{
    str++;
	}
	
  return (str);
}

void string_reverse(char *str)
{
	/* To p deixnei sto telos tis simvoloseiras */
  char c, *p = string_end(str);

	/* To str auksanetai, to p meionetai
	 * Otan to str ftasei to p tote i
	 * antistrofi exei oloklirothei
	 */
  for ( ; p > str; str++, p--)
  {
		/* Antallazw tous xaraktires,
		 * O 1os me ton teleytaio,
		 * O 2os me ton proteleytaio ktl...
		 */
    c = *str;
    *str = *p;
    *p = c;
  }
}


int main(void)
{
  char s[100];
	
	/* O xristis dinei tin simvoloseira */
  printf("Enter string: ");
  fgets(s, 99, stdin);
	
  string_reverse(s);
	
	/* Ektypwsi apotelesmatos */
  printf("Reversed: %s\n", s);
	
	return 0;
}
