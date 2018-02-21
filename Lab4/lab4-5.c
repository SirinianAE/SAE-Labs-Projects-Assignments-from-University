//Author: Sirinian Aram Emmanouil
/* String2words
 * MYY502
 */
#include <stdio.h>
#include <ctype.h>    // isspace()
#include <stdlib.h>   // malloc()


void printwords(char **words)
{
	int i;
	
	for (i = 0; words[i] != NULL; i++)
		puts(words[i]);
	
	/* Another way would be the following: */
	
	// for ( ; *words != NULL; words++) 
	//  puts(*words);
}


char **string2words(char *string)
{
	int  n;
	char *s, **words;

	/* 1. First pass: determine the number of words */
	for (s = string, n = 0; *s != '\0'; )
	{
		while (isspace(*s))   /* Skip spaces */
			s++;
		if (*s == '\0')       /* End of string (EOS) ? */
			break;
		
		n++;                  /* Non-space character: a new word starts */
		while (!isspace(*s) && *s != '\0')  /* Till we find a space or EOS */
			s++;
	}
	
	/* 2. Allocate memory for word pointers */
	words = malloc((n+1)*sizeof(char *));     /* n pointers to words + 1 */
	if (words == NULL)
	{
		printf("cannot allocate memory for the words\n");
		exit(1);
	}
	
	/* 3. Second pass: word terminations and word pointers */
	for (s = string, n = 0; *s != '\0'; )
	{
		while (isspace(*s))   /* First skip spaces */
		{
			*s = '\0';          /* Replace by \0 */
			s++;
		}
		if (*s == '\0')       /* End of string */
			break;
		
		words[n++] = s;       /* Start of new word */
		while (!isspace(*s) && *s != '\0')
			s++;
	}
	words[n] = NULL;        /* Last pointer set to NULL */
	return (words);
}


main()
{
	char s[258], **words;

	printf("String to tokenize: ");
	if (fgets(s, 256, stdin) < 0)
		exit (1);

	words = string2words(s);
	printwords(words);
}
