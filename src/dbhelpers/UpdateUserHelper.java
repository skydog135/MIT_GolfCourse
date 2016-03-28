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
public class UpdateUserHelper {

public Connection connection;
	
	public UpdateUserHelper(String dbName, String uname, String pw){	
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
	
	
	public void doUpdate(User user){
		//set up String for query
		//String query = "UPDATE Golfer (golferGender, golferPassword, golferFirstName, golferLastName, golferEmail, golferHandicapIndex, golferAvgScoreGross, golferAvgScoreNet) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String query = "UPDATE Golfer SET golferGender=?, golferPassword=?, golferFirstName=?, golferLastName=? golferhandicapIndex=? golferAvgScoreGross=? golferAvgScoreNet=? WHERE golferEmail=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, user.getGender());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setFloat(5, user.getGolferHandicapIndex());
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setString(8, user.getEmail());
				
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

