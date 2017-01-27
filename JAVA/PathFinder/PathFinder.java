import java.io.*;
import java.util.Scanner;
import java.util.ArrayDeque;

/*
 * Recursive class to represent a position in a path
 */
class Position{
	public int i;     //row
	public int j;     //column
	public char val;  //1, 0, or 'X'
	
	// reference to the previous position (parent) that leads to this position on a path
	Position parent;
	
	Position(int x, int y, char v){
		i=x; j = y; val=v;
	}
	
	Position(int x, int y, char v, Position p){
		i=x; j = y; val=v;
		parent=p;
	}
	
}


public class PathFinder {
	
	public static void main(String[] args) throws IOException {
		if(args.length<1){
			System.err.println("***Usage: java PathFinder maze_file");
			System.exit(-1);
		}
		
		char [][] maze;
		maze = readMaze(args[0]);
		printMaze(maze);
		Position [] path = stackSearch(maze);
		System.out.println("stackSearch Solution:");
		printPath(path);
		printMaze(maze);
		
		char [][] maze2 = readMaze(args[0]);
		path = queueSearch(maze2);
		System.out.println("queueSearch Solution:");
		printPath(path);
		printMaze(maze2);
	}
	
	
	public static Position [] stackSearch(char [] [] maze){
		ArrayDeque<Position> path = new ArrayDeque<Position>();	
		Position initial = new Position(0, 0, '0');
		path.push(initial);
		
		while (!path.isEmpty()){
			
			Position p = path.pop();
			Position up = new Position(p.i-1, p.j, '0', p);
			Position down = new Position(p.i+1, p.j, '0', p);
			Position right = new Position(p.i, p.j+1, '0', p);
			Position left = new Position(p.i, p.j-1, '0', p);

			//up
			if (p.i-1 >= 0 && p.i-1 < maze.length && maze[p.i-1][p.j] == '0'){
				path.push(up);
			}
			//down
			if (p.i+1 > 0 && p.i+1 < maze.length && maze [p.i+1][p.j] == '0'){
				path.push(down);
			}
			//right
			if (p.j+1 > 0 && p.j+1 < maze.length && maze[p.i][p.j+1] == '0'){
				path.push(right);
			}
			//left
			if (p.j-1 >= 0 && p.j-1 < maze.length && maze[p.i][p.j-1] == '0'){
				path.push(left);
			}
									
			maze[p.i][p.j] = '*';
								
			if (p.i == maze.length-1 && p.j == maze.length-1){
				Position [] mark = new Position[1000];
				Position coordinates = p.parent;
				maze[p.i][p.j] = 'X';
				int i = 0;
				mark[i] = p;
				while (coordinates != null){
					i++;					
					maze[coordinates.i][coordinates.j] = 'X';
					mark[i] = coordinates;
					coordinates = coordinates.parent;
				}
				return mark;
				}
			}

		return null;
	}
	
	public static Position [] queueSearch(char [] [] maze){	
		ArrayDeque<Position> path = new ArrayDeque<Position>();	
		Position initial = new Position(0, 0, '0');
		path.push(initial);
	
		while (!path.isEmpty()){
		
			Position p = path.removeLast();
			Position up = new Position(p.i-1, p.j, '0', p);
			Position down = new Position(p.i+1, p.j, '0', p);
			Position right = new Position(p.i, p.j+1, '0', p);
			Position left = new Position(p.i, p.j-1, '0', p);

			//up
			if (p.i-1 >= 0 && p.i-1 < maze.length && maze[p.i-1][p.j] == '0'){
				path.addFirst(up);
			}
			//down
			if (p.i+1 > 0 && p.i+1 < maze.length && maze [p.i+1][p.j] == '0'){
				path.addFirst(down);
			}
			//right
			if (p.j+1 > 0 && p.j+1 < maze.length && maze[p.i][p.j+1] == '0'){
				path.addFirst(right);
			}
			//left
			if (p.j-1 >= 0 && p.j-1 < maze.length && maze[p.i][p.j-1] == '0'){
				path.addFirst(left);
			}
								
			maze[p.i][p.j] = '*';
			
			if (p.i == maze.length-1 && p.j == maze.length-1){
				Position [] mark = new Position[100];
				Position coordinates = p.parent;
				maze[p.i][p.j] = 'X';
				int i = 0;
				mark[i] = p;
				while (coordinates != null){
					i++;					
					maze[coordinates.i][coordinates.j] = 'X';
					mark[i] = coordinates;
					coordinates = coordinates.parent;
				}
				return mark;
			}
		}

		return null;
	}
	
	public static void printPath(Position [] path){
		if (path != null){
			for(int i = path.length - 1; i >=0; i--){
				if(path[i] != null)
					System.out.print("["+ path[i].i + ","  + path[i].j + "]");
			}
			System.out.println(); //to show starting point. 
		}
		else
			System.out.println("There is no valid path");
		// todo: print the path to the stdout0
	}
	
	/**
	 * Reads maze file in format:
	 * N  -- size of maze
	 * 0 1 0 1 0 1 -- space-separated 
	 * @param filename
	 * @return
	 * @throws IOException
	 */

	public static char [][] readMaze(String filename) throws IOException{
		char [][] maze;
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("*** Invalid filename: " + filename);
			return null;
		}
		
		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		int i=0;
		while(i < N && scanner.hasNext()){
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j!=N){
				System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
				return null;
			}
			i++;
		}
		if(i!=N){
			System.err.println("*** Invalid file: has wrong number of rows: " + i);
			return null;
		}
		return maze;
	}
	
	public static void printMaze(char[][] maze){
		
		if(maze==null || maze[0] == null){
			System.err.println("*** Invalid maze array");
			return;
		}
		
		for(int i=0; i< maze.length; i++){
			for(int j = 0; j< maze[0].length; j++){
				System.out.print(maze[i][j] + " ");	
			}
			System.out.println();
		}
		
		System.out.println();
	}

}
