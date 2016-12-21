/* DongSun Yoon
 * Problem 1.
 * 
 * THIS CODE IS MY OWN WORK.
 * IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.
 * _DONG_SUN_YOON_
 */
import java.sql.*;

public class DongSunYoonProb1 {
	public static void main (String[] args) throws Exception{
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "imdb";
		String userName = "root";
		String password = "MyPassword";
		System.out.println("Dong Sun Yoon" + "\n" + "Problem 1" + "\n" + "THIS CODE IS MY OWN WORK." + "\n" + "IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS." + " \n" + "_DONG_SUN_YOON_");
		Connection conn = null;
		conn = DriverManager.getConnection(url+dbName,userName,password);
		
		Statement stmt = conn.createStatement();
		
		ResultSet rset = stmt.executeQuery("select * from movie, casts , actor where movie.id = casts.mid and casts.aid = actor.id and actor.fname = 'Matt' and actor.lname = 'Damon' order by year desc limit 10");
		
		while (rset.next()){
			System.out.println(rset.getString("movie.name") + " ; " + rset.getString("casts.role")+ " ; " + rset.getString("movie.year"));
		}
		rset.close();
		stmt.close();
		conn.close();
	}
}
