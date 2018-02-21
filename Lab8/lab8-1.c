//Author: Sirinian Aram Emmanouil
#include <stdio.h>      /* For scanf, printf, puts */
#include <stdlib.h>     /* For exit */
#include <unistd.h>     /* For fork, execl */
#include <errno.h>      /* For perror */
#include <sys/wait.h>   /* For waitpid */

#define NUM 7

typedef struct {     /* Nice structure */
  char *name;
  char *menutitle;
  char *fullpath;
} option_t;


/* Array of structures */
option_t menu[NUM] = {
  { NULL,               "0. exit",                      NULL },
  { "firefox",          "1. run firefox",               "/usr/bin/firefox" },
  { "firefox",          "2. run firefox (and wait)",    "/usr/bin/firefox" },
  { "gedit",            "3. run gedit",                 "/usr/bin/gedit" },
  { "gedit",            "4. run gedit (and wait)",      "/usr/bin/gedit" },
  { "gnome-calculator", "5. run gnome-calc",            "/usr/bin/gnome-calculator" },
  { "gnome-calculator", "6. run gnome-calc (and wait)", "/usr/bin/gnome-calculator" }
};


int main()
{
  int i, sel, pid;

  while (1)      /* loop for ever */
  {
    for (i = 0; i < NUM; i++)
      puts(menu[i].menutitle);
    printf("\nPlease enter your selection: ");

    scanf("%d", &sel);
    if (sel < 0 || sel >= NUM)
      continue;
    if (sel == 0)
      break;

    if ((pid = fork()) == 0)    /* Create child process */
    {
      execl(menu[sel].fullpath, menu[sel].name, NULL);  /* Exec application */
      perror("execl()");        /* This should not be executed */
      exit(1);
    }
    else                        /* Me (parent) */
    {
      if ((sel & 1) == 0)       /* Even choice */
        waitpid(pid, NULL, 0);
    }
  }
}
