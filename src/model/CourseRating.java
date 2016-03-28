/**
 * 
 */
package model;

/**
 * @author jjewell_000
 *
 */
public class CourseRating {
	private int courseRatingID;
	private int courseRatingCourseID;
	private String courseRatingTee;
	private String courseRatingGender;
	private long courseRating18CR;
	private int courseRating18Slope;
	private long courseRatingF9CR;
	private int courseRatingF9Slope;
	private long courseRatingB9CR;
	private int courseRatingB9Slope;


	public CourseRating() {
		// default constructor
		this.courseRatingID=0;
		this.courseRatingCourseID=1;
		this.courseRatingTee="";
		this.courseRatingGender="";
		this.courseRating18CR=0;
		this.courseRating18Slope=0;
		this.courseRatingF9CR=0;
		this.courseRatingF9Slope=0;
		this.courseRatingB9CR=0;
		this.courseRatingB9Slope=0;
	}
	
	
	/**
	 * @param courseRatingID
	 * @param courseRatingCourse
	 * @param courseRatingTeeID
	 * @param courseRatingGender;
	 * @param courseRating18CR;
	 * @param courseRating18Slope;
	 * @param courseRatingF9CR;
	 * @param courseRatingF9Slope;
	 * @param courseRatingB9CR;
	 * @param courseRatingB98Slope;

	 * 
	 */
	public CourseRating(int courseratingid,int courseratingcourseid, String courseratingtee, String courseratinggender, long courserating18cr, int courserating18slope, long courseratingf9cr, int courseratingf9slope, long courseratingb9cr, int courseratingb9slope)
	{
		this.courseRatingID=courseratingid;
		this.courseRatingCourseID=courseratingcourseid;
		this.courseRatingTee=courseratingtee;
		this.courseRatingGender=courseratinggender;
		this.courseRating18CR=courserating18cr;
		this.courseRating18Slope=courserating18slope;
		this.courseRatingF9CR=courseratingf9cr;
		this.courseRatingF9Slope=courseratingf9slope;
		this.courseRatingB9CR=courseratingb9cr;
		this.courseRatingB9Slope=courseratingb9slope;
	}


	/**
	 * @return the courseRatingID
	 */
	public int getCourseRatingID() {
		return courseRatingID;
	}


	/**
	 * @param courseRatingID the courseRatingID to set
	 */
	public void setCourseRatingID(int courseRatingID) {
		this.courseRatingID = courseRatingID;
	}



	/**
	 * @return the courseRatingCourseID
	 */
	public int getCourseRatingCourseID() {
		return courseRatingCourseID;
	}


	/**
	 * @param courseRatingCourseID the courseRatingCourseID to set
	 */
	public void setCourseRatingCourseID(int courseRatingCourseID) {
		this.courseRatingCourseID = courseRatingCourseID;
	}


	/**
	 * @return the courseRatingTee
	 */
	public String getCourseRatingTee() {
		return courseRatingTee;
	}


	/**
	 * @param courseRatingTee the courseRatingTee to set
	 */
	public void setCourseRatingTee(String courseRatingTee) {
		this.courseRatingTee = courseRatingTee;
	}

	
	/**
	 * @return the courseRatingGender
	 */
	public String getCourseRatingGender() {
		return courseRatingGender;
	}


	/**
	 * @param courseRatingGender the courseRatingGender to set
	 */
	public void setCourseRatingGender(String courseRatingGender) {
		this.courseRatingGender = courseRatingGender;
	}


	/**
	 * @return the courseRating18CR
	 */
	public long getCourseRating18CR() {
		return courseRating18CR;
	}


	/**
	 * @param courseRating18CR the courseRating18CR to set
	 */
	public void setCourseRating18CR(long courseRating18CR) {
		this.courseRating18CR = courseRating18CR;
	}


	/**
	 * @return the courseRating18Slope
	 */
	public int getCourseRating18Slope() {
		return courseRating18Slope;
	}


	/**
	 * @param courseRating18Slope the courseRating18Slope to set
	 */
	public void setCourseRating18Slope(int courseRating18Slope) {
		this.courseRating18Slope = courseRating18Slope;
	}


	/**
	 * @return the courseRatingF9CR
	 */
	public long getCourseRatingF9CR() {
		return courseRatingF9CR;
	}


	/**
	 * @param courseRatingF9CR the courseRatingF9CR to set
	 */
	public void setCourseRatingF9CR(long courseRatingF9CR) {
		this.courseRatingF9CR = courseRatingF9CR;
	}


	/**
	 * @return the courseRatingF9Slope
	 */
	public int getCourseRatingF9Slope() {
		return courseRatingF9Slope;
	}


	/**
	 * @param courseRatingF9Slope the courseRatingF9Slope to set
	 */
	public void setCourseRatingF9Slope(int courseRatingF9Slope) {
		this.courseRatingF9Slope = courseRatingF9Slope;
	}


	/**
	 * @return the courseRatingB9CR
	 */
	public long getCourseRatingB9CR() {
		return courseRatingB9CR;
	}


	/**
	 * @param courseRatingB9CR the courseRatingB9CR to set
	 */
	public void setCourseRatingB9CR(long courseRatingB9CR) {
		this.courseRatingB9CR = courseRatingB9CR;
	}


	/**
	 * @return the courseRatingB98Slope
	 */
	public int getCourseRatingB9Slope() {
		return courseRatingB9Slope;
	}


	/**
	 * @param courseRatingB98Slope the courseRatingB98Slope to set
	 */
	public void setCourseRatingB9Slope(int courseRatingB9Slope) {
		this.courseRatingB9Slope = courseRatingB9Slope;
	}


}
