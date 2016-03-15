/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Hole;
import model.HoleYards;

/**
 * @author jjewell_000
 *
 */
public class ReadHoleYardsQuery {

public Connection connection;
private ResultSet results;
	
	public ReadHoleYardsQuery(String dbName, String uname, String pw){
		
		String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pw);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doReadHoleYards (){
		//set up String for query
		String query = "select * from HoleYards WHERE holeYardsCourseID=1 ORDER BY holeNumber ASC";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results= ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  ArrayList<HoleYards> loadHoleSessionArray (){
		
		//Define the array list that will store each hole tee yard object
		ArrayList<HoleYards> holeYardsArrayList = new ArrayList<HoleYards>();
		
		try {

			while(this.results.next()){
				
			//read row by row of retrieved hole yard information
				HoleYards holeYards = new HoleYards();
				holeYards.setHoleYardsCourseID(this.results.getInt("holeYardsCourseID"));
				holeYards.setHoleYardsHoleID(this.results.getInt("holeYardsHoleID"));
				holeYards.setHoleYardsTeeID(this.results.getInt("holeYardsTeeID"));
				holeYards.setHoleYardsYardage(this.results.getInt("holeYardsYardage"));
				
				//load array list to be stored as a session variable
				holeYardsArrayList.add(holeYards);

			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return holeYardsArrayList;


	}
	
}

