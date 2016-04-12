/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import model.Shot;


/**
 * @author ajone_000
 *
 */
public class AddShotQuery {

public Connection connection;
private int currentRoundHoleSummaryID = 0;
	
	public AddShotQuery(String dbName, String uname, String pw){
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
	
	public void doAddShot (Shot shot){
		//initialize current round ID
		
		//set up String for query
		String query = "insert into shot (shotRoundHoleSummaryID, shotClub, shotLocation, shotNumber, shotPenaltyStroke) values (?, ?, ?, ?, ?)";
		System.out.println("I'm in the AddShotQuery dbhelper inside doAddShot");	
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, shot.getShotRoundHoleSummaryID());
			ps.setString(2, shot.getShotClub());
			ps.setString(3, shot.getShotLocation());
			ps.setInt(4, shot.getShotNumber());
			ps.setInt(5, shot.getShotPenaltyStroke());	
			
			System.out.println("I'm in the AddShotQuery dbHelper just about to ps.executeUpdate");		
			ps.executeUpdate();
			

		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("The current round hole summary ID is " + currentRoundHoleSummaryID);	
		}

		
		

}

