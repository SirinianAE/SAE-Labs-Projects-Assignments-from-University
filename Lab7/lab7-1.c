//Author: Sirinian Aram Emmanouil
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>


void copy(char *input_file, char *output_file)
{
  int input_fd, output_fd;
  char buf;
  
  input_fd = open(input_file, O_RDONLY);
  if(input_fd == -1)
  {
    perror(input_file);
    exit(1);
  }
  
  /* We choose 0755 permissions */
  output_fd = open(output_file, O_WRONLY | O_CREAT | O_TRUNC, 
                        S_IRWXU | S_IRGRP | S_IXGRP | S_IROTH | S_IXOTH);
  if(output_fd == -1)
  {
    close(input_fd);
    perror(output_file);
    exit(1);
  }
  
  while(read(input_fd, &buf, sizeof(buf)))
    write(output_fd, &buf, sizeof(buf));
  
  close(input_fd);
  close(output_fd);
}

int main(int argc, char **argv)
{

  if(argc != 3)
  {
    printf("Usage: %s input output\n", argv[0]);
    return 1;
  }
  
  copy(argv[1], argv[2]);

  return 0;
}
