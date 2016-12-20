/*THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS Dong Sun Yoon */

#include <sys/shm.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <setjmp.h>
#include <string.h>
#include <sys/msg.h>
#include "header.h" //included my own header file

int sid, mid;
shared_memory *shm; //shared memory
message msg; // message

void print(){ // print all current proccessing compute 
    int i;
    printf("Found Perfect numbers {  ");
    for(i=0; i<20; i++){ 
        if((*shm).p_num[i] != 0 ){
            printf(" %d, ", (*shm).p_num[i]);
        }
    }
    printf(" }\n");

    printf("Compute Processes:\n");
    for(i=0; i<20; i++){
        if((*shm).process[i].pid != 0){ 
            printf("Pid: %-10d", (*shm).process[i].pid);
            printf("Tested : %-10d", (*shm).process[i].num_tested);
            printf("Skipped : %-10d", (*shm).process[i].num_skipped);
            printf("Founded : %d\n",(*shm).process[i].num_found);
   		}
    }
}

void totalprint(){ // print total number of test, skip, found
	int i;
    printf("Found Perfect numbers {  ");
    for(i=0; i<20; i++){
        if((*shm).p_num[i] != 0 ){
            printf(" %d, ", (*shm).p_num[i]);
            (*shm).t_found++;
        }
    }
    printf(" }\n");

    printf("Total Computing Processes:\n");
    printf("Total_Tested : %-10d", (*shm).t_test);
    printf("Total_Skipped : %-10d", (*shm).t_skip);
    printf("Total_Founded : %d\n",(*shm).t_found);  
}

int main(int argc, char *argv[])
{
   	sid = shmget(KEY, sizeof(shared_memory), 0);
	shm = shmat(sid, NULL, 0);
	if(argc == 2 && strcmp(argv[1], "-k") == 0){
		totalprint();
		kill((*shm).mid, SIGINT);
	}
	else if(argc == 1){
		print();
	} else{
		printf ("Usage: ./report [-k]\n");
	}
}

