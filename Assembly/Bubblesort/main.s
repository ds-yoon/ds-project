
        xdef Start,Start2,Stop,End
        xdef A,B

        xref BubbleSort
	xref WriteInt,WriteLn

*****************************************************************************
* Main program: call BubbleSort twice to sort 2 different arrays
*	 	and print the sorted arrays out
*****************************************************************************
Start:
	move.l #A,D0			* Pass address of the array in D0
	move.l #5,D1			* Pass size of the array in D1
	jsr    BubbleSort		* Sort array A


Start2:
	nop

        move.l #B,D0			* Pass address of the array in D0
        move.l #10,D1			* Pass size of the array in D1
        jsr    BubbleSort		* Sort array B


Stop:   nop
        nop

A:      dc.l 6,-3,-8,4,11
B:      dc.l 78,-1,8,45,11,-89,56,9,12,-19

End:

        end

