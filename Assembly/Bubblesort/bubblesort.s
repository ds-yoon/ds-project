* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
* OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
* 
*       
	 xdef BubbleSort


BubbleSort:

	move.l #0, Done
	move.l #1, k
while_start:
	move.l Done, d2
	cmp.l #0, d2
	bne while_end
	add.l #1, d2
	move.l d2, Done
	move.l k, d3
for_while:
	move.l i, d4
	move.l d1, d5
	sub.l d3, d5		*N-k
	cmp.l d5, d4		* i against N-k
	bge for_exit
	muls #4, d4
	add.l d0, d4
	movea.l d4, a0
	move.l (a0), d7		*A[i] = d7
	move.l 4(a0), d6	*A[i+1] = d6
	cmp.l d6, d7		*A[i] against A[i+1]
	ble if_end
	move.l d6, (a0)		
	move.l d7, 4(a0)	*swap A[i] and A[i+1]
	move.l #0, Done
if_end:
	move.l i, d4
	add.l #1, d4
	move.l d4, i		*i++
	bra for_while
for_exit:
	move.l k, d3
	add.l #1, d3
	move.l d3, k		* k= k+1
	move.l #0, i		*reset i
	bra while_start
while_end:
	move.l #0, i	
	move.l #0, Done
	move.l #1, k
	rts

Done:	dc.l 0
k:	dc.l 1
i:	dc.l 0



        end
