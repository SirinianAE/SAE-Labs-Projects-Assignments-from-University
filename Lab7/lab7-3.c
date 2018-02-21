//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>

void printnums(int n, ...)
{
  char    c;
  int     integer_value;
  double  double_value;
  int     i;
  va_list args;

  va_start(args, n);
  for(i=0; i<n; i++)
  {
    c = va_arg(args, int);
    if(c == 'd')
    {
      integer_value = va_arg(args, int);
      printf("%d ", integer_value);
    }
    else
    {
      double_value = va_arg(args, double);
      printf("%lf ", double_value);
    }
  }
  va_end(args);
}


int main(void)
{
  printnums(3, 'd', 4, 'f', 4.3, 'd', 8);
  return 0;
}
