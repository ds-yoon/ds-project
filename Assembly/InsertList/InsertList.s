* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
* OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
* 
*   
	xdef InsertList

InsertList:

* ********************************************************************
* Put your InsertList function here 
* ********************************************************************
	move.l a6, -(a7)
	move.l a7, a6
	move.l 12(a6), d0	*d0 = head
	cmp.l #0, d0		*head = null
	beq if
	move.l 12(a6), a0	*a0 = head
	move.l 8(a6), a1	*a1 = elem
	move.l 0(a0), d1	*d1 = head.value
	move.l 0(a1), d2	*d2 = elem.value
	cmp.l d2, d1
	blt if_else

if:
	move.l 12(a6), d0
	move.l 8(a6), a0
	move.l d0, 4(a0)	*elem.next = head
	move.l 8(a6), d0	*return elem
	move.l a6, a7
	move.l (a7)+, a6
	rts

if_else:
	move.l 12(a6), a0
	move.l 4(a0), -(a7)
	move.l 8(a6), -(a7)	
	bsr InsertList	
	adda.l #8, a7
	move.l 12(a6), a0
	move.l d0, 4(a0)	*head.next = InsertList(head.next, elem)
	move.l 12(a6), d0	*return head
	move.l a6, a7
	move.l (a7)+, a6
	rts










        end
