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

import model.RoundHoleSummary;


/**
 * @author ajone_000
 *
 */
public class AddRoundHoleSummaryQuery {

public Connection connection;
private ResultSet rs;
private int currentRoundHoleSummaryID = 0;
	
	public AddRoundHoleSummaryQuery(String dbName, String uname, String pw){
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
	
	public int doAddRoundHoleSummary (RoundHoleSummary rhs){
		//initialize current round ID
		
		//set up String for query
		String query = "insert into roundholesummary (roundHoleSummaryRoundID, roundHoleSummaryHoleID, roundHoleSummaryGross, roundHoleSummaryComments) values (?, ?, ?, ?)";
		System.out.println("I'm in the AddRoundHoleSummary dbhelper inside doAddRoundHoleSummary");	
		try {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, rhs.getRoundHoleSummaryRoundID());
			ps.setInt(2, rhs.getRoundHoleSummaryHoleID());
			ps.setInt(3, rhs.getRoundHoleSummaryGross());
			ps.setString(4, rhs.getRoundHoleSummaryComments());
		
			
			System.out.println("I'm in the AddRoundHoleSummary dbHelper just about to ps.executeUpdate");		
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			 if(rs.next())
             {
                 currentRoundHoleSummaryID = rs.getInt(1);
         		System.out.println("I'm in the AddRoundHoleSummary:  retrieved currentRoundHoleSummaryID = " + currentRoundHoleSummaryID);
             }
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("The current round hole summary ID is " + currentRoundHoleSummaryID);	
		return currentRoundHoleSummaryID;
		}

		
		

}

