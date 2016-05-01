/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Round;

/**
 * @author jjewell_000
 *
 */
public class ReadRoundQuery {

public Connection connection;
private ResultSet results;
	
	public ReadRoundQuery(String dbName, String uname, String pw){
		//JAJ corrected url
		/*String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;*/
		System.out.println("I'm in the ReadRoundQuery dbHelper");
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pw);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doReadRound (int golferID){
		
		System.out.println("I'm in the ReadRoundQuery dbHelper doReadRound");
		//set up String for query
		String query = "select * from round WHERE roundGolferID=? ORDER BY roundDate DESC";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, golferID);	
			this.results= ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getRoundHTMLTable(){
		String roundTable ="";
		roundTable += "<table border=1>";
		
		try {	
			roundTable +="<tr>";
			roundTable +="<td>";
			roundTable += "Date";
			roundTable +="</td>";
			roundTable +="<td>";
			roundTable += "Holes";
			roundTable +="</td>";
			roundTable +="<td>";
			roundTable += "Score";
			roundTable +="</td>";
			roundTable +="<td>";
			roundTable += "More";
			roundTable +="</td>";
			roundTable +="</tr>";
			
			this.results.beforeFirst();
			System.out.println("Just set round result set point to before first");
			
			while(this.results.next()){
				Round round = new Round();
				round.setRoundID(this.results.getInt("roundID"));
				round.setRoundGolferID(this.results.getInt("roundGolferID"));
				round.setRoundTee(this.results.getString("roundTee"));
				round.setRoundDate(this.results.getString("roundDate"));
				round.setRoundHolesToPlay(this.results.getInt("roundHolesToPlay"));
				round.setRoundStartingHole(this.results.getInt("roundStartingHole"));
				round.setRoundCourseHandicap(this.results.getFloat("roundCourseHandicap"));
				round.setRoundTotalGross(this.results.getInt("roundTotalGross"));
				round.setRoundTotalNet(this.results.getInt("roundTotalNet"));
				round.setRoundComments(this.results.getString("roundComments"));
				System.out.println("RoundID = " + (this.results.getInt("roundID")));
			
				roundTable +="<tr>";
				roundTable +="<td>";
				roundTable += round.getRoundDate();
				roundTable +="</td>";
				roundTable +="<td>";
				roundTable += round.getRoundHolesToPlay();
				roundTable +="</td>";
				roundTable +="<td>";
				roundTable += round.getRoundTotalGross();
				roundTable +="</td>";
				roundTable +="<td>";
				roundTable += "<form action='BuildRoundHistory' method = 'post'>" +
						"<input type='hidden' name='roundID' value='" + round.getRoundID()+ "'>" +
						"<input type='hidden' name='golferID' value='" + round.getRoundGolferID()+ "'>" +
						"<input type='hidden' name='holesPlayed' value='" + round.getRoundHolesToPlay()+ "'>" +
						"<input type='hidden' name='tee' value='" + round.getRoundTee()+ "'>" +
						"<input type='hidden' name='comments' value='" + round.getRoundComments()+ "'>" +
						"<input type='hidden' name='date' value='" + round.getRoundDate()+ "'>" +
						"<input type='submit' value='+'>" +
						"</form>";
				roundTable +="</td>";		
				roundTable +="</tr>";
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		roundTable += "</table>";
		System.out.println(roundTable);
		return roundTable;
	}
	
}

