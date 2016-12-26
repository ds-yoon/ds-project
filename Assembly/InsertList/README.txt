/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Assignment given in the class CS255: Computer Organization & Architecture at Emory University 

Instructor: Prof. Cheung
Program: M68000 assembler programming
Used file:	HelpList.java 
		InsertList.s
		egt.main //provided by Prof.Cheung for debugging
		main.s   //basic instruction provided by Prof.Cheung
		 

Overview:

Recursive insert list algorithm in M68000 assembler language that maintains a sorted list. The list is in ascending order and the prototype of the assembly language is written in Java. First, the program was programmed in Java file HelpList.java, for the guideline to construct assembly. With the correct algorithm with an output:
   List = 30	     
   List = 30 50	     
   List = 10 30 50
   List = 10 30 40 50
   List = 10 20 30 40 50
Then, converted recursion function InsertList in Java to assembler language called InsertList.s and main function to main.s. This gives the same output when run by Egtapi.
