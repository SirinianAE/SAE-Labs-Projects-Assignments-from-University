//Author: Sirinian Aram Emmanouil
#include <stdio.h>

void set_zero(char str[50])
{
  int i,j;
  
  for(i=0; j<50; i++)
    str[i] = '\0';
  
  return;
}

int main(void)
{
  char name[50];
  int age;

  printf("Type your age: ");
  scanf("%d", age);
  
  set_zero(name);
  
  printf("Type your name: ");
  scanf("%s", name);
    
  printf("Hello %s! In ten years will be %d\n", name, age+10);
  
  return 0;
}
