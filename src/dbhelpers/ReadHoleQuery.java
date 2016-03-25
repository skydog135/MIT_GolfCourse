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

/**
 * @author jjewell_000
 *
 */
public class ReadHoleQuery {

public Connection connection;
private ResultSet results;
	
	public ReadHoleQuery(String dbName, String uname, String pw){
		//JAJ corrected url
		/*String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;*/
		System.out.println("I'm in the ReadHoleQuery dbHelper");
		
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
	
	public void doReadHole (){
		
		System.out.println("I'm in the ReadHoleQuery dbHelper doReadHole");
		//set up String for query
		String query = "select * from Hole WHERE holeCourseID=1 ORDER BY holeNumber ASC";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results= ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  Hole[] loadHoleSessionArray (){
		System.out.println("I'm in the ReadHoleQuery dbHelper loadHoleSessionArray");
		//Define the array that will store each hole object
		Hole[] holesArray = new Hole [18];
		int i = 0;
		
		try {

			while(this.results.next()){
				
			//read row by row of retrieved hole information
				Hole hole = new Hole();
				hole.setHoleID(this.results.getInt("holeID"));
				System.out.println("I'm in the ReadHoleQuery dbHelper loadHoleSessionArray" + this.results.getInt("holeID"));
				hole.setHoleCourseID(this.results.getInt("holeCourseID"));
				hole.setHoleNumber(this.results.getInt("holeNumber"));
				System.out.println("I'm in the ReadHoleQuery dbHelper loadHoleSessionArray" + this.results.getInt("holeNumber"));
				hole.setHoleBackGreenDistance(this.results.getInt("holeBackGreenDistance"));
				hole.setHolePar(this.results.getInt("holePar"));
				hole.setHoleHandicap(this.results.getInt("holeHandicap"));
				hole.setHoleWomensPar(this.results.getInt("holeWomensPar"));

				//load array to be stored as a session variable
				holesArray[i]=hole;
				System.out.println("The int counter is" + i);
				System.out.println("I'm in the ReadHoleQuery dbHelper loadHoleSessionArray" + holesArray[i].getHoleID());
				System.out.println("I'm in the ReadHoleQuery dbHelper loadHoleSessionArray" + holesArray[i].getHoleNumber());
				System.out.println("I'm in the ReadHoleQuery dbHelper loadHoleSessionArray" + holesArray[i].getHolePar());
				System.out.println("I'm in the ReadHoleQuery dbHelper loadHoleSessionArray" + holesArray[i].getHoleHandicap());
				
				//increment counter
				i=i+1;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return holesArray;


	}
	
}

