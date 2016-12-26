/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Assignment given in the class CS255: Computer Organization & Architecture at Emory University 

Instructor: Prof. Cheung
Program: M68000 assembler programming
Used file:	multi_answer.s
		        egt.multi_answer //provided by Prof.Cheung for debugging


Overview:

Program consists of 10 sections of M68000 assembly code corresponding to 10 questions: 

1.	ans_l = i;      (ans: 2)
2.	ans_l = B[8];      (ans: 999)
3.	ans_l = C[k];      (ans: 5555)
4.	ans_w = A[i + j];      (ans: -66)
5.	ans_w = C[j + k];      (ans: -8888)
6.	ans_l = A[i] + B[k];      (ans: 33 + 555 = 588)
7.	ans_l = A[A[k] - 50];      (ans: A[55 - 50] = A[5] = -66)
8.	ans_w = B[15];
9.	ans_l = head.value2;      (ans: 89)
10.	ans_w = head.next.next.value1;      (ans: 3456)

with given variable definition. 

