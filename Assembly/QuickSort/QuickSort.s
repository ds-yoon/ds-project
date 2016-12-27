* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
* OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
* 
*       
	 xdef QuickSort

QuickSort:

	move.l a6, -(a7)
	move.l a7, a6	
	suba.l #12, a7
	move.l 12(a6), d0 		*d0 = n1
	move.l 8(a6), d1		*d1 = n2
	cmp.l d1, d0			*n1 against n2
	bge if_end
	movea.l 16(a6), a0		*a0 has x[] address
	muls #4, d1
	move.l (a0, d1.w), d1	*d1 = x[n2]
	add.l d1, -4(a6)		*pivot = x[n2]
	move.l 8(a6), d0		*d0 = n2
	add.l d0, -12(a6)		*right_arr = n2;
	move.l 12(a6), d0		*d0 = n1
	add.l d0, -8(a6)		*curr = n1

while:

	move.l -8(a6), d0		*curr = d0
	move.l -12(a6), d1		*right = d1
	cmp d1, d0
	beq while_end
	muls #4, d0
	move.l (a0, d0.w), d0	*d0 = x[curr]
	move.l -4(a6), d2		*d2 = pivot
	cmp.l d2, d0			*x[curr] against pivot
	bgt else
	move.l -8(a6), d0
	add.l #1, d0
	move.l d0, -8(a6)		*curr++
	bra while

else:		

	move.l -12(a6), d0		*d0 = right
	move.l -8(a6), d1		*d1 = curr
	muls #4, d1
	muls #4, d0
	move.l (a0, d1.w), d3	*d3 = x[curr]
	move.l d3, (a0, d0.w)	*x[right] = x[curr]
	sub.l #1, d0			*d0 = right-1
	muls #4, d0
	move.l (a0, d0.w), d2	*d2 = x[right-1]
	move.l d2, (a0, d1.w)	*x[curr] = x[right]
	move.l -12(a6), d0
	sub.l #1, d0
	move.l d0, -12(a6)		* right--
	bra while

while_end:	

	move.l -4(a6), d0		*d0 = pivot
	move.l -8(a6), d1		*d1 = curr
	muls #4, d1
	move.l d0, (a0, d1.w)	*x[curr] = pivot
	sub.l #1, d1			*d1 = curr-1
	move.l 12(a6), d2
	move.l 0(a0), -(a7)
	move.l d2, -(a7)
	move.l d1, -(a7)
	bsr QuickSort
	move.l 16(a6), (a0)
	move.l -8(a6), d1
	add.l #1, d1
	move.l 8(a6), d2
	move.l 0(a0), -(a7)
	move.l d1, -(a7)
	move.l d2, -(a7)
	bsr QuickSort

	move.l a6, a7
	move.l (a7)+, a6	*reset stack
	rts

