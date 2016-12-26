/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Assignment given in the class CS255: Computer Organization & Architecture at Emory University 

Instructor: Prof. Cheung
Program: M68000 assembler programming
Used file:	recursion.s
		egt.main //provided by Prof.Cheung for debugging
		main.s  //basic instruction provided by Prof.Cheung


Overview:

Recursion algorithm in M68000 assembler language. The following c function line was used as a guide line for assembler language:

int F(int i, int j, int k)
  {
     int s, t;

     if ( i <= 0 || j <= 0 )
        return(-1);
     else if ( i + j < k )
        return (i+j);
     else
     {
        s = 0;
        for (t = 1; t < k; t++)
        {
           s = s + F(i-t, j-t, k-1) + 1;               
        }
        return(s);
     }
}

The main.s program call the recursion.s by the command line bsr F to do the recursion process of different case values. The initial values are given by the command line move.l before recursion is being called. 

