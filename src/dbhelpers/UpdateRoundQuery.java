/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;

import model.Round;
import model.User;


/**
 * @author ajone_000
 *
 */
public class UpdateRoundQuery {

public Connection connection;
private HttpSession session;


	
	public UpdateRoundQuery(String dbName, String uname, String pw){
		System.out.println("I'm in the AddUser servlet just about to set up url string ");	
		//String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			System.out.println("I'm in the AddUser dbhelper just about to do Class.forName ");		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("I'm in the AddUser db helper just about to do this.connection ");
			this.connection = DriverManager.getConnection(url, uname, pw);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//JAJ - Added three fields for user
	//handicap index, gross score (default to zero), net score (default to zero)
	
	public void doUpdateRound (int golferID, int roundID, int cumulativeShots,float courseHandicap, String comments){
		
		int roundCourseHandicap = (Integer)Math.round(courseHandicap);
		
		//set up String for query
		
		String query = "UPDATE Round SET golfer roundTotalGross=?, roundTotalNet=?, roundComments=? WHERE roundID=? AND roundGolferID=?";
		System.out.println("I'm in the Update Round dbhelper inside doUpdateRound");	
		try {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, cumulativeShots);
			ps.setInt(2, (cumulativeShots-roundCourseHandicap));
			ps.setString(3, comments);
			ps.setInt(4, golferID);
			ps.setInt(5, roundID);
	
			
			System.out.println("I'm in the Update Round dbHelper just about to ps.executeUpdate");		
			ps.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		}

		
		

}

