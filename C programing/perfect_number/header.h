/*THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS Dong Sun Yoon */

#define KEY (key_t) 40842
#define BITMAP_MAX 1048576

typedef struct{  //process 
	int pid;
	int num_tested;
	int num_skipped;
	int num_found;
	int num_nottested;
}process_struct;

typedef struct { 	//shared memory
	int bitmap[BITMAP_MAX]; 
	int p_num[20]; 	// list of perfect number 
	process_struct process[20];
	int mid; 		//manage PID
	int t_test; 	//total number teseted
	int t_skip; 	//total number skipped
	int t_found; 	//total number found 
}shared_memory;

struct msg_data{
	int pid;
	int text;
};

typedef struct {
	long type;
	struct msg_data data;
}message;
