/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR   
 OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon
*/
 
class List{
   int  value;
   List next;
}

public class HelpList{
   public static List InsertList(List h, List e) {
	List help= null;	
	if (e.next == null)
		return e;
	else{
		if (h.value > e.value){
			h = help;
			h = e;	
			e = help;
			return InsertList(h, e);
		}
		else
			return InsertList(h, e);
	}
}

public static void main(String[] args){
   int[] v = {30, 50, 10, 40, 20};
   List head = null;
   List elem;
   for ( int i = 0; i < v.length; i++ ){
         elem = new List(); 
         elem.value = v[i];
         head = InsertList(head, elem);
         System.out.print("List = ");
         PrintList( head );
         System.out.println();
   }
}

public static void PrintList(List h) {
      if ( h == null )
         return;
      else
      {
	 System.out.print(h.value + " ");
	 PrintList(h.next);
      }
   }
}

