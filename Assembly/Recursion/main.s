* ====================================================================
* Do not touch the following 2 xdef lists:
* ====================================================================
        xdef Stop1, Stop2, Stop3, Stop4
        xdef Start, Stop, End
	xdef ans
	xref F

Start:
		move.l #7, -(a7)		
		move.l #4, -(a7)
		move.l #5, -(a7)
		bsr F
		adda.l #12, a7
		move.l d0, ans
Stop1:

		move.l #5, -(a7)
		move.l #-4, -(a7)
		move.l #5, -(a7)
		bsr F
		adda.l #12, a7
		move.l d0, ans
Stop2:

		move.l #5, -(a7)
		move.l #4, -(a7)
		move.l #3, -(a7)
		bsr F
		adda.l #12, a7
		move.l d0, ans
Stop3:

		move.l #2, -(a7)
		move.l #2, -(a7)
		move.l #2, -(a7)
		bsr F
		adda.l #12, a7
		move.l d0, ans
Stop4:

		move.l #5, -(a7)
		move.l #5, -(a7)
		move.l #5, -(a7)
		bsr F
		adda.l #12, a7
		move.l d0, ans

Stop:   nop
        nop

ans:	ds.l  1


End:
        end
