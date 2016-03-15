/**
 * 
 */
package model;

/**
 * @author jjewell_000
 *
 */
public class Course {
	private int courseID;
	private String courseShortName;
	private String courseLongName;


	public Course() {
		// default constructor
		this.courseID = 1;
		this.courseShortName = "";
		this.courseLongName = "";
	}
	
	
	/**
	 * @param courseID
	 * @param courseShortName
	 * @param courseLongName
	 */
	public Course(int courseid,String courseshortname,String courselongname) {
		this.courseID = courseid;
		this.courseShortName = courseshortname;
		this.courseLongName = courselongname;

	}


	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}


	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}


	/**
	 * @return the courseShortName
	 */
	public String getCourseShortName() {
		return courseShortName;
	}


	/**
	 * @param courseShortName the courseShortName to set
	 */
	public void setCourseShortName(String courseShortName) {
		this.courseShortName = courseShortName;
	}


	/**
	 * @return the courseLongName
	 */
	public String getCourseLongName() {
		return courseLongName;
	}


	/**
	 * @param courseLongName the courseLongName to set
	 */
	public void setCourseLongName(String courseLongName) {
		this.courseLongName = courseLongName;
	}


}
