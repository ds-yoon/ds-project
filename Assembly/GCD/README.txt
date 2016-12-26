/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Assignment given in the class CS255: Computer Organization & Architecture at Emory University 

Instructor: Prof. Cheung
Program: M68000 assembler programming
Used file:	gcd.s
		        egt.gcd //provided by Prof.Cheung for debugging


Overview:

Euler algorithm in M68000 assembler language determining the GCD of the two integer values. The following c program was used as a guide line for assembler language:

int A, B, GCD;
if (B>A) {
	X = A;
	A = B;
	B = X;
}
X= A%B;
while (X != 0){
	A = B;
	B = X;
	X = A%B;
}
GCD = B;
