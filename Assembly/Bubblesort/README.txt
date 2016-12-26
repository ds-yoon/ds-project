/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Assignment given in the class CS255: Computer Organization & Architecture at Emory University 

Instructor: Prof. Cheung
Program: M68000 assembler programming
Used file:	bubblesort.s
		egt.main //provided by Prof.Cheung for debugging
		main.s  //basic instruction provided by Prof.Cheung


Overview:

Bubble sort algorithm in M68000 assembler language. The following c program was used as a guide line for assembler language:

void BubbleSort(int[] A, int N)
   {
       Done = 0;             // 0 represents false
       k = 1;

       while (Done == 0) 
       {  
          Done = 1;          // 1 represents true.

          for (i = 0; i < N-k; i++)
          { 
             if (A[i] > A[i+1])
             { 
	         Help = A[i];
                 A[i] = A[i+1];
                 A[i+1] = Help;
                 Done = 0;          // Not sorted...
	     }
	  }
          k = k + 1;
       }
   }

The main.s program call the bubblesort.s to sort two given arrays of A[5] and B[10]. More array to be sorted can be written in the main.s program. 
