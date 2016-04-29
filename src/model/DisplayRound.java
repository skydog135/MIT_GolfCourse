/**
 * 
 */
package model;

/**
 * @author jjewell_000
 * This model is used to format the hole summary information for each hole 
 * during a round
 */
public class DisplayRound {
	private int displayRoundHoleID;
	private int displayRoundHoleNumber;
	private int displayRoundHolePar;
	private int displayRoundHoleHandicap;
	private String displayTee;
	private int displayRoundHoleYardage;
	private int displayRoundHoleGross;
	private int displayRoundHoleSummaryID;
	private String displayRoundHoleSummaryComments;


	public DisplayRound() {
		// default constructor
		this.displayRoundHoleID = 0;
		this.displayRoundHoleNumber = 0;
		this.displayRoundHolePar = 0;
		this.displayRoundHoleHandicap = 0;
		this.displayTee = "";
		this.displayRoundHoleYardage = 0;
		this.displayRoundHoleGross = 0;
		this.displayRoundHoleSummaryID = 0;
		this.displayRoundHoleSummaryComments="";
	}
	
	
	/**
	 * @param roundHoleSummaryID
	 * @param roundHoleSummaryRoundID
	 * @param roundHoleSummaryHoleID
	 * @param roundHoleSummaryGross
	 * @param roundHoleSummaryComments
	 */
	public DisplayRound(int holeid,int holenumber,int holepar,int holehandicap, String tee, int holeyardage, int holegross, int holesummaryID, String holesummarycomments) {
		this.displayRoundHoleID = holeid;
		this.displayRoundHoleNumber = holenumber;
		this.displayRoundHolePar = holepar;
		this.displayRoundHoleHandicap = holehandicap;
		this.displayTee = tee;
		this.displayRoundHoleYardage = holeyardage;
		this.displayRoundHoleGross = holegross;
		this.displayRoundHoleSummaryID = holesummaryID;
		this.displayRoundHoleSummaryComments = holesummarycomments;
	}


	/**
	 * @return the displayRoundHoleID
	 */
	public int getDisplayRoundHoleID() {
		return displayRoundHoleID;
	}


	/**
	 * @param displayRoundHoleID the displayRoundHoleID to set
	 */
	public void setDisplayRoundHoleID(int displayRoundHoleID) {
		this.displayRoundHoleID = displayRoundHoleID;
	}


	/**
	 * @return the displayRoundHoleNumber
	 */
	public int getDisplayRoundHoleNumber() {
		return displayRoundHoleNumber;
	}


	/**
	 * @param displayRoundHoleNumber the displayRoundHoleNumber to set
	 */
	public void setDisplayRoundHoleNumber(int displayRoundHoleNumber) {
		this.displayRoundHoleNumber = displayRoundHoleNumber;
	}


	/**
	 * @return the displayRoundHolePar
	 */
	public int getDisplayRoundHolePar() {
		return displayRoundHolePar;
	}


	/**
	 * @param displayRoundHolePar the displayRoundHolePar to set
	 */
	public void setDisplayRoundHolePar(int displayRoundHolePar) {
		this.displayRoundHolePar = displayRoundHolePar;
	}


	/**
	 * @return the displayRoundHoleHandicap
	 */
	public int getDisplayRoundHoleHandicap() {
		return displayRoundHoleHandicap;
	}


	/**
	 * @param displayRoundHoleHandicap the displayRoundHoleHandicap to set
	 */
	public void setDisplayRoundHoleHandicap(int displayRoundHoleHandicap) {
		this.displayRoundHoleHandicap = displayRoundHoleHandicap;
	}


	/**
	 * @return the displayTee
	 */
	public String getDisplayTee() {
		return displayTee;
	}


	/**
	 * @param displayTee the displayTee to set
	 */
	public void setDisplayTee(String displayTee) {
		this.displayTee = displayTee;
	}


	/**
	 * @return the displayRoundHoleYardage
	 */
	public int getDisplayRoundHoleYardage() {
		return displayRoundHoleYardage;
	}


	/**
	 * @param displayRoundHoleYardage the displayRoundHoleYardage to set
	 */
	public void setDisplayRoundHoleYardage(int displayRoundHoleYardage) {
		this.displayRoundHoleYardage = displayRoundHoleYardage;
	}


	/**
	 * @return the displayRoundHoleGross
	 */
	public int getDisplayRoundHoleGross() {
		return displayRoundHoleGross;
	}


	/**
	 * @param displayRoundHoleGross the displayRoundHoleGross to set
	 */
	public void setDisplayRoundHoleGross(int displayRoundHoleGross) {
		this.displayRoundHoleGross = displayRoundHoleGross;
	}


	/**
	 * @return the displayRoundHoleSummaryID
	 */
	public int getDisplayRoundHoleSummaryID() {
		return displayRoundHoleSummaryID;
	}


	/**
	 * @param displayRoundHoleSummaryID the displayRoundHoleSummaryID to set
	 */
	public void setDisplayRoundHoleSummaryID(int displayRoundHoleSummaryID) {
		this.displayRoundHoleSummaryID = displayRoundHoleSummaryID;
	}
	
	/**
	 * @return the displayRoundHoleSummaryComments
	 */
	public String getDisplayRoundHoleSummaryComments() {
		return displayRoundHoleSummaryComments;
	}


	/**
	 * @param displayTee the displayTee to set
	 */
	public void setDisplayRoundHoleSummaryComments(String displayRoundHoleSummaryComments) {
		this.displayRoundHoleSummaryComments = displayRoundHoleSummaryComments;
	}	
}


