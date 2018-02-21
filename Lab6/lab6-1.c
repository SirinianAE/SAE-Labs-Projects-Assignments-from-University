//Author: Sirinian Aram Emmanouil
/* Read & write files
 */
#include <stdio.h>
#include <stdlib.h>


int main()
{
  int i, n, total_numbers, total_positive_numbers;
  int *numbers;
  FILE *input, *positives, *negatives;
  int pos, neg, sum;
  
  /* Open files */
  input = fopen("data.txt", "r");
  if(input == NULL)
  {
    printf("Could not open file \"data.txt\" for reading!\n");
    return 1;
  }
  
  positives = fopen("positive.txt", "w+");
  if(positives == NULL)
  {
    printf("Could not open file \"positive.txt\" for writing!\n");
    fclose(input);
    return 1;
  }

  negatives = fopen("negative.txt", "w+");
  if(negatives == NULL)
  {
    printf("Could not open file \"negative.txt\" for writing!\n");
    fclose(input);
    fclose(positives);
    return 1;
  }
  
  /* Count numbers */
  fscanf(input, "%d", &total_numbers);
  if(total_numbers <=0)
  {
    fprintf(positives, "0");
    fprintf(negatives, "0");
    fclose(input);
    fclose(positives);
    fclose(negatives);
    return 1;
  }
  
  numbers = (int *)malloc(total_numbers*sizeof(int));
  
  /* Read numbers from file */
  pos = 0;
  neg = 0;
  for(i=0; i<total_numbers; i++)
  {
    fscanf(input, "%d", &n);
    if(n>=0) pos++;
    else neg++;
    numbers[i] = n;
  }

  /* Write numbers to appropriate file */
  fprintf(positives, "%d\n", pos);
  fprintf(negatives, "%d\n", neg);
  for(i=0; i<total_numbers; i++)
  {
    if(numbers[i]>=0)
      fprintf(positives, "%d\n", numbers[i]);
    else
      fprintf(negatives, "%d\n", numbers[i]);
  }
  
  fclose(input);          /* close unnecessary files */
  fclose(negatives);
  
  /* Rewind file to starting position */
  rewind(positives);

  /* Print the sum of positive numbers */
	sum = 0;
  fscanf(positives, "%d", &total_positive_numbers);
  for(i=0; i<total_positive_numbers; i++)
  {
    fscanf(positives, "%d", &n);
    sum += n;
  }
  
  printf("Sum of positives is %d\n", sum);
  
  fclose(positives);
  return 0;
}
