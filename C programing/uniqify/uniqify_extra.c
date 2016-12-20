/*THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS Dong Sun Yoon */
//This is Optional Extension for Extra Credit

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>

void parser(int file, int file1);
void mergesuppressor(int file, int file1);
char *truncc(char *name);

int main(int argc, char* argv[]){
	int child, child1, child2;
	int pspipe[2]; // pipe to sort 1
	int ps1pipe[2]; // pipe to sort 2
	int sspipe[2];	
	int ss1pipe[2];

	pipe(pspipe);
	pipe(ps1pipe);
	pipe(sspipe);
	pipe(ss1pipe);
	
	child2 = fork();
	if (child2 == 0){
		close(pspipe[0]); 
		close(pspipe[1]); 
		close(ps1pipe[0]);
		close(ps1pipe[1]);// close pipe parser to sort
		close(sspipe[1]); 
		close(ss1pipe[1]);// close pipe write to sort
		mergesuppressor(sspipe[0], ss1pipe[0]);
	}
	else{
		child1 = fork();
		if(child1 == 0){ //sort 2
			close(pspipe[0]);
			close(pspipe[1]);
			close(sspipe[0]);
			close(sspipe[1]);
			close(ps1pipe[1]); // close pipe write to parser
			close(ss1pipe[0]); // close pipe read from suppressor
			dup2(ps1pipe[0], STDIN_FILENO);//duplicate to stdin read from parser
			dup2(ss1pipe[1], STDOUT_FILENO);//duplicate to stdout write to suppressor
			close(ps1pipe[0]);
			close(ss1pipe[1]);
			execl("/bin/sort","sort",(char*) NULL);
		}
		else{
			child = fork();
			if(child == 0){ //sort 1
				close(ps1pipe[0]);
				close(ps1pipe[1]);
				close(ss1pipe[0]);
				close(ss1pipe[1]);
				close(pspipe[1]);
				close(sspipe[0]);
				dup2(pspipe[0], STDIN_FILENO);//duplicate to stdin read from parser
				dup2(sspipe[1], STDOUT_FILENO);//duplicate to stdout write to suppressor
				close(pspipe[0]);
				close(sspipe[1]);
				execl("/bin/sort","sort",(char*) NULL);
			} else{
				close(pspipe[0]);
				close(ps1pipe[0]); // close pipe read from sort
				close(sspipe[0]);
				close(sspipe[1]); 
				close(ss1pipe[0]);
				close(ss1pipe[1]);// close pipe sort to suppressor
				parser(pspipe[1], ps1pipe[1]);
				while(wait(NULL) > 0 );
			}
		}
	}
}

void parser(int file, int file1){
	FILE *pstream;
	FILE *p1stream;
	int c;
	int check = 0;
	int lineNum = 0; // line number of the word seperate odd and even lineNum
	pstream = fdopen(file, "w"); // to sort 1
	p1stream = fdopen(file1, "w"); //to sort 2
	while((c = fgetc(stdin)) != EOF ){
		c = tolower(c);
		if((c >= 97 && c <= 122) && lineNum == 0 ){
			fputc(c, pstream);
			check = 1;
		}else if((c >= 97 && c <= 122) && lineNum == 1 ){
			fputc(c, p1stream);
			check = 1;
		}else {
			if(check == 1 && lineNum == 0){
				fputc('\n', pstream);
				check = 0;
				lineNum = 1;
			}else if (check == 1 && lineNum == 1){
				fputc('\n', p1stream); 
				check = 0;
				lineNum = 0;
			}
		}
	}
	fclose(pstream);
	fclose(p1stream);
}

void mergesuppressor(int file, int file1){
	FILE *sstream[2];
	char nfile[2][1000];
	char cfile[31];
	int count = 0;
	int sd = 0; //sort displacement
	sstream[0] = fdopen(file, "r");
	sstream[1] = fdopen(file1, "r");
	fgets(nfile[1],1000,sstream[1]);

	while(fgets(nfile[sd],1000,sstream[sd])!=NULL){
		if(strcmp(nfile[sd], nfile[(sd+1)%2]) <= 0){
			if (strlen(nfile[sd]) -1 >2 && strlen(nfile[sd]) -1 < 31){
				if(strcmp(cfile, nfile[sd]) == 0){
					count++;
				}else if(count >= 1){
					printf("%-5d", count);
					fputs(cfile, stdout);
					count = 1;
					strcpy(cfile, nfile[sd]);
				}else{
					strcpy(cfile, nfile[sd]);
					count = 1;
				}
			}else if(strlen(nfile[sd]) -1 > 30){
				char *n_file;
				n_file = truncc(nfile[sd]);
				if(strcmp(cfile, n_file) == 0){
					count++;
				}else if (count >=1){
					printf("%-5d", count);
					fputs(cfile, stdout);
					count = 1;
					strcpy(cfile, n_file);
				}else{
					strcpy(cfile, n_file);
					count = 1;
				}		
			}
		}
		else{
			if(strlen(nfile[(sd+1)%2])-1 > 2 && strlen(nfile[(sd+1)%2]) -1 < 31){
				if(strcmp(cfile, nfile[(sd+1)%2]) == 0){
					count++;
					sd = (sd+1)%2;
				}else if(count >= 1){
					printf("%-5d", count);
					fputs(cfile, stdout);
					count = 1;
					strcpy(cfile, nfile[(sd+1)%2]);
					sd = (sd+1)%2;
				}else{
					strcpy(cfile, nfile[(sd+1)%2]);
					count = 1;
					sd = (sd+1)%2;
				}
			}else if( strlen(nfile[(sd+1)%2]) -1 > 30){
				char *n_file;
				n_file = truncc(nfile[(sd+1)%2]);
				if(strcmp(cfile, n_file) == 0){
					count++;
					sd = (sd+1)%2;
				}else if (count >= 1){
					printf("%-5d", count);
					fputs(cfile, stdout);
					count = 1;
					strcpy(cfile, n_file);
					sd = (sd+1)%2;
				}else{
					strcpy(cfile, n_file);
					count = 1;
					sd = (sd+1)%2;
				}
			}else if(strlen(nfile[(sd+1)%2]) -1 < 3){
				sd = (sd+1)%2;
			}
		}
	}
	
	sd = (sd+1)%2;
	if(strlen(nfile[sd])-1 > 2 && strlen(nfile[sd]) -1 < 31){
		if(strcmp(cfile, nfile[sd]) == 0){
			count++;
		}else if (count >= 1){
			printf("%-5d", count);
			fputs(cfile, stdout);
			count = 1;
			strcpy(cfile, nfile[sd]);
		}
	}else if(strlen(nfile[sd])-1 > 30){
		char *n_file;
		n_file = truncc(nfile[sd]);
		if(strcmp(cfile, n_file) == 0){
			count++;
		}else if (count >= 1 ){
			printf("%-5d", count);
			fputs(n_file, stdout);
			count = 1;
			strcpy(cfile, nfile[sd]);
		}
	}

	while(fgets(nfile[sd], 1000, sstream[sd])!= NULL){ //any unfinished sort section.
		if(strlen(nfile[sd])-1 > 2 && strlen(nfile[sd]) -1 < 31){
			if (strcmp(cfile, nfile[sd]) == 0){
				count++;
			}else if (count >= 1){
				printf("%-5d", count);
				fputs(cfile, stdout);
				count = 1;
				strcpy(cfile, nfile[sd]);
			}else{
				strcpy(cfile, nfile[sd]);
				count = 1;
			}

		}else if(strlen(nfile[sd])-1 >30){
			char *n_file;
			n_file = truncc(nfile[sd]); //get 30char and truncate rest
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

	if(strlen(cfile)-1 > 2 && strlen(cfile)-1 < 31 && count >= 1){
		printf("%-5d", count);
		fputs(cfile, stdout);
	}
	
	fclose(sstream[0]);
	fclose(sstream[1]);
}

char *truncc(char *name){
	char *fname;
	fname = malloc(16*sizeof(char));
	int i;
	for(i=0; i<30; i++){
		if(name[i] != '\n'){ fname[i] = name[i]; }
	}
	fname[30] = '\n';
	return fname;
}