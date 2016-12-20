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

void terminate (int signum){
	int i; 
	printf("Terminating all running process\n");
	//SIGINT signal for every compute process
	for (i = 0; i < 20; i++){
		if((*shm).process[i].pid != 0){
			kill((*shm).process[i].pid, SIGINT);
		}
	}

	sleep(5); 
	shmdt(shm);
	shmctl(sid, IPC_RMID, 0);
	msgctl(mid, IPC_RMID, NULL);
	printf("Terminatation finished \n");
	exit(0);
}

int main(int argc, char *argv[]){
	
	struct sigaction action;
	action.sa_handler = terminate;
	sigaction(SIGINT,&action,NULL);
	sigaction(SIGHUP,&action,NULL);
	sigaction(SIGQUIT,&action,NULL);

	
	printf("Run compute\n");

	sid = shmget(KEY, sizeof(shared_memory), IPC_CREAT | IPC_EXCL | 0666);
	shm = shmat(sid, NULL, 0);
	mid = msgget(KEY, IPC_CREAT | 0666);
	//initialize total num
	int i;
	int perf_num = 0;
	(*shm).mid = getpid();
	(*shm).t_test = 0;
	(*shm).t_skip = 0;
	(*shm).t_found = 0;

	while(1){ 
		msgrcv(mid, &msg, sizeof(msg.data), -3, 0); // wait for compute process
		if (msg.type == 1){ 
			printf("Compute process Registered\n");
			for(i =0; i< 20; i++){ 
				if (!(*shm).process[i].pid){
					(*shm).process[i].pid = msg.data.pid;
					(*shm).process[i].num_tested = 0;
					(*shm).process[i].num_found = 0;
					(*shm).process[i].num_skipped = msg.data.text;
					(*shm).t_skip += msg.data.text;
					break;						
				}
			}	
			msg.type = msg.data.pid;
			msg.data.text = i;
			msgsnd(mid, &msg, sizeof(msg.data), 0);
		}else if(msg.type == 2){ //perfect number found
			for (i = 0; i< 20; i++){
				if(!(*shm).p_num[i]){
					(*shm).p_num[i] = msg.data.text;
					perf_num++;
					break;
				}
			}
		}
	}
	terminate(SIGQUIT);
}