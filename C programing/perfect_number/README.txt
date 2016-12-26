/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Assignment given in the class CS450: Systems Programming at Emory University 

Instructor: Prof. K. Mandelberg
Used OS: Unix 
Language: C
Used file:	compute.c
		manage.c
		report.c
		header.h


Overview:

The program is based on the shared memory system using three programs compute.c, manage.c, and report.c to store result of perfect number and its information in shared memory. Compute.c computes perfect numbers testing all numbers starting at the point indicated in the command line. Manage.c maintains the shared memory segment that computes processes provides results tracking active compute systems. Report.c read from the shared memory segment and prints the result of found perfect numbers and computing process until invoked with –k in command line which will report the total number of compute information and inform manage.c to shut down everything. 

Instructions:

They way to run program is to make a compiler for compute.c, manage.c, and report.c then run manage compiler first before running any other program. There is no need to call any other command line other than manage compiler itself to begin than call compute compiler with the number indicating the starting point to look for perfect number. Run report compiler to see the current compute processes and finally prompt –k to terminate every process and get total found perfect numbers and total information regarding number of tested, skipped and found

Sample Output:
$ manage &
Run compute
$ compute 1000 &
Compute process Registered
$ compute 100 &
Compute process Registered
$ ./report
Found Perfect numbers {   8128,  496,  28,  }
Compute Processes:
Pid: 29161     Tested : 118095    Skipped : 999       Founded : 1
Pid: 29164     Tested : 105426    Skipped : 100       Founded : 1
Pid: 29172     Tested : 42045     Skipped : 11        Founded : 1
$ compute 10 &
Compute process Registered
$ ./report
Found Perfect numbers {   8128,  496,  28,  }
Compute Processes:
Pid: 29161     Tested : 118095    Skipped : 999       Founded : 1
Pid: 29164     Tested : 105426    Skipped : 100       Founded : 1
Pid: 29172     Tested : 42045     Skipped : 11        Founded : 1
$ ./report –k
Found Perfect numbers {   8128,  496,  28,  }
Total Computing Processes:
Total_Tested : 295353    Total_Skipped : 1110      Total_Founded : 3
Terminating all running process
Termination finished
