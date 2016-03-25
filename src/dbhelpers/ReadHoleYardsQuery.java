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
		System.out.println("I'm in the ReadHoleYardsQuery dbHelper");
		/*JAJ corrected URL*/
		/*String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;*/
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
	
	public void doReadHoleYards (){
		
		System.out.println("I'm in the ReadHoleYardsQuery dbHelper doReadHoleYards ");
		//set up String for query
		String query = "select * from HoleYards WHERE holeYardsCourseID=1";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results= ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  ArrayList<HoleYards> loadHoleSessionArray (){
		System.out.println("I'm in the ReadHoleYardsQuery dbHelper loadHoleSessionArray ");
		
		//Define the array list that will store each hole tee yard object
		ArrayList<HoleYards> holeYardsArrayList = new ArrayList<HoleYards>();
		
		try {

			while(this.results.next()){
				
			//read row by row of retrieved hole yard information
				HoleYards holeYards = new HoleYards();
				holeYards.setHoleYardsCourseID(this.results.getInt("holeYardsCourseID"));
				System.out.println("holeYardsCourseID = " + holeYards.getHoleYardsCourseID());
				holeYards.setHoleYardsHoleID(this.results.getInt("holeYardsHoleID"));
				System.out.println("holeYardsHoleID = " + holeYards.getHoleYardsHoleID());
				//JAJ 3/20 corrected tee from int to string
				holeYards.setHoleYardsTee(this.results.getString("holeYardsTee"));
				System.out.println("holeYardsTee = " + holeYards.getHoleYardsTee());
				holeYards.setHoleYardsYardage(this.results.getInt("holeYardsYardage"));
				System.out.println("holeYardsYardage = " + holeYards.getHoleYardsYardage());
				
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

