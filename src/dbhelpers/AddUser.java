/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;


/**
 * @author ajone_000
 *
 */
public class AddUser {

public Connection connection;
	
	public AddUser(String dbName, String uname, String pw){
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
	
	public void doAdd (User user){
		//set up String for query
		String query = "insert into Golfer (golferGender, golferPassword, golferFirstName, golferLastName, golferEmail,golferHandicapIndex, golferAvgScoreGross, golferAvgScoreNet) values (?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("I'm in the AddUser dbhelper inside doAdd");	
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, user.getGender());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setFloat(6, user.getGolferHandicapIndex());
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			
			System.out.println("I'm in the AddUser dbHelper just about to ps.executeUpdate");		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

