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
		System.out.println("I'm in the Update Round dbhelper just about to set up url string ");	
		//String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			System.out.println("I'm in the Update Round dbhelper just about to do Class.forName ");		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("I'm in the Update Round dbhelper just about to do this.connection ");
			this.connection = DriverManager.getConnection(url, uname, pw);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//JAJ - Added three fields for user
	//handicap index, gross score (default to zero), net score (default to zero)
	
	public void doUpdateRound (int golferID, int roundID, int cumulativeShots,int netScore, String comments){
		
		
		//set up String for query
		
		String query = "UPDATE Round SET roundTotalGross=?, roundTotalNet=?, roundComments=? WHERE roundID=? AND roundGolferID=?";
		System.out.println("I'm in the Update Round dbhelper inside doUpdateRound");	
		System.out.println("The golfer id is "+ golferID);
		System.out.println("The round id is "+ roundID);
		System.out.println("The cumulative shots are "+ cumulativeShots);
		System.out.println("The net Score "+ netScore);
		System.out.println("The comments "+ comments);
		
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, cumulativeShots);
			ps.setInt(2, netScore);
			ps.setString(3, comments);
			ps.setInt(4, roundID);
			ps.setInt(5, golferID);
	
			
			System.out.println("I'm in the Update Round dbHelper just about to ps.executeUpdate");		
			ps.executeUpdate();
			
			System.out.println("I'm in the Update Round dbHelper just executed Round Update");	
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		}

		
		

}

