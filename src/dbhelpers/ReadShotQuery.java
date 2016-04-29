/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.mysql.jdbc.Statement;

import model.DisplayRound;
import model.Shot;


/**
 * @author ajone_000
 *
 */
public class ReadShotQuery {

public Connection connection;
private ResultSet results;

	
	public ReadShotQuery(String dbName, String uname, String pw){
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
	
	public void doReadShot (int roundHoleSummaryID){
		//get the shotRoundHoleSummaryID for the request attribute
		
		
		//set up String for query
		System.out.println("I'm in the ReadShotQuery dbhelper inside doReadShot");
		System.out.println("roundHoleSummaryID =" + roundHoleSummaryID);
		
		String query = "select * from shot WHERE shotRoundHoleSummaryID=? ORDER BY shotNumber ASC";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, roundHoleSummaryID);	
			
			System.out.println("I'm in the ReadShotQuery dbHelper doReadShot to ps.executeUpdate");		
			this.results= ps.executeQuery();
			this.results.last();
			int shotSize = this.results.getRow();
			System.out.println("I'm in the ReadShotQuery dbHelper doReadShot shot rows =" + shotSize);	
			//testing to be sure result set is not empty...
			int z = 1;
			this.results.beforeFirst();
			while (this.results.next()){
				System.out.println("Shot Result " + z + " is Shot Number " + this.results.getInt("shotNumber"));
				z=z+1;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Just finished doReadShot");	
		}

		
	public String getShotHTMLTable(){
		String shotTable ="";
		shotTable += "<table border=1>";
		
		try {	
			shotTable +="<tr>";
			shotTable +="<td>";
			shotTable += "Shot #";
			shotTable +="</td>";
			shotTable +="<td>";
			shotTable += "Club";
			shotTable +="</td>";
			shotTable +="<td>";
			shotTable += "Lie";
			shotTable +="</td>";
			shotTable +="<td>";
			shotTable += "Penalty";
			shotTable +="</td>";
			shotTable +="</tr>";
			
			this.results.beforeFirst();
			System.out.println("Just set Shot result set point to before first");
			
			while(this.results.next()){
				Shot shot = new Shot();
				System.out.println("I'm in the ReadShotQuery dbHelper reading through result set");	
				shot.setShotID(this.results.getInt("shotNumber"));
				shot.setShotRoundHoleSummaryID(this.results.getInt("shotRoundHoleSummaryID"));
				shot.setShotClub(this.results.getString("shotClub"));
				shot.setShotLocation(this.results.getString("shotLocation"));
				shot.setShotNumber(this.results.getInt("shotNumber"));
				System.out.println("I'm in the ReadShotQuery and shotNumber = "+ shot.getShotNumber());
				shot.setShotPenaltyStroke(this.results.getInt("ShotPenaltyStroke"));
			
				shotTable +="<tr>";
				shotTable +="<td>";
				shotTable += shot.getShotNumber();
				shotTable +="</td>";
				shotTable +="<td>";
				shotTable += shot.getShotClub();
				shotTable +="</td>";
				shotTable +="<td>";
				shotTable += shot.getShotLocation();
				shotTable +="</td>";
				shotTable +="<td>";
				shotTable += shot.getShotPenaltyStroke();
				shotTable +="</td>";	
				shotTable +="</tr>";
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		shotTable += "</table>";
		System.out.println("just finished loading shot detail table: = " + shotTable);
		return shotTable;
	}		

}

