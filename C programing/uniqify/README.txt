/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

Assignment given in the class CS450: Systems Programming at Emory University 

Instructor : Prof. K. Mandelberg
Used OS: Unix 
Language: C
Used file: uniqify.c
	   uniqify_extra.c


Overview:

This program receives given txt files and retrieves characters in the txt files until stopped by non-char values. It converts every char to lowercase, and truncate if the size of the char is greater than 30 char. The program uses pipe and fork method to retrieve data from file and exec system call to sort in alphabetic order. Result indicates how many times the word is included in the text file and listed out in alphabetical order. 
The first C program file uniqify.c uses single pipeline to conduct the result. The second C program file uniqify_extra.c uses two pipeline to separate even and odd line before combining into one result. 


Instructions:

To run the program, conduct a.out file from terminal then process by < followed by txt file that needs to be sorted. Both uniqify.c and uniqify_extra.c generates same result. The difference comes within using different number of pipeline. The result will be indicated in the terminal. The number value means the amount of the word displayed in the txt file. 

Sample Output:
with input text file:
    aaaaAAA!@#!bbbbbbB1124jsidjfo123jooowi120cccc123124cccc123aaaaAAA!@#!bbbbbbB1124jsidjfo123jooowi12dddddddddddddddddddddddddddddd!@#!230cccc123124cccc123aaaaAAA!@#!bbbbbbB1124jsidjfo123jooowi120cccc123124cccc123ddddddddddddddddddddddddddddddddddddddddddddddd!$!$%)ddddddddddddddddddddddddddddddddddddddddddddddd!$!$%)ddddddddddddddddddddddddddddddddddddddddddddddd!$!$%)ddddddddddddddddddddddddddddddddddddddddddddddd!$!$%)dddddddddddddddddddddddddddddd!@#!23aa!@#ab.,.ooo./oo.,pp;

Output: 
3    aaaaaaa
3    bbbbbbb
6    cccc
6    dddddddddddddddddddddddddddddd
3    jooowi
3    jsidjfo
1    ooo
