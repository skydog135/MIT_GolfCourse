/**
 * 
 */
package model;

/**
 * @author ajone_000
 *
 */
public class User {
	private int golferID;
/*Julie changed genderID to integer and added handicap index, avg gross, avg net fields
 * 3/10/16	
 */
	private String golferGender;
	private String golferPassword;
	private String golferFirstName;
	private String golferLastName;
	private String golferEmail;
	private float golferHandicapIndex;
	private int golferAvgScoreGross;
	private int golferAvgScoreNet;
	
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email 
	 * @param handicapIndex
	 * @param avgScoreGross
	 * @param avgScoreNet 
	 */
	
	public User() {
		// default constructor
		this.golferID = 0;
		this.golferGender = "";
		this.golferPassword = "";
		this.golferFirstName = "";
		this.golferLastName = "";
		this.golferEmail = "";
		this.golferHandicapIndex=0;
		this.golferAvgScoreGross=0;
		this.golferAvgScoreNet=0;
	}

	
	public User(int id, String gender, String password, String firstName,
			String lastName, String email, float handicapIndex, int avgScoreGross, int avgScoreNet) {
		this.golferID = id;
		this.golferGender = gender;
		this.golferPassword = password;
		this.golferFirstName = firstName;
		this.golferLastName = lastName;
		this.golferEmail = email;
		this.golferHandicapIndex=handicapIndex;
		this.golferAvgScoreGross=avgScoreGross;
		this.golferAvgScoreNet=avgScoreNet;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return golferID;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.golferID = id;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return golferGender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.golferGender = gender;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return golferPassword;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.golferPassword = password;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return golferFirstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.golferFirstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return golferLastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.golferLastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return golferEmail;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.golferEmail = email;
	}
	
	/**
	 * @return the golferHandicapIndex
	 */
	public float getGolferHandicapIndex() {
		return golferHandicapIndex;
	}
	/**
	 * @param golferHandicapIndex the golferHandicapIndex to set
	 */
	public void setGolferHandicapIndex(float golferHandicapIndex) {
		this.golferHandicapIndex = golferHandicapIndex;
	}
	/**
	 * @return the golferAvgScoreGross
	 */
	public int getGolferAvgScoreGross() {
		return golferAvgScoreGross;
	}
	/**
	 * @param golferAvgScoreGross the golferAvgScoreGross to set
	 */
	public void setGolferAvgScoreGross(int golferAvgScoreGross) {
		this.golferAvgScoreGross = golferAvgScoreGross;
	}
	/**
	 * @return the golferAvgScoreNet
	 */
	public int getGolferAvgScoreNet() {
		return golferAvgScoreNet;
	}
	/**
	 * @param golferAvgScoreNet the golferAvgScoreNet to set
	 */
	public void setGolferAvgScoreNet(int golferAvgScoreNet) {
		this.golferAvgScoreNet = golferAvgScoreNet;

	}
}
