* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
* OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
* 
* 
* ********************************************************************
* Do not touch the following 2 xdef lists:
* ********************************************************************
        xdef Start, Stop, End
        xdef A, B, GCD

* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
* You can add more xdef here to export labels to emacsim
* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
*      Put your assembler program here - between the start and stop label
*      DO NOT define any variables here - see the variable section below
* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Start:

	move.l A, d0
	move.l B, d1
	cmp.l d0, d1
	ble L1
	move.l d0, d7
	move.l d1, d0
	move.l d7, d1

L1:
	divs d1, d0
	swap d0
	move.w d0, d2
	ext.l d2
Loop: 	
	cmp.l #0, d2
	beq Loop_end
	move.l d1, d0
	move.l d2, d1
	divs d1, d0
	swap d0
	move.w d0, d2
	ext.l d2
	bra Loop

Loop_end: move.l d1, GCD












* ********************************************************************
* Don't touch the stop label - you need it to stop the program
* ********************************************************************
Stop:   nop



* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
*    Variable Section -   Put your variables here IF you need more
*
*    DO NOT define A, B and GCD !!!
*    They are already defined below
*
* You can add more variables below this line if you need them
* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++






* ********************************************************************
* XXXX Don't touch anything below this line !!!
* ********************************************************************
A:      dc.l  168
B:      dc.l  651
GCD:    ds.l  1

End:    nop
        end
