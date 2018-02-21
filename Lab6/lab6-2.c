//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LINE_SIZE 256

struct student {
	char  onoma[LINE_SIZE];
	int   am;
	float vathmos;
};

typedef struct student student_t;

/* Specify return type */
student_t *create_struct_table(int student_num)
{
	int i;
	student_t *table;
	char buff[LINE_SIZE];

	/* Allocate a table of structures */
	table = (student_t *)malloc(student_num*sizeof(student_t)); 
	if (table == NULL) exit(1);  
	for(i=0; i<student_num; i++)
	{
		/* Get the name of student */
		printf("%d Student name: ", i+1);
		fgets(buff, LINE_SIZE, stdin);
		buff[strlen(buff) - 1] = '\0'; /* Remove \n from end of buff */
		strcpy(table[i].onoma, buff);
		
		/* Get the AM of the student */
		printf("%d Student AM(integer): ", i+1);
		fgets(buff, LINE_SIZE, stdin);
		table[i].am = atoi(buff);

		/* Get the grade of the student */
		printf("%d Student grade(float): ", i+1);
		fgets(buff, LINE_SIZE, stdin);
		table[i].vathmos = atof(buff);
	}
	return (table);
}

void show_struct_table(student_t *table, int student_num)
{
	int i;
	
	/* Print all the members of struct */
	for(i=0; i<student_num; i++)
		printf("Onoma: %s, AM: %d, Vathmos: %f\n", table[i].onoma, table[i].am, table[i].vathmos);
}

float compute_average_grade(student_t *table, int student_num)
{
	int i;
	float total_grade = 0.0;
	
	/* Find sum of all grades */
	for(i=0; i<student_num; i++)
		total_grade += table[i].vathmos;
	
	/* Return the average */
	return (total_grade/((float)student_num));
}

void print_failed_names(student_t *table, int student_num)
{
	int i;
	
	printf("Kopikan oi eksis mathites:\n");
	
	/* Search the table for students that have grade less than 10 */
	for(i=0; i<student_num; i++)
		if(table[i].vathmos < 10) /* Print... */
			printf("Onoma: %s\n", table[i].onoma);
}

void change_failed_names(student_t *table, int student_num)
{
	int i;
	
	/* Search the table for students that have grade less than 10 */
	for(i=0; i<student_num; i++)
		if(table[i].vathmos < 10)
			strcat(table[i].onoma, " (failed)"); /* Concatenate the (failed) */

}

int main(void)
{
	int student_num;
	char buff[LINE_SIZE];
	
	printf("Give the number of students: ");

	/* First get the number of the students */
	fgets(buff, LINE_SIZE, stdin);
	student_num = atoi(buff);
	
	if(student_num <= 0)
		return 0;
	
	student_t *table = create_struct_table(student_num);
	show_struct_table(table, student_num);
	
	print_failed_names(table, student_num);
	
	printf("Average grade =%f\n", compute_average_grade(table, student_num));
	
	change_failed_names(table, student_num);
	printf("After changing names...\n");
	
	print_failed_names(table, student_num);

	return 0;
}
