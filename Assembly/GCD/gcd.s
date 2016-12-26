* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
* OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
* 
* 
        xdef Start, Stop, End
        xdef A, B, GCD

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


Stop:   nop

A:      dc.l  168
B:      dc.l  651
GCD:    ds.l  1

End:    nop
        end
