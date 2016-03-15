/**
 * 
 */
package model;

/**
 * @author jjewell_000
 * This model is used to format the shot table for each shot 
 * during a round
 */
public class Shot {
	private int shotID;
	private int shotRoundHoleSummaryID;
	private int shotClubID;
	private int shotLocationID;
	private int shotNumber;
	private int shotDistance;
	private int shotPenaltyStroke;


	public Shot() {
		// default constructor
		this.shotID = 0;
		this.shotRoundHoleSummaryID = 0;
		this.shotClubID = 0;
		this.shotLocationID = 0;
		this.shotNumber = 0;
		this.shotDistance = 0;
		this.shotPenaltyStroke = 0;
	}
	
	
	/**
	 * @param shotID
	 * @param shotRoundHoleSummaryID
	 * @param shotClubID
	 * @param shotLocationID
	 * @param shotNumber
	 * @param shotDistance
	 * @param shotPenaltyStroke
	 * 
	 */
	public Shot(int shotid,int shotroundholesummaryid,int shotclubid,int shotlocationid, int shotnumber, int shotdistance, int shotpenaltystroke) {
		this.shotID =  shotid;
		this.shotRoundHoleSummaryID = shotroundholesummaryid;
		this.shotClubID = shotclubid;
		this.shotLocationID = shotlocationid;
		this.shotNumber = shotnumber;
		this.shotDistance = shotdistance;
		this.shotPenaltyStroke = shotpenaltystroke;
	}


	/**
	 * @return the shotID
	 */
	public int getShotID() {
		return shotID;
	}


	/**
	 * @param shotID the shotID to set
	 */
	public void setShotID(int shotID) {
		this.shotID = shotID;
	}


	/**
	 * @return the shotRoundHoleSummaryID
	 */
	public int getShotRoundHoleSummaryID() {
		return shotRoundHoleSummaryID;
	}


	/**
	 * @param shotRoundHoleSummaryID the shotRoundHoleSummaryID to set
	 */
	public void setShotRoundHoleSummaryID(int shotRoundHoleSummaryID) {
		this.shotRoundHoleSummaryID = shotRoundHoleSummaryID;
	}


	/**
	 * @return the shotClubID
	 */
	public int getShotClubID() {
		return shotClubID;
	}


	/**
	 * @param shotClubID the shotClubID to set
	 */
	public void setShotClubID(int shotClubID) {
		this.shotClubID = shotClubID;
	}


	/**
	 * @return the shotLocationID
	 */
	public int getShotLocationID() {
		return shotLocationID;
	}


	/**
	 * @param shotLocationID the shotLocationID to set
	 */
	public void setShotLocationID(int shotLocationID) {
		this.shotLocationID = shotLocationID;
	}


	/**
	 * @return the shotNumber
	 */
	public int getShotNumber() {
		return shotNumber;
	}


	/**
	 * @param shotNumber the shotNumber to set
	 */
	public void setShotNumber(int shotNumber) {
		this.shotNumber = shotNumber;
	}


	/**
	 * @return the shotDistance
	 */
	public int getShotDistance() {
		return shotDistance;
	}


	/**
	 * @param shotDistance the shotDistance to set
	 */
	public void setShotDistance(int shotDistance) {
		this.shotDistance = shotDistance;
	}


	/**
	 * @return the shotPenaltyStroke
	 */
	public int getShotPenaltyStroke() {
		return shotPenaltyStroke;
	}


	/**
	 * @param shotPenaltyStroke the shotPenaltyStroke to set
	 */
	public void setShotPenaltyStroke(int shotPenaltyStroke) {
		this.shotPenaltyStroke = shotPenaltyStroke;
	}


}
