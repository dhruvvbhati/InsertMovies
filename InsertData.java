package MoviesInfoTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

	public static void main(String[] args) {
		   
		InsertData app = new InsertData();
	       app.insert("KGF Chapter 1", "Yash", "Srinidhi Shetty", "Prashanth Neel", 2018);
	       app.insert("Swades", "Shahrukh Khan", "Gayatri Joshi", "Ashutosh Gowariker", 2004);
	       app.insert("Shershaah", "Sidharth Malhotra", "Kiara Advani", "Vishnuvardhan", 2021);
	       app.SelectSQL(); 
	    }
	
	 public void insert(String name, String actor, String actress, String director, Integer year) {
	        String sql = "INSERT INTO Movies(name,actor,actress,director,year) VALUES(?,?,?,?,?)";

	        try {
	        	Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, name);
	            pstmt.setString(2, actor);
	            pstmt.setString(3, actress);
	            pstmt.setString(4, director);
	            pstmt.setInt(5, year);
	            pstmt.executeUpdate();				
	            }
	        
	        catch (SQLException e) {
	            System.out.println(e.getMessage());
	        	}
	        
	    }
		private Connection connect() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:D:\\SQLite\\sqlite-tools-win32-x86-3360000\\Moviesdb.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
		private void SelectSQL() {
	        String url1 = "jdbc:sqlite:D:\\SQLite\\sqlite-tools-win32-x86-3360000\\Moviesdb.db";
			try {
				Connection connection = DriverManager.getConnection(url1);
				String sql = "SELECT rowid, * FROM Movies";
				
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				System.out.println("ROWID | NAME | ACTOR | ACTRESS | DIRECTOR | YEAR");
				
				while(result.next()) {
					Integer rowid = result.getInt("rowid");
					String name = result.getString("name");
					String actor = result.getString("actor");
					String actress = result.getString("actress");
					String director = result.getString("director");
					Integer year = result.getInt("year");

					
					System.out.println(rowid+ "     | " +name + " | " + actor+ " | " +actress+ " | " +director+ " | " +year);
				}
				
			} catch (SQLException e) {
				
				System.out.println("Error connecting to SQLite database - Moviesdb.db");
				
				e.printStackTrace();
			}
			
		}

}
