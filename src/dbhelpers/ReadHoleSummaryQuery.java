/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Hole;
import model.DisplayRound;



/**
 * @author jjewell_000
 *
 */
public class ReadHoleSummaryQuery {

public Connection connection;
private ResultSet results;
	
	public ReadHoleSummaryQuery(String dbName, String uname, String pw){
		//JAJ corrected url
		/*String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;*/
		System.out.println("I'm in the ReadHoleSummary dbHelper");
		
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
	
	public void doReadHoleSummary (String tee, int roundID){
		
		System.out.println("I'm in the ReadHoleSummary dbHelper doReadHoleSummary");
		//set up String for query
		String query = "select holeID, holeNumber, holePar, holeHandicap, holeYardsTee, holeYardsYardage, roundHoleSummaryGross, roundHoleSummaryID from hole h JOIN holeYards hy ON h.holeID=hy.holeYardsHoleID JOIN roundholesummary rhs ON hy.holeYardsHoleID = rhs.roundHoleSummaryHoleID WHERE hy.holeYardsTee=? AND rhs.roundHoleSummaryRoundID=? ORDER BY holeNumber ASC";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, tee);
			ps.setInt(2, roundID);	
			this.results= ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getHTMLTable(){
		String table ="";
		table += "<table border=1>";
		
		try {	
			table +="<tr>";
			table +="<td>";
			table += "Hole #";
			table +="</td>";
			table +="<td>";
			table += "Yards";
			table +="</td>";
			table +="<td>";
			table += "Par";
			table +="</td>";
			table +="<td>";
			table += "Shots";
			table +="</td>";
			table +="<td>";
			table += "Handicap";
			table +="</td>";
			table +="<td>";
			table += "Detail";
			table +="</td>";
			table +="</tr>";
			
			while(this.results.next()){
				DisplayRound dr = new DisplayRound();
				dr.setDisplayRoundHoleID(this.results.getInt("holeID"));
				dr.setDisplayRoundHoleNumber(this.results.getInt("holeNumber"));
				dr.setDisplayRoundHolePar(this.results.getInt("holePar"));
				dr.setDisplayRoundHoleHandicap(this.results.getInt("holeHandicap"));
				dr.setDisplayTee(this.results.getString("holeYardsTee"));
				dr.setDisplayRoundHoleYardage(this.results.getInt("holeYardsYardage"));
				dr.setDisplayRoundHoleGross(this.results.getInt("roundHoleSummaryGross"));
				dr.setDisplayRoundHoleSummaryID(this.results.getInt("roundHoleSummaryID"));
			
				table +="<tr>";
				table +="<td>";
				table += dr.getDisplayRoundHoleNumber();
				table +="</td>";
				table +="<td>";
				table += dr.getDisplayRoundHoleYardage();
				table +="</td>";
				table +="<td>";
				table += dr.getDisplayRoundHolePar();
				table +="</td>";
				table +="<td>";
				table += dr.getDisplayRoundHoleGross();
				table +="</td>";
				table +="<td>";
				table += dr.getDisplayRoundHoleHandicap();
				table +="</td>";
				table +="<td>";
				table += "<form action='shotDetail' method = 'post'>" +
						"<input type='hidden' name='roundHoleSummaryID' value='" + dr.getDisplayRoundHoleSummaryID()+ "'>" +
						"<input type='submit' value='Details'>" +
						"</form>";
				table +="</td>";		
				table +="</tr>";
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table += "</table>";
		System.out.println(table);
		return table;
	}
	
}

