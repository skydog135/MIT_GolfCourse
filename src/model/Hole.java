/**
 * 
 */
package model;

/**
 * @author jjewell_000
 *
 */
public class Hole {
	private int holeID;
	private int holeCourseID;
	private int holeNumber;
	private int holeBackGreenDistance;
	private int holePar;
	private int holeHandicap;
	private int holeWomensPar;

	public Hole() {
		// default constructor
		this.holeID = 0;
		this.holeCourseID = 0;
		this.holeNumber = 0;
		this.holeBackGreenDistance = 0;
		this.holePar = 0;
		this.holeHandicap = 0;
		this.holeWomensPar = 0;
	}
	
	
	
	
	/**
	 * @param holeID
	 * @param holeCourseID
	 * @param holeNumber
	 * @param holeBackGreenDistance
	 * @param holePar
	 * @param holeHandicap
	 * @param holeWomensPar
	 */
	public Hole(int holeid,int courseid,int holenumber,int holebackgreendistance, int holepar, int holehandicap, int holewomenspar) {
		this.holeID = holeid;
		this.holeCourseID = courseid;
		this.holeNumber = holenumber;
		this.holeBackGreenDistance = holebackgreendistance;
		this.holePar = holepar;
		this.holeHandicap = holehandicap;
		this.holeWomensPar = holewomenspar;
	}

	/**
	 * @return the holeID
	 */
	public int getHoleID() {
		return holeID;
	}

	/**
	 * @param holeID the holeID to set
	 */
	public void setHoleID(int holeID) {
		this.holeID = holeID;
	}

	/**
	 * @return the holeCourseID
	 */
	public int getHoleCourseID() {
		return holeCourseID;
	}

	/**
	 * @param holeCourseID the holeCourseID to set
	 */
	public void setHoleCourseID(int holeCourseID) {
		this.holeCourseID = holeCourseID;
	}

	/**
	 * @return the holeNumber
	 */
	public int getHoleNumber() {
		return holeNumber;
	}

	/**
	 * @param holeNumber the holeNumber to set
	 */
	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}

	/**
	 * @return the holeBackGreenDistance
	 */
	public int getHoleBackGreenDistance() {
		return holeBackGreenDistance;
	}

	/**
	 * @param holeBackGreenDistance the holeBackGreenDistance to set
	 */
	public void setHoleBackGreenDistance(int holeBackGreenDistance) {
		this.holeBackGreenDistance = holeBackGreenDistance;
	}

	/**
	 * @return the holePar
	 */
	public int getHolePar() {
		return holePar;
	}

	/**
	 * @param holePar the holePar to set
	 */
	public void setHolePar(int holePar) {
		this.holePar = holePar;
	}

	/**
	 * @return the holeHandicap
	 */
	public int getHoleHandicap() {
		return holeHandicap;
	}

	/**
	 * @param holeHandicap the holeHandicap to set
	 */
	public void setHoleHandicap(int holeHandicap) {
		this.holeHandicap = holeHandicap;
	}

	/**
	 * @return the holeWomensPar
	 */
	public int getHoleWomensPar() {
		return holeWomensPar;
	}

	/**
	 * @param holeWomensPar the holeWomensPar to set
	 */
	public void setHoleWomensPar(int holeWomensPar) {
		this.holeWomensPar = holeWomensPar;
	}

}
