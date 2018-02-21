//Author: Sirinian Aram Emmanouil
/* Printbin
 */
#include <stdio.h>

void printbin(int n)
{
  int i, mask = 1 << 31;    /* We assume integers are 32 bit */
  
  for (i = 0; i < 32; i++)
  {
    if (n & mask)           /* Keep only the highest bit */
      printf("1");
    else
      printf("0");
    n = n << 1;             /* Shift left */
  } 
}


int main()
{
  int n;

  while (1)
  {
    printf("\nEnter integer (0 exits): ");
    scanf("%d", &n);
    if (n == 0)
      break;
    printbin(n);
  }
  return 0;
}
