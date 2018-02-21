//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAXNUM 1000

int main(void)
{
  int size;
  int *matrix;
  int i, sum = 0;
  
  printf("Type the size of the matrix: ");
  scanf("%d", &size);
  
  /* Desmeuw xwro */
  matrix = (int *)malloc(size*sizeof(int));
  if(matrix == NULL)
  {
    printf("Error in malloc\n");
    return 1;
  }
  
  /* Arxikopoiisi mixanis tuxaiwn arithmwn */
  srand(time(NULL));
  
  /* Arxikopoiisi toy pinaka */
  for(i=0; i<size; i++)
    matrix[i] = rand() % MAXNUM;
  
  /* Ektipwsi toy pinaka kai ipologismos athroismatow twn
   * stoixeiwn toy.
   */
  for(i=0; i<size; i++)
  {
    printf("Matrix[%d]=%d\n", i, matrix[i]);
    sum += matrix[i];
  }
  
  /* Ektipwsi toy mesoy oroy */
  printf("Average=%f\n", (float)sum/(float)size);
  
  return 0;
}
