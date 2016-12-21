/* DongSun Yoon
 * Problem 2.
 * 
 * THIS CODE IS MY OWN WORK.
 * IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.
 * _DONG_SUN_YOON_
 */
import java.sql.*;
import java.util.*;


public class DongSunYoonProb2 {
	public static void main (String[] args) throws Exception{
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "imdb";
		String userName = "root";
		String password = "MyPassword";
		System.out.println("Dong Sun Yoon" + "\n" + "Problem 2" + "\n" + "THIS CODE IS MY OWN WORK." + "\n" + "IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS." + " \n" + "_DONG_SUN_YOON_");
		Connection conn = null;
	
		conn = DriverManager.getConnection(url+dbName,userName,password);
		
		if (args.length %2 != 0 ){
			System.out.print("Input must have both fname and lname of the actors");
			conn.close();
		}
		
		String[] fname = new String[args.length/2];
		String[] lname = new String[args.length/2];
				
		int j=0;
		for (int i = 0; i<args.length; i+=2){
			fname[j] = args[i];
			lname[j] = args[i+1];
			j++;
		}
		

	

		Statement stmt = conn.createStatement();
		
		for (int i = 0; i<fname.length; i++){
			
			ResultSet rset = stmt.executeQuery(
					"select distinct actor.id from actor where actor.fname = '"+fname[i]+"' and actor.lname = '"+lname[i]+"'"
					);
			if (!rset.next()){
				System.out.println("actor named : \"" + fname[i] + " " + lname[i] + "\" does not exist");
				rset.close();
				stmt.close();
				conn.close();
			}
		}
		
		ArrayList<Integer> mid = new ArrayList<Integer>();
		
		for (int i = 0; i<fname.length; i++){
			
			ResultSet rset = stmt.executeQuery(
					"select distinct movie.id from movie, casts, actor where movie.id = casts.mid and casts.aid = actor.id and actor.fname = '"+fname[i]+"' and actor.lname = '"+lname[i]+"'"
					);
		
			while(rset.next()){
				
				mid.add(rset.getInt("movie.id"));
			}
		
			
		}

		ArrayList<Integer> dupMid = new ArrayList<Integer>();
	

		
		System.out.println("-----------------------------");

		int cnt = fname.length;
		
		Map<Integer, Integer> count = new HashMap<Integer, Integer>();

		for (int i : mid) {
		    if (count.containsKey(i)) {
		        count.put(i, count.get(i) + 1);
		    } else {
		        count.put(i, 1);
		    }
		}
		
		for (Map.Entry<Integer, Integer> input : count.entrySet()) {
		    if(input.getValue().equals(cnt)){
		    	dupMid.add(input.getKey());
		    }
		}
		
		
		if(dupMid.isEmpty()){
			System.out.println("No results has been found!!");
			System.out.println("-----------------------------");
		}
		
		else{
			System.out.println("Total number of movies: " + dupMid.size());
			System.out.println("-----------------------------");
		}
		
		for (int i = 0; i<dupMid.size(); i++){
			
			ResultSet rset = stmt.executeQuery(
					"select distinct * from movie where movie.id = "+dupMid.get(i)+" order by movie.year desc"
					);
			
			while(rset.next()){
				System.out.println(rset.getString("movie.name") + " ; " + rset.getString("movie.year"));
			}
					
		
		}
		stmt.close();
		conn.close();
	}
}
