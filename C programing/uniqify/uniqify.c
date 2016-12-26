/*THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS Dong Sun Yoon */

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>

void parser (int file);
void suppressor(int file);
char *truncc(char *name);

int main(int argc, char* argv[]){
	int child, child1;
	int pspipe[2];
	int sspipe[2];

	pipe(pspipe);
	pipe(sspipe);
	
	child1 = fork();
	if (child1 == 0){
		close(pspipe[0]); 
		close(pspipe[1]); // close pipe parser to sort
		close(sspipe[1]); // close pipe write to sort
		suppressor(sspipe[0]);
	}
	else{
		child = fork();
		if(child == 0){
			close(pspipe[1]); // close pipe write to parser
			close(sspipe[0]); // close pipe read from suppressor
			dup2(pspipe[0], STDIN_FILENO);//duplicate to stdin read from parser
			dup2(sspipe[1], STDOUT_FILENO);//duplicate to stdout write to suppressor
			close(pspipe[0]);
			close(sspipe[1]);
			execl("/bin/sort","sort",(char*) NULL);
		}
		else{
			close(pspipe[0]); // close pipe read from sort
			close(sspipe[0]);
			close(sspipe[1]); // close pipe sort to suppressor
			parser(pspipe[1]);
			while(wait(NULL) > 0 );
		}
	}
}

void parser (int file){ //parse only alphabet characters to sorting pipe
	FILE *pstream;
	int c;
	int check = 0; 
	pstream = fdopen(file, "w");
	while((c = fgetc(stdin)) != EOF ){
		c = tolower(c);
		if ((c >= 97 && c <= 122)){
			fputc(c, pstream);	
			check = 1;		
		}else{
			if(check == 1){
				fputc('\n', pstream);
				check = 0;
			}
		}
	}
	fclose(pstream);
}

void suppressor(int file){ //combine duplicates of char. and count the numbers it is duplicated.
	FILE *sstream;
	char nfile[1000];
	char cfile[31];
	int count = 0;
	sstream = fdopen(file, "r");
	
	while(fgets(nfile, 1000, sstream)!=NULL){
		if(strlen(nfile) -1 < 31){
			if (strcmp(cfile, nfile) == 0){
				count++;
			}else if (count >= 1){
				printf("%-5d", count);
				fputs(cfile, stdout);
				count = 1;
				strcpy(cfile, nfile);

			}else{
				strcpy(cfile, nfile);
				count = 1;
			}

		}else if(strlen(nfile)-1 > 30){
			char *n_file;
			n_file = truncc(nfile); //get 30char and truncate rest
			if (strcmp(cfile, n_file) == 0){
				count++;
			}else{
				if (count >= 1){
					printf("%-5d", count);
					fputs(cfile, stdout);
					count = 1;
					strcpy(cfile, n_file);
				}else {
					strcpy(cfile, n_file);
					count = 1;
				}
			}
		}	
	}
	if (strlen(cfile)-1 < 31 && count >= 1){ //include the very last char
		printf("%-5d", count);
		fputs(cfile, stdout);
	}
	fclose(sstream);
}

char *truncc(char *name){ //truncate any char size greater than 30
	char *fname;
	fname = malloc(16*sizeof(char));
	int i;
	for(i=0; i<30; i++){
		if(name[i] != '\n'){ fname[i] = name[i]; }
	}
	fname[30] = '\n';
	return fname;
}
