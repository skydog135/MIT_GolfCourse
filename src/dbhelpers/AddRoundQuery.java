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

import model.Round;


/**
 * @author ajone_000
 *
 */
public class AddRoundQuery {

public Connection connection;
private ResultSet results;
	
	public AddRoundQuery(String dbName, String uname, String pw){
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
	
	public int doAddRound (Round round){
		//initialize current round ID
		int currentRoundID=0;
		
		//set up String for query
		String query = "insert into Round (roundGolferID, roundTee, roundDate, roundHolesToPlay,roundStartingHole, roundCourseHandicap, roundTotalGross, roundTotalNet, roundComments) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("I'm in the AddRound dbhelper inside doAddRound");	
		try {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, round.getRoundGolferID());
			ps.setString(2, round.getRoundTee());
			ps.setString(3, round.getRoundDate());
			ps.setInt(4, round.getRoundHolesToPlay());
			ps.setInt(5, round.getRoundStartingHole());
			ps.setFloat(6, round.getRoundCourseHandicap());
			ps.setInt(7, round.getRoundTotalGross());
			ps.setInt(8, round.getRoundTotalNet());
			ps.setString(9, round.getRoundComments());			
			
			System.out.println("I'm in the AddRound dbHelper just about to ps.executeUpdate");		
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			 if(rs.next())
             {
                 currentRoundID = rs.getInt(1);
             }
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("The current round ID is " + currentRoundID);	
		return currentRoundID;
		}

		
		

}

