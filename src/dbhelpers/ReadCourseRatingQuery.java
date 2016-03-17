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
import model.CourseRating;


/**
 * @author jjewell_000
 *
 */
public class ReadCourseRatingQuery {

public Connection connection;
private ResultSet results;
	
	public ReadCourseRatingQuery(String dbName, String uname, String pw){
		
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
	
	public void doReadCourseRating (){
		//set up String for query
		String query = "select * from CourseRating";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			this.results= ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  ArrayList<CourseRating> loadCourseRatingSessionArray (){
		
		//Define the array list that will store each club object
		ArrayList<CourseRating> courseRatingArrayList = new ArrayList<CourseRating>();
		
		try {

			while(this.results.next()){
				
			//read row by row of retrieved club information
				CourseRating courseRating = new CourseRating();
				courseRating.setCourseRatingID(this.results.getInt("courseRatingID"));
				courseRating.setCourseRatingCourseID(this.results.getInt("courseRatingCourseID"));
				courseRating.setCourseRatingTee(this.results.getString("courseTee"));
				courseRating.setCourseRatingGender(this.results.getString("courseRatingGender"));
				courseRating.setCourseRating18CR(this.results.getLong("courseRating18CR"));
				courseRating.setCourseRating18Slope(this.results.getInt("courseRating18Slope"));
				courseRating.setCourseRatingF9CR(this.results.getLong("courseRatingF9CR"));
				courseRating.setCourseRatingF9Slope(this.results.getInt("courseRatingF9Slope"));
				courseRating.setCourseRatingB9CR(this.results.getLong("courseRatingB9CR"));
				courseRating.setCourseRatingB9Slope(this.results.getInt("courseRatingB9Slope"));
				
				//load array list to be stored as a session variable
				courseRatingArrayList.add(courseRating);

			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courseRatingArrayList;
		
		//TEST


	}
	
}

