* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
* OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
* 
*   
* ====================================================================
* Do not touch the following xdef:
* ====================================================================
        xdef F



* **************************************************************************
* You can add more xdef directives above if you need more external labels
*
* - Remember that you can only add a label as a break point (stop location) 
*   in EGTAPI if the label has been xdef'ed !!!
*
* - And I am pretty sure you will need to use break point for debugging 
* - in this project... So add more xdef above to export your breakpoints
* **************************************************************************

F:

* ********************************************************************
* Put your (recursive) function F here 
*
* F receives the parameters i, j, k on the stack
* F returns the function value in register d0
*
* Make sure you use the "rts" instruction to return to the caller
* Also: Make sure you DE-ALLOCATE the local variables and restore a6
*       BEFORE you return to the caller !!!
* ********************************************************************
	move.l a6, -(a7)
	move.l a7, a6
	suba.l #8, a7
	move.l 16(a6), d0	*d0 = i
	move.l 12(a6), d1	*d1 = j
	cmp.l #0, d0
	ble if
	cmp.l #0, d1
	bgt else_if

if:
	move.l #-1, d0
	move.l a6, a7
	move.l (a7)+, a6
	rts

else_if:
	move.l d0, d7
	move.l d1, d6
	add.l d6, d7		*d0 = i+j
	move.l 8(a6), d2
	cmp.l d2, d7
	bge else
	move.l d7, d0
	move.l a6, a7
	move.l (a7)+, a6
	rts

else:
	move.l #0, -8(a6)	*s = 0
	move.l #1, -4(a6)	*t = 1
while:	
	move.l 8(a6), d2	*d2 = k
	move.l -4(a6), d4	*d4 = t
	cmp.l d2, d4		*t against k
	bge while_end
	move.l -4(a6), d4
	move.l 16(a6), d0
	move.l 12(a6), d1
	move.l 8(a6), d2
	sub.l d4, d0		*i-t
	sub.l d4, d1		*j-t
	sub.l #1, d2		*k-1
	move.l d0, -(a7)
	move.l d1, -(a7)
	move.l d2, -(a7)	
	bsr F
	adda.l #12, a7
	add.l #1, d0
	add.l d0, -8(a6)
	move.l -4(a6), d4	
	add.l #1, d4
	move.l d4, -4(a6)	*t++
	bra while

while_end:
	move.l -8(a6), d0
	movea.l a6, a7
	movea.l (a7)+, a6
	rts








*====================================================================
* Don't add anything below this line...
* ====================================================================
        end
