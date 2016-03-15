/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

/**
 * @author ajone_000
 *
 */
public class UserDBHelper {

	
	private PreparedStatement authenticateUserStatement;
	
	
	public UserDBHelper(String dbName, String uname, String pw) {
		System.out.println("I'm in the UserDBHelper Servlet");
		
		//String url  = "jdbc:mysql://jjewell@ebus2.terry.uga.edu:22/" + dbName;
		String url  = "jdbc:mysql://localhost:3306/" + dbName;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, uname, pw);
		
			authenticateUserStatement = conn.prepareStatement("select * from tomcatdb where golferEmail=? and golferPassword=?");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/**
	 * Authenticates a user in the database.
	 * @param email  The email for the user.
	 * @param password  The password for the user.
	 * @return A user object if successful, null if unsuccessful.
	 */
	public User authenticateUser(String email, String password) {
		User user = null;
		try {
			
			authenticateUserStatement.setString(1, email);
			authenticateUserStatement.setString(2, password);
			ResultSet rs = authenticateUserStatement.executeQuery();

			//	public User(int id, String gender, String password, String firstName,
			
			//JAJ Added three new user fields at the end.
		
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("gender"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getLong("handicapIndex"), rs.getInt("avgScoreGross"), rs.getInt("avgScoreNet"));
			}
		} catch (SQLException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return user;
	}
}

