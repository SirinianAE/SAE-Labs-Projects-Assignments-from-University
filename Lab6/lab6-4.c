//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LINE_SIZE 256

struct student {
	char  onoma[LINE_SIZE];
	int   am;
	float vathmos;
	struct student *next;
};

typedef struct student student_t;

/* Specify return type */
student_t *create_struct_table(int student_num)
{
	int i;
	char buff[LINE_SIZE];
	student_t *temp;  /* Store one student here */
	student_t *head;  /* Create the list */

	head = NULL;       /* Empty list */
	
	for(i=0; i<student_num; i++)
	{
		temp = (student_t *)malloc(sizeof(student_t));  /* Memory for 1 node */
		if (temp == NULL) exit(1);
		/* Get the name of student */
		printf("%d Student name: ", i+1);
		fgets(buff, LINE_SIZE, stdin);
		buff[strlen(buff) -1] = '\0'; /* Remove '\n' from buff */
		strcpy(temp->onoma, buff);
		
		/* Get the AM of the student */
		printf("%d Student AM(integer): ", i+1);
		fgets(buff, LINE_SIZE, stdin);
		temp->am = atoi(buff);

		/* Get the grade of the student */
		printf("%d Student grade(float): ", i+1);
		fgets(buff, LINE_SIZE, stdin);
		temp->vathmos = atof(buff);
		
		temp->next = head;                  /* Insert in the front of the list */
		head = temp;
	}
	
	return (head);
}

void show_struct_table(student_t *list)
{
	while (list != NULL)        /* Until there is no other node in the list */
	{
		printf("Onoma: %s, AM: %d, Vathmos: %f\n", list->onoma, list->am, list->vathmos);
		list = list->next;               /* Go to the next node */
	}
}

float compute_average_grade(student_t *list)
{
	float total_grade = 0.0;
	int student_num = 0;
	
	while (list != NULL)        /* Until there is no other node in the list */
	{
		total_grade += list->vathmos;
		student_num ++;          /* Count the students */
		list = list->next;               /* Go to the next node */
	}
	
	/* Return the average */
	return (total_grade/((float)student_num));
}

void print_failed_names(student_t *list)
{
	printf("Kopikan oi eksis mathites:\n");
	
	while (list != NULL)        /* Until there is no other node in the list */
	{
		if(list->vathmos < 10) /* Print... */
			printf("Onoma: %s\n", list->onoma);
		
		list = list->next;               /* Go to the next node */
	}
}

void change_failed_names(student_t *list)
{
	while (list != NULL)        /* Until there is no other node in the list */
	{
		if(list->vathmos < 10)
			strcat(list->onoma, " (failed)"); /* Concatenate the (failed) */

		list = list->next;               /* Go to the next node */
	}

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
	
	student_t *head = create_struct_table(student_num);
	show_struct_table(head);
	
	print_failed_names(head);
	
	printf("Average grade =%f\n", compute_average_grade(head));
	
	change_failed_names(head);
	printf("After changing names...\n");
	
	print_failed_names(head);

	return 0;
}
