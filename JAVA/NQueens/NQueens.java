import java.util.Stack;

public class NQueens {
	public static Stack<Integer> s = new Stack<Integer>();
	public static int current = 0, sol = 0;
	 
	  //finds and prints out all solutions to the n-queens problem
	public static int solve(int n) {
		if (n == 2 || n == 3)
			return 0;
		nQueens (s,n);
		return sol;
		
	}	
	
	
	//Using Recursion.
	public static void nQueens(Stack<Integer> s, int n){
		if (s.size() == n){
			sol++;
//			printSolution(s);   //to see the results.
		}
		else
			for (int i = 0; i<n; i++){
				if (placeQueen(s, i)){
					s.push(i);
					nQueens(s, n);
					s.pop();
				}
			}
	}	
	//Eligibility of placing Queen.
	public static boolean placeQueen(Stack<Integer> s, int current){
		int a = s.size();
		for (int row = 0; row < s.size(); row++)
			if (s.get(row) == current || (a-row) == (current-s.get(row)) || (a-row) == (s.get(row)-current))
				return false;			
				return true;
	}
	
	//this method prints out a solution from the current stack

	  private static void printSolution(Stack<Integer> s) {
	    for (int i = 0; i < s.size(); i ++) {
	      for (int j = 0; j < s.size(); j ++) {
	        if (j == s.get(i))
	          System.out.print("Q ");
	        else
	          System.out.print("* ");
	      }
	      System.out.println();
	    }
	    System.out.println();  
	  }
	  

	  public static void main(String[] args) {
	  
	  int n = 8;
	  
	  // pass in parameter n from command line
	  if (args.length == 1) {
	    n = Integer.parseInt(args[0].trim());
	    if (n < 1) {
	      System.out.println("Incorrect parameter");
	      System.exit(-1);
	    }
	  }
	  
	  int number = solve(n);
	  System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
	 }
	  
	  
	  
}
