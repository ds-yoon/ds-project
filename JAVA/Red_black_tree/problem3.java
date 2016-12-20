//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
//A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Dong Sun Yoon

import java.util.*;
public class problem3{
	public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Insert value for N: ");
		int N = in.nextInt();
		System.out.print("Insert value for M: ");
		int M = in.nextInt();	
		System.out.print("How many times?: ");
		int O = in.nextInt();
		
		int avgCount = 0, avgMax = 0; 
		
		for (int i = 0; i<O; i++){
			int[] numList = new int[N];
			for (int j = 0; j<N; j++){
				numList[j] = (int)(Math.random()*M);
			}
			
			Arrays.sort(numList);
			int numCount[]=new int[M];
			
			for(int j:numList){
				numCount[j]= numCount[j]+1;
			}

			int count = 0;
			for (int j = 0; j<M; j++){
				if(numCount[j] == 0)
					count++;
				else
					continue;
			}	
			
			Arrays.sort(numCount);
			int max = numCount[numCount.length - 1];
			avgMax += max;
			avgCount += count;
		}
		System.out.println("Average length of Longest List: " + avgMax/O);
		System.out.println("Average number of Empty Lists : "+ avgCount/O);
	}
	
}
