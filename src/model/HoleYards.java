/**
 * 
 */
package model;

/**
 * @author jjewell_000
 *
 */
public class HoleYards {
	private int holeYardsCourseID;
	private int holeYardsHoleID;
	private int holeYardsTeeID;
	private int holeYardsYardage;

	public HoleYards() {
		// default constructor
		this.holeYardsCourseID = 1;
		this.holeYardsHoleID = 0;
		this.holeYardsTeeID = 0;
		this.holeYardsYardage = 0;
	}
	
	
	/**
	 * @param holeYardsCourseID
	 * @param holeYardsHoleID
	 * @param holeYardsTeeID
	 * @param holeYardsYardage
	 */
	public HoleYards(int holeyardscourseid,int holeyardsholdid,int holeyardsteeid,int holeyardsyardage) {
		this.holeYardsCourseID = holeyardscourseid;
		this.holeYardsHoleID = holeyardsholdid;
		this.holeYardsTeeID = holeyardsteeid;
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
	 * @return the holeYardsTeeID
	 */
	public int getHoleYardsTeeID() {
		return holeYardsTeeID;
	}


	/**
	 * @param holeYardsTeeID the holeYardsTeeID to set
	 */
	public void setHoleYardsTeeID(int holeYardsTeeID) {
		this.holeYardsTeeID = holeYardsTeeID;
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
