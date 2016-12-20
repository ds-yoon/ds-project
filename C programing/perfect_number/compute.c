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
message msg;
int p;

void terminate(int signum){
	//initialize compute process of p
	(*shm).process[p].pid = 0;
	(*shm).process[p].num_tested = 0;
	(*shm).process[p].num_skipped = 0;
	(*shm).process[p].num_found = 0;
	exit(0);
}

int main(int argc, char *argv[]){
	if (argc != 2){
		printf("Usage : ./compute [starting number] \n");
		exit(1);
	}
	// initialize signal
	struct sigaction action;
	action.sa_handler = terminate;
	sigaction(SIGINT,&action,NULL);
	sigaction(SIGHUP,&action,NULL);
	sigaction(SIGQUIT,&action,NULL);

	int start_num = atoi(argv[1]);

	if (start_num > BITMAP_MAX || start_num < 2){ //ask for valid starting number
		printf("Starting number should be between 2 and %d\n", BITMAP_MAX);
		exit(1);
	}

	int i, j, sum;
	//share memory and message queue
	sid = shmget(KEY, sizeof(shared_memory), 0);
	mid = msgget(KEY, 0);
	shm = shmat(sid, NULL, 0);

	msg.type = 1;
	msg.data.pid = getpid();
	msg.data.text = start_num -1;

	msgsnd(mid, &msg, sizeof(msg.data), 0);
	msgrcv(mid, &msg, sizeof(msg.data), getpid(), 0);
	p = msg.data.text;

	for (i = start_num; i < BITMAP_MAX; i++){ // get perfect nums
		if( (*shm).bitmap[i-2] == 0){		
			sum = 1;
			for (j = 2; j < i; j++)
				if(!(i%j)) sum += j;

			(*shm).process[p].num_tested++;
			(*shm).t_test++;

			if (sum == i){ 			//number of perfect number found
				(*shm).process[p].num_found++; 
				msg.type = 2;
				msg.data.pid = getpid();
				msg.data.text = i;
				(*shm).bitmap[i-2] = 1;
				msgsnd(mid, &msg, sizeof(msg.data) , 0);
			}
		}else{
			(*shm).process[p].num_skipped++;
			(*shm).t_skip++;
		}
	}
	terminate(SIGQUIT);		   //compute done
	kill((*shm).mid, SIGQUIT); //message manage compute is done
}
