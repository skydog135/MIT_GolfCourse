/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				table += "<form action='displayShotDetail' method = 'post'>" +
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

	public String[] getHTML18Table(){
		String tableF9 ="";
		String tableB9 ="";
		int holeCounter = 1;
		
		String[] F9B9TableArray = new String[2];
		

		
//*****************************************************************************
//Format table for holes 1-9
		tableF9 += "<table border=1>";
		
		try {	
			tableF9 +="<tr>";
			tableF9 +="<td>";
			tableF9 += "Hole #";
			tableF9 +="</td>";
			tableF9 +="<td>";
			tableF9 += "Yards";
			tableF9 +="</td>";
			tableF9 +="<td>";
			tableF9 += "Par";
			tableF9 +="</td>";
			tableF9 +="<td>";
			tableF9 += "Shots";
			tableF9 +="</td>";
			tableF9 +="<td>";
			tableF9 += "Handicap";
			tableF9 +="</td>";
			tableF9 +="<td>";
			tableF9 += "Detail";
			tableF9 +="</td>";
			tableF9 +="</tr>";
			
			while (holeCounter < 10){//load the F9 table rows
			
				if(this.results.next()){
					DisplayRound dr = new DisplayRound();
					dr.setDisplayRoundHoleID(this.results.getInt("holeID"));
					dr.setDisplayRoundHoleNumber(this.results.getInt("holeNumber"));
					dr.setDisplayRoundHolePar(this.results.getInt("holePar"));
					dr.setDisplayRoundHoleHandicap(this.results.getInt("holeHandicap"));
					dr.setDisplayTee(this.results.getString("holeYardsTee"));
					dr.setDisplayRoundHoleYardage(this.results.getInt("holeYardsYardage"));
					dr.setDisplayRoundHoleGross(this.results.getInt("roundHoleSummaryGross"));
					dr.setDisplayRoundHoleSummaryID(this.results.getInt("roundHoleSummaryID"));
				
					tableF9 +="<tr>";
					tableF9 +="<td>";
					tableF9 += dr.getDisplayRoundHoleNumber();
					tableF9 +="</td>";
					tableF9 +="<td>";
					tableF9 += dr.getDisplayRoundHoleYardage();
					tableF9 +="</td>";
					tableF9 +="<td>";
					tableF9 += dr.getDisplayRoundHolePar();
					tableF9 +="</td>";
					tableF9 +="<td>";
					tableF9 += dr.getDisplayRoundHoleGross();
					tableF9 +="</td>";
					tableF9 +="<td>";
					tableF9 += dr.getDisplayRoundHoleHandicap();
					tableF9 +="</td>";
					tableF9 +="<td>";
					tableF9 += "<form action='displyShotDetail' method = 'post'>" +
							"<input type='hidden' name='roundHoleSummaryID' value='" + dr.getDisplayRoundHoleSummaryID()+ "'>" +
							"<input type='submit' value='Details'>" +
							"</form>";
					tableF9 +="</td>";		
					tableF9 +="</tr>";
				}
			//increment hole counter; will only process holes 1 - 9 for first table	
			holeCounter = holeCounter +1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableF9 += "</table>";
		F9B9TableArray[0]= tableF9;
		System.out.println(tableF9);

// End processing for holes 1-9		
//*****************************************************************************		

		
//*$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//Format table for holes 10-18
				tableB9 += "<table border=1>";
				
				try {	
					tableB9 +="<tr>";
					tableB9 +="<td>";
					tableB9 += "Hole #";
					tableB9 +="</td>";
					tableB9 +="<td>";
					tableB9 += "Yards";
					tableB9 +="</td>";
					tableB9 +="<td>";
					tableB9 += "Par";
					tableB9 +="</td>";
					tableB9 +="<td>";
					tableB9 += "Shots";
					tableB9 +="</td>";
					tableB9 +="<td>";
					tableB9 += "Handicap";
					tableB9 +="</td>";
					tableB9 +="<td>";
					tableB9 += "Detail";
					tableB9 +="</td>";
					tableB9 +="</tr>";
					
					while (holeCounter < 18){//load the B9 table rows
					
						if(this.results.next()){
							DisplayRound dr = new DisplayRound();
							dr.setDisplayRoundHoleID(this.results.getInt("holeID"));
							dr.setDisplayRoundHoleNumber(this.results.getInt("holeNumber"));
							dr.setDisplayRoundHolePar(this.results.getInt("holePar"));
							dr.setDisplayRoundHoleHandicap(this.results.getInt("holeHandicap"));
							dr.setDisplayTee(this.results.getString("holeYardsTee"));
							dr.setDisplayRoundHoleYardage(this.results.getInt("holeYardsYardage"));
							dr.setDisplayRoundHoleGross(this.results.getInt("roundHoleSummaryGross"));
							dr.setDisplayRoundHoleSummaryID(this.results.getInt("roundHoleSummaryID"));
						
							tableF9 +="<tr>";
							tableF9 +="<td>";
							tableF9 += dr.getDisplayRoundHoleNumber();
							tableF9 +="</td>";
							tableF9 +="<td>";
							tableF9 += dr.getDisplayRoundHoleYardage();
							tableF9 +="</td>";
							tableF9 +="<td>";
							tableF9 += dr.getDisplayRoundHolePar();
							tableF9 +="</td>";
							tableF9 +="<td>";
							tableF9 += dr.getDisplayRoundHoleGross();
							tableF9 +="</td>";
							tableF9 +="<td>";
							tableF9 += dr.getDisplayRoundHoleHandicap();
							tableF9 +="</td>";
							tableF9 +="<td>";
							tableF9 += "<form action='shotDetail' method = 'post'>" +
									"<input type='hidden' name='roundHoleSummaryID' value='" + dr.getDisplayRoundHoleSummaryID()+ "'>" +
									"<input type='submit' value='Details'>" +
									"</form>";
							tableF9 +="</td>";		
							tableF9 +="</tr>";
						}
					//increment hole counter; will only process holes 10 - 18 for first table	
					holeCounter = holeCounter +1;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tableF9 += "</table>";
				F9B9TableArray[1]= tableB9;
				System.out.println(tableB9);

// End processing for holes 10-18		
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
		

		return F9B9TableArray;
	}
	
}

