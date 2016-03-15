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
		
		String url  = "jdbc:mysql://ajones35@ebus2.terry.uga.edu:22/" + dbName;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
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
		String query = "insert into Golfer (golferGender, golferPassword, golferFirstName, golferLastName, golferEmail, golferHandicapIndex) values ('Male', ?, ?, ?, ?, ?)";
		
		
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, user.getGender());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setLong(6, user.getGolferHandicapIndex());
			//ps.setInt(7, 0);
			//ps.setInt(8, 0);
			
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

