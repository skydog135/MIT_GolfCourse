/**
 * 
 */
package model;

/**
 * @author jjewell_000
 * This model is used to format the hole summary information for each hole 
 * during a round
 */
public class RoundHoleSummary {
	private int roundHoleSummaryID;
	private int roundHoleSummaryRoundID;
	private int roundHoleSummaryHoleID;
	private int roundHoleSummaryGross;
	private String roundHoleSummaryComments;


	public RoundHoleSummary() {
		// default constructor
		this.roundHoleSummaryID = 0;
		this.roundHoleSummaryRoundID = 0;
		this.roundHoleSummaryHoleID = 0;
		this.roundHoleSummaryGross = 0;
		this.roundHoleSummaryComments = "   ";
	}
	
	
	/**
	 * @param roundHoleSummaryID
	 * @param roundHoleSummaryRoundID
	 * @param roundHoleSummaryHoleID
	 * @param roundHoleSummaryGross
	 * @param roundHoleSummaryComments
	 */
	public RoundHoleSummary(int roundholesummaryid,int roundholesummaryroundid,int roundholesummaryholeid,int roundholesummarygross, String roundholesummarycomments) {
		this.roundHoleSummaryID = roundholesummaryid;
		this.roundHoleSummaryRoundID = roundholesummaryroundid;
		this.roundHoleSummaryHoleID = roundholesummaryholeid;
		this.roundHoleSummaryGross = roundholesummarygross;
		this.roundHoleSummaryComments = roundholesummarycomments;
	}


	/**
	 * @return the roundHoleSummaryID
	 */
	public int getRoundHoleSummaryID() {
		return roundHoleSummaryID;
	}


	/**
	 * @param roundHoleSummaryID the roundHoleSummaryID to set
	 */
	public void setRoundHoleSummaryID(int roundHoleSummaryID) {
		this.roundHoleSummaryID = roundHoleSummaryID;
	}


	/**
	 * @return the roundHoleSummaryRoundID
	 */
	public int getRoundHoleSummaryRoundID() {
		return roundHoleSummaryRoundID;
	}


	/**
	 * @param roundHoleSummaryRoundID the roundHoleSummaryRoundID to set
	 */
	public void setRoundHoleSummaryRoundID(int roundHoleSummaryRoundID) {
		this.roundHoleSummaryRoundID = roundHoleSummaryRoundID;
	}


	/**
	 * @return the roundHoleSummaryHoleID
	 */
	public int getRoundHoleSummaryHoleID() {
		return roundHoleSummaryHoleID;
	}


	/**
	 * @param roundHoleSummaryHoleID the roundHoleSummaryHoleID to set
	 */
	public void setRoundHoleSummaryHoleID(int roundHoleSummaryHoleID) {
		this.roundHoleSummaryHoleID = roundHoleSummaryHoleID;
	}


	/**
	 * @return the roundHoleSummaryGross
	 */
	public int getRoundHoleSummaryGross() {
		return roundHoleSummaryGross;
	}


	/**
	 * @param roundHoleSummaryGross the roundHoleSummaryGross to set
	 */
	public void setRoundHoleSummaryGross(int roundHoleSummaryGross) {
		this.roundHoleSummaryGross = roundHoleSummaryGross;
	}


	/**
	 * @return the roundHoleSummaryComments
	 */
	public String getRoundHoleSummaryComments() {
		return roundHoleSummaryComments;
	}


	/**
	 * @param roundHoleSummaryComments the roundHoleSummaryComments to set
	 */
	public void setRoundHoleSummaryComments(String roundHoleSummaryComments) {
		this.roundHoleSummaryComments = roundHoleSummaryComments;
	}

	
}
