//Author: Sirinian Aram Emmanouil
#include <stdio.h>

/* Small dictionary */

#define N 8
#define M 32

int main(void)
{
	/* Initialize the dictionary */
	char *dic[N] = { 
		"Hello",
		"World",
		"This",
		"is",
		"a",
		"very",
		"small",
		"dictionary"
	};
	char user_input[M];
	int i;
	int found;
	
	while(1)
	{
		/* Prompt for input */
		printf("\n\nType a word or \"fin\" to exit program: ");
		scanf("%s", user_input);
		
		/* Check for program termination */
		if(strcmp(user_input, "fin") == 0)
			break;
		
		/* Search dictionary for the given word */
		found = 0;
		for(i=0; i<N; i++)
		{
			if(strcmp(user_input, dic[i]) == 0)
			{
				found = 1;
				break;
			}
		}
		
		if(found)
			printf("World: \"%s\" exists in dictionary\n", user_input);
		else
			printf("World: \"%s\" DOES NOT exist in dictionary\n", user_input);
	}
	
	return 0;
}
