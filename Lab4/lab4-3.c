//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
  int total_length = 0;
  char *all_strings;
  int i;
  
  /* An o xristis den edwse kamia simvoloseira */
  if(argc == 1)
    return 0;
  
  /* Metraw to sinoliko mikos twn simvoloseirwn */
  for(i=1; i<argc; i++)
    total_length += strlen(argv[i]);
  
  /* Desmeuw mnini */
  all_strings = (char *)malloc((total_length+1)*sizeof(char));
  
  /* Arxikopoiw to all_strings sto '\0' wste na mporw na
   * xrisimopoiisw tin strcat
   */
  *all_strings = '\0';
  
  /* Sinenwnw ta strings */
  for(i=1; i<argc; i++)
    strcat(all_strings, argv[i]);
  
  /* Ektipwsi toy sinenomenoy string */
  printf("%s\n", all_strings);
  
  return 0;
}
