/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Practice Exam given in the class CS255: Computer Organization & Architecture at Emory University 

Instructor: Prof. S.Y. Cheung
Program: M68000 assembler programming
Used file:	QuickSort.s
		 

Overview:

Recursive QuickSort algorithm in M68000 assembler language that is based on the following java recursive QuickSort function provided by Prof. S.Y.Cheung: 

void QuickSort(int X[], int n1, int n2){
	int pivot, curr, right_array_spot;

	if(n1 < n2){
		pivot = X[n2];
		right_array_spot = n2;
		curr = n1;

		while (curr != right_array_spot){
			if (X[curr] <= pivot){ curr++; }
			else{
				X[right_array_spot] = X[curr];
				X[curr] = X[right_array_spot -1];
				right_array_spot--;
			}
		}

		X[curr] = pivot;
		QuickSort(X, n1, curr-1);
		QuickSort(X, curr+1, n2);
	}
}

