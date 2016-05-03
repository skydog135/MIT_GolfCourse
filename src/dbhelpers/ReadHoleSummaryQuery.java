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
		String query = "select holeID, holeNumber, holePar, holeHandicap, holeYardsTee, holeYardsYardage, roundHoleSummaryGross, roundHoleSummaryID, roundHoleSummaryComments from hole h JOIN holeYards hy ON h.holeID=hy.holeYardsHoleID JOIN roundholesummary rhs ON hy.holeYardsHoleID = rhs.roundHoleSummaryHoleID WHERE hy.holeYardsTee=? AND rhs.roundHoleSummaryRoundID=? ORDER BY holeNumber ASC";
		
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
	
	public String getHTMLTable(){//this code formats the 9 hole only table
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
			
			this.results.beforeFirst();
			System.out.println("Just set hole result set point to before first");
	
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
				dr.setDisplayRoundHoleSummaryComments(this.results.getString("roundHoleSummaryComments"));
				System.out.println("Hole Comments = " + this.results.getString("roundHoleSummaryComments"));
				
			
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
				table += "<form action='DisplayShotDetails' method = 'post'>" +
						"<input type='hidden' name='roundHoleSummaryID' value='" + dr.getDisplayRoundHoleSummaryID()+ "'>" +
						"<input type='hidden' name='holeNum' value='" + dr.getDisplayRoundHoleNumber()+ "'>" +
						"<input type='hidden' name='holeCom' value='" + dr.getDisplayRoundHoleSummaryComments()+ "'>" +
						"<input type='submit' value='+'>" +
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
	
	public int[] getNineHoleTotals(){//this code calcs the totals for a particular nine hole round
		int nineHoleTotal=0;
		int nineHolePar=0;

			System.out.println("In getNineHoleTotals: Just set hole result set point to before first");
		try {
			this.results.beforeFirst();
			
			while(this.results.next()){
				nineHoleTotal=nineHoleTotal+(this.results.getInt("roundHoleSummaryGross"));
				nineHolePar=nineHolePar + (this.results.getInt("holePar"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		int[] nineHoleSummaryArray = new int[6];
		nineHoleSummaryArray[0]=nineHoleTotal;
		nineHoleSummaryArray[1]=nineHolePar;
		nineHoleSummaryArray[2]=(nineHoleTotal - nineHolePar);
		nineHoleSummaryArray[3]=0;
		nineHoleSummaryArray[4]=0;
		nineHoleSummaryArray[5]=0;
								
		
		System.out.println(nineHoleSummaryArray);
		return nineHoleSummaryArray;
	}
	
	public String getRoundHoleSummaryHTML(int[] HoleSummaryArray, String tee){//this code formats the 9 hole only summary table
		String holeSummaryTable ="";
		int F9HoleTotal = HoleSummaryArray[0];
		int F9HoleOverUnder = HoleSummaryArray[2];
		int B9HoleTotal = HoleSummaryArray[3];
		int B9HoleOverUnder = HoleSummaryArray[5];
		
		holeSummaryTable += "<table border=1>";
		
			
			holeSummaryTable +="<tr>";
			holeSummaryTable +="<td>";
			holeSummaryTable += "Tee:";
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += tee;
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += "  ";
			holeSummaryTable +="</td>";
			holeSummaryTable +="</tr>";
			holeSummaryTable +="<tr>";
			holeSummaryTable +="<td>";
			holeSummaryTable += " ";
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += "Score";
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += "Over/Under";
			holeSummaryTable +="</td>";
			holeSummaryTable +="</tr>";
			holeSummaryTable +="<tr>";
			holeSummaryTable +="<td>";
			holeSummaryTable += "Front 9:";
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += F9HoleTotal;
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += F9HoleOverUnder;
			holeSummaryTable +="</td>";		
			holeSummaryTable +="</tr>";
			holeSummaryTable +="<tr>";
			holeSummaryTable +="<td>";
			holeSummaryTable += "Back 9:";
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += B9HoleTotal;
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += B9HoleOverUnder;
			holeSummaryTable +="</td>";		
			holeSummaryTable +="</tr>";
			holeSummaryTable +="<tr>";
			holeSummaryTable +="<td>";
			holeSummaryTable += "Total:";
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += (F9HoleTotal+B9HoleTotal);
			holeSummaryTable +="</td>";
			holeSummaryTable +="<td>";
			holeSummaryTable += (F9HoleOverUnder+B9HoleOverUnder);
			holeSummaryTable +="</td>";		
			holeSummaryTable +="</tr>";
			holeSummaryTable +="</tr>";
			holeSummaryTable += "</table>";	
		
		System.out.println(holeSummaryTable);
		return holeSummaryTable;
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
			
			this.results.beforeFirst();
			System.out.println("Just set hole result set point to before first");
			
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
					dr.setDisplayRoundHoleSummaryComments(this.results.getString("roundHoleSummaryComments"));
					System.out.println("Hole Comments = " + this.results.getString("roundHoleSummaryComments"));
				
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
					tableF9 += "<form action='DisplayShotDetails' method = 'post'>" +
							"<input type='hidden' name='roundHoleSummaryID' value='" + dr.getDisplayRoundHoleSummaryID()+ "'>" +
							"<input type='hidden' name='holeNum' value='" + dr.getDisplayRoundHoleNumber()+ "'>" +
							"<input type='hidden' name='holeCom' value='" + dr.getDisplayRoundHoleSummaryComments()+ "'>" +
							"<input type='submit' value='+'>" +
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
							dr.setDisplayRoundHoleSummaryComments(this.results.getString("roundHoleSummaryComments"));
							System.out.println("Hole Comments = " + this.results.getString("roundHoleSummaryComments"));
						
							tableB9 +="<tr>";
							tableB9 +="<td>";
							tableB9 += dr.getDisplayRoundHoleNumber();
							tableB9 +="</td>";
							tableB9 +="<td>";
							tableB9 += dr.getDisplayRoundHoleYardage();
							tableB9 +="</td>";
							tableB9 +="<td>";
							tableB9 += dr.getDisplayRoundHolePar();
							tableB9 +="</td>";
							tableB9 +="<td>";
							tableB9 += dr.getDisplayRoundHoleGross();
							tableB9 +="</td>";
							tableB9 +="<td>";
							tableB9 += dr.getDisplayRoundHoleHandicap();
							tableB9 +="</td>";
							tableB9 +="<td>";
							tableB9 += "<form action='DisplayShotDetails' method = 'post'>" +
									"<input type='hidden' name='roundHoleSummaryID' value='" + dr.getDisplayRoundHoleSummaryID()+ "'>" +
									"<input type='hidden' name='holeNum' value='" + dr.getDisplayRoundHoleNumber()+ "'>" +
									"<input type='hidden' name='holeCom' value='" + dr.getDisplayRoundHoleSummaryComments()+ "'>" +
									"<input type='submit' value='+'>" +
									"</form>";
							tableB9 +="</td>";		
							tableB9 +="</tr>";
						}
					//increment hole counter; will only process holes 10 - 18 for first table	
					holeCounter = holeCounter +1;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tableB9 += "</table>";
				F9B9TableArray[1]= tableB9;
				System.out.println(tableB9);

// End processing for holes 10-18		
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
		

		return F9B9TableArray;
	}
	
	public int[] getEighteenHoleTotals(){//this code calcs the totals for a particular 18 hole round
		int F9HoleTotal=0;
		int F9HolePar=0;
		int B9HoleTotal=0;
		int B9HolePar=0;	
		int counter = 1;

			System.out.println("In getEighteenHoleTotals: Just set hole result set point to before first");
		try {
			this.results.beforeFirst();
			
			while(this.results.next()){
				if (counter < 10){
					
				
				F9HoleTotal=F9HoleTotal+(this.results.getInt("roundHoleSummaryGross"));
				F9HolePar=F9HolePar + (this.results.getInt("holePar"));
				} else {
								
					B9HoleTotal=B9HoleTotal+(this.results.getInt("roundHoleSummaryGross"));
					B9HolePar=B9HolePar + (this.results.getInt("holePar"));	
				}
				counter = counter +1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		int[] EighteenHoleSummaryArray = new int[6];
		EighteenHoleSummaryArray[0]=F9HoleTotal;
		EighteenHoleSummaryArray[1]=F9HolePar;
		EighteenHoleSummaryArray[2]=F9HoleTotal-F9HolePar;
		EighteenHoleSummaryArray[3]=B9HoleTotal;
		EighteenHoleSummaryArray[4]=B9HolePar;
		EighteenHoleSummaryArray[5]=B9HoleTotal-B9HolePar;
								
		
		System.out.println(EighteenHoleSummaryArray);
		return EighteenHoleSummaryArray;
	}
	
}

