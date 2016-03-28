/**
 * 
 */
package model;

/**
 * @author jjewell_000
 *
 */
public class Round {
	private int roundID;
	private int roundGolferID;
	private String roundTee;
	private String roundDate;
	private int roundHolesToPlay;
	private int roundStartingHole;
	private float roundCourseHandicap;
	private int roundTotalGross;
	private int roundTotalNet;
	private String roundComments;

	
	/**
	 * @param roundID
	 * @param roundGolferID
	 * @param roundTee
	 * @param roundDate
	 * @param roundHolesToPlay
	 * @param roundStartingHole
	 * @param roundCourseHandicap
	 * @param roundTotalGross
	 * @param roundTotalNet
	 * @param roundComments
	 */
	
	public Round() {
		//default constructor
	
		this.roundID = 0;
		this.roundGolferID = 0;
		this.roundTee = "";
		this.roundDate = "";
		this.roundHolesToPlay = 0;
		this.roundStartingHole = 0;
		this.roundCourseHandicap = 0;
		this.roundTotalGross = 0;
		this.roundTotalNet = 0;
		this.roundComments = "";	
	}
	
	public Round(int roundID, int roundGolferID, String roundTee,
			String roundDate, int roundHolesToPlay, int roundStartingHole,
			float roundCourseHandicap, int roundTotalGross, int roundTotalNet,
			String roundComments) {
		super();
		this.roundID = roundID;
		this.roundGolferID = roundGolferID;
		this.roundTee = roundTee;
		this.roundDate = roundDate;
		this.roundHolesToPlay = roundHolesToPlay;
		this.roundStartingHole = roundStartingHole;
		this.roundCourseHandicap = roundCourseHandicap;
		this.roundTotalGross = roundTotalGross;
		this.roundTotalNet = roundTotalNet;
		this.roundComments = roundComments;
	}

	/**
	 * @return the roundID
	 */
	public int getRoundID() {
		return roundID;
	}

	/**
	 * @param roundID the roundID to set
	 */
	public void setRoundID(int roundID) {
		this.roundID = roundID;
	}

	/**
	 * @return the roundGolferID
	 */
	public int getRoundGolferID() {
		return roundGolferID;
	}

	/**
	 * @param roundGolferID the roundGolferID to set
	 */
	public void setRoundGolferID(int roundGolferID) {
		this.roundGolferID = roundGolferID;
	}

	/**
	 * @return the roundTee
	 */
	public String getRoundTee() {
		return roundTee;
	}

	/**
	 * @param roundTee the roundTee to set
	 */
	public void setRoundTee(String roundTee) {
		this.roundTee = roundTee;
	}

	/**
	 * @return the roundDate
	 */
	public String getRoundDate() {
		return roundDate;
	}

	/**
	 * @param roundDate the roundDate to set
	 */
	public void setRoundDate(String roundDate) {
		this.roundDate = roundDate;
	}

	/**
	 * @return the roundHolesToPlay
	 */
	public int getRoundHolesToPlay() {
		return roundHolesToPlay;
	}

	/**
	 * @param roundHolesToPlay the roundHolesToPlay to set
	 */
	public void setRoundHolesToPlay(int roundHolesToPlay) {
		this.roundHolesToPlay = roundHolesToPlay;
	}

	/**
	 * @return the roundStartingHole
	 */
	public int getRoundStartingHole() {
		return roundStartingHole;
	}

	/**
	 * @param roundStartingHole the roundStartingHole to set
	 */
	public void setRoundStartingHole(int roundStartingHole) {
		this.roundStartingHole = roundStartingHole;
	}

	/**
	 * @return the roundCourseHandicap
	 */
	public float getRoundCourseHandicap() {
		return roundCourseHandicap;
	}

	/**
	 * @param roundCourseHandicap the roundCourseHandicap to set
	 */
	public void setRoundCourseHandicap(float roundCourseHandicap) {
		this.roundCourseHandicap = roundCourseHandicap;
	}

	/**
	 * @return the roundTotalGross
	 */
	public int getRoundTotalGross() {
		return roundTotalGross;
	}

	/**
	 * @param roundTotalGross the roundTotalGross to set
	 */
	public void setRoundTotalGross(int roundTotalGross) {
		this.roundTotalGross = roundTotalGross;
	}

	/**
	 * @return the roundTotalNet
	 */
	public int getRoundTotalNet() {
		return roundTotalNet;
	}

	/**
	 * @param roundTotalNet the roundTotalNet to set
	 */
	public void setRoundTotalNet(int roundTotalNet) {
		this.roundTotalNet = roundTotalNet;
	}

	/**
	 * @return the roundComments
	 */
	public String getRoundComments() {
		return roundComments;
	}

	/**
	 * @param roundComments the roundComments to set
	 */
	public void setRoundComments(String roundComments) {
		this.roundComments = roundComments;
	}

}