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

student_t *create_struct_table(int student_num)
{
	int i;
	student_t *table;
	char buff[LINE_SIZE];
	
	/* Allocate a table of structures */
	table = (student_t *)malloc((student_num)*sizeof(student_t)); 
	if (table == NULL) exit(1);  
	for(i=0; i<student_num; i++)
	{
		/* Get the name of student */
		printf("%d Student name: ", i+1);
		fgets(buff, LINE_SIZE, stdin);
		buff[strlen(buff) -1] = '\0'; /* Remove '\n' from buff */
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

student_t *create_struct_table_from_file(FILE *sf, int *student_num)
{
	int i;
	student_t *table;
	char buff[LINE_SIZE];

	/* First get the number of the students */
	fgets(buff, LINE_SIZE, sf);
	buff[strlen(buff) -1] = '\0'; /* Remove '\n' from buff */
	*student_num = atoi(buff);
	
	/* Allocate a table of structures */
	table = (student_t *)malloc((*student_num)*sizeof(student_t)); 
	if (table == NULL) exit(1);  
	for(i=0; i<*student_num; i++)
	{
		/* Get the name of student */
		fgets(buff, LINE_SIZE, sf);
		buff[strlen(buff) -1] = '\0'; /* Remove '\n' from buff */
		strcpy(table[i].onoma, buff);
		
		/* Get the AM of the student */
		fgets(buff, LINE_SIZE, sf);
		table[i].am = atoi(buff);

		/* Get the grade of the student */
		fgets(buff, LINE_SIZE, sf);
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
		/* If student has failed and the tag has not been enabled yet... */
		if(table[i].vathmos < 10 && strstr(table[i].onoma, " (failed)") == NULL)
			strcat(table[i].onoma, " (failed)"); /* Concatenate the (failed) */

}

void write_student_data_to_file(student_t *table, int student_num)
{
	int i;
	FILE *sf;
	
	/* Try to open file with student data */
	sf = fopen("students.txt", "w");
	if(sf == NULL)
	{
		printf("Could not open file \"students.txt\" for writing!\n");
		return;
	}
	
	/* First print number of students to file */
	fprintf(sf, "%d\n", student_num);
	
	/* Next Print all the members of struct */
	for(i=0; i<student_num; i++)
		fprintf(sf, "%s\n%d\n%f\n", table[i].onoma, table[i].am, table[i].vathmos);
	
	/* Close the file... */
	fclose(sf);
}

int main(void)
{
	int student_num;
	char buff[LINE_SIZE];
	FILE *student_file;
	int pos, neg, sum;
	student_t *table;
  
	/* Try to open file with student data */
	student_file = fopen("students.txt", "r");
	if(student_file == NULL)
	{
		printf("Give the number of students: ");

		/* First get the number of the students */
		fgets(buff, LINE_SIZE, stdin);
		student_num = atoi(buff);
		
		if(student_num <= 0)
			return 0;

		/* Student file does not exist */
		table = create_struct_table(student_num);
	}
	else
	{
		/* Read student data from file */
		table = create_struct_table_from_file(student_file, &student_num);
		close(student_file);
	}
	
	
	show_struct_table(table, student_num);
	print_failed_names(table, student_num);
	printf("Average grade =%f\n", compute_average_grade(table, student_num));
	
	change_failed_names(table, student_num);
	printf("After changing names...\n");
	
	print_failed_names(table, student_num);
	write_student_data_to_file(table, student_num);
	printf("Data saved to file students.txt...\n");

	return 0;
}
