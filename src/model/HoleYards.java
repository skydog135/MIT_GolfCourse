/**
 * 
 */
package model;

/**
 * @author jjewell_000
 * JAJ 3/20 CHANGED TEEID TO TEE / INT TO STRING
 */
public class HoleYards {
	private int holeYardsCourseID;
	private int holeYardsHoleID;
	private String holeYardsTee;
	private int holeYardsYardage;

	public HoleYards() {
		// default constructor
		this.holeYardsCourseID = 1;
		this.holeYardsHoleID = 0;
		this.holeYardsTee = "";
		this.holeYardsYardage = 0;
	}
	

	/**
	 * @param holeYardsCourseID
	 * @param holeYardsHoleID
	 * @param holeYardsTee
	 * @param holeYardsYardage
	 */
	public HoleYards(int holeyardscourseid,int holeyardsholdid,String holeyardstee,int holeyardsyardage) {
		this.holeYardsCourseID = holeyardscourseid;
		this.holeYardsHoleID = holeyardsholdid;
		this.holeYardsTee = holeyardstee;
		this.holeYardsYardage = holeyardsyardage;
	}


	/**
	 * @return the holeYardsCourseID
	 */
	public int getHoleYardsCourseID() {
		return holeYardsCourseID;
	}


	/**
	 * @param holeYardsID the holeYardsID to set
	 */
	public void setHoleYardsCourseID(int holeYardsCourseID) {
		this.holeYardsCourseID = holeYardsCourseID;
	}


	/**
	 * @return the holeYardsHoleID
	 */
	public int getHoleYardsHoleID() {
		return holeYardsHoleID;
	}


	/**
	 * @param holeYardsHoleID the holeYardsHoleID to set
	 */
	public void setHoleYardsHoleID(int holeYardsHoleID) {
		this.holeYardsHoleID = holeYardsHoleID;
	}


	/**
	 * @return the holeYardsTee
	 */
	public String getHoleYardsTee() {
		return holeYardsTee;
	}


	/**
	 * @param holeYardsTee the holeYardsTee to set
	 */
	public void setHoleYardsTee(String holeYardsTee) {
		this.holeYardsTee = holeYardsTee;
	}


	/**
	 * @return the holeYardsYardage
	 */
	public int getHoleYardsYardage() {
		return holeYardsYardage;
	}


	/**
	 * @param holeYardsYardage the holeYardsYardage to set
	 */
	public void setHoleYardsYardage(int holeYardsYardage) {
		this.holeYardsYardage = holeYardsYardage;
	}

	
}
