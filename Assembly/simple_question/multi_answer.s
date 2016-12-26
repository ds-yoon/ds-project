* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
* OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
* 
* 
*
	xdef Start, Stop, End
	xdef Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10
	xdef A, B, C
	xdef i, j, k
	xdef head
	xdef ans_b, ans_w, ans_l

Start:
*******************************************************************
* Put your assembler instructions here
* Write the answer to each question after the corresponding label.
* DO NOT REMOVE ANY LABEL IN THIS ASSIGNMENT (all labels are now NECESSARY)
* *** Failure to do so will result in point dedections !!! ***
*******************************************************************

Q1:
*         ans_l = i
	move.b i, d0
	ext.w d0
	ext.l d0
	move.l d0, ans_l     
	

Q2:
*         ans_l = B[8];
	movea.w #B, a0
	move.w 16(a0), d0
	ext.l d0
	move.l d0, ans_l




Q3:
*         ans_l = C[k];
	movea.l #C, a0
	move.l k, d0
	muls #4, d0
	move.l 0(a0,d0.l), ans_l
	



Q4:
*         ans_w = A[i + j];     
	move.l #A, a0
	move.w j, d0
	move.b i, d1
	ext.w d1
	add.w d1,d0
	muls #1, d0
	move.b 0(a0,d0.w), d7
	ext.w d7
	move.w d7, ans_w



Q5:
*         ans_w = C[j + k];      
	movea.l #C, a0
	move.l k, d0
	move.w j, d1
	ext.l d1
	add.l d1,d0
	muls #4, d0
	ext.l d7
	move.l 0(a0,d0.w), d7
	move.w d7, ans_w



Q6:
*         ans_l = A[i] + B[k];  
	movea.l #A, a0
	movea.l #B, a1
	move.b i, d0
	move.b 0(a0,d0.w), d7
	ext.w d7
	move.l k, d1
	muls #2, d1
	move.w 0(a1, d1.w), d6
	add.w d6, d7
	ext.l d7
	move.l d7, ans_l



Q7:
*         ans_l = A[A[k] - 50];      
	movea.l #A, a0
	move.l k, d0
	muls #1, d0
	move.b 0(a0,d0.l), d7
	sub.b #50, d7
	ext.w d7
	ext.l d7
	move.b 0(a0,d7.l), d6
	ext.w d6
	ext.l d6
	move.l d6, ans_l
 


	
Q8:
*         ans_w = B[ 15 ]
	movea.l #B, a0
	move.w 30(a0), d0
	move.w d0, ans_w
	* The end of address B starts with beginning of address of C. When the value of address go over the given address of B, it starts reading address from C. 
	* What it does is that it reads 2 bytes and computes as a short value. from the 4 byte system of C address, it takes first 2 bytes and convert into short binary system and gives the value. 
	* 3333 can be written in 2 byte system and therefore, it reads 3333, however, when it gets beyond 2 byte number , it takes half binary number and compute as a short system. 
	* for instance, if C[2] was given 33333333, than B[15] would give a055(Hex) and convert that into 2 byte short number system giving negetive number not 33333333. 
	* to conclude, the address continues after B with C address, but when it is computed with B[], it takes 2 bytes from each address and convert it into short value.  




Q9:
*	  ans_l = head.value2;
	movea.l head, a0
	move.w 4(a0), d0
	ext.l d0
	move.l d0, ans_l


Q10:
*	  ans_w = head.next.next.value1;
	movea.l head, a0
	movea.l 6(a0), a0
	movea.l 6(a0), a0
	move.w 2(a0), d0
	move.w d0, ans_w


*************************************************
* Don't write any code below this line
*************************************************

Stop:	nop
	nop

*************************************************
* Don't touch these variables
* You do NOT need to define more variables !!!
*************************************************

ans_b: ds.b 1
	even
ans_w: ds.w 1
ans_l: ds.l 1

i:     dc.b  2
************************************************************************
* We need to add a 1 byte dummy variable to make the next variable j
* locate on an EVEN address.
* Due to some architectural constraints, short and int variables MUST
* start on an even address (or you will get an "odd address" error
************************************************************************
	even

j:   dc.w  3
k:   dc.l  4

A:  dc.b   11, -22, 33, -44, 55, -66, 77, -88, 99, -123

B:  dc.w   111, -222, 333, -444, 555, -666, 777, -888, 999, -5432

C:  dc.l   1111, -2222, 3333, -4444, 5555, -6666, 7777, -8888, 9999, -9876


head:   dc.l  list1

list3:  dc.l 3456
        dc.w 67
	dc.l list4
list2:  dc.l 2345
        dc.w 78
	dc.l list3
list4:  dc.l 4567
        dc.w 56
	dc.l list5
list1:  dc.l 1234
        dc.w 89
	dc.l list2
list5:  dc.l 5678
        dc.w 45
	dc.l 0


End:
	end

