/**
 * 
 */
package model;

/**
 * @author ajone_000
 *
 */
public class NewEmail {

	private String golferNewEmail;
	
	/**
	
	 * @param newEmail 
	 */
	
	public NewEmail() {
		// default constructor
		this.golferNewEmail = "";
		
	}

	
	public NewEmail(String newEmail) {
		this.golferNewEmail = newEmail;
	}

	/**
	 * @return the email
	 */
	public String getNewEmail() {
		return golferNewEmail;
	}
	/**
	 * @param email the email to set
	 */
	public void setNewEmail(String newEmail) {
		this.golferNewEmail = newEmail;
	}
}
