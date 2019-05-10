package opinion;


import java.util.Collection;

public class Member {

	/**
	 * @uml.property  name="login"
	 */
	private String login;

	/**
	 * Getter of the property <tt>login</tt>
	 * @return  Returns the login.
	 * @uml.property  name="login"
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Setter of the property <tt>login</tt>
	 * @param login  The login to set.
	 * @uml.property  name="login"
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @uml.property  name="password"
	 */
	private String password;

	/**
	 * Getter of the property <tt>password</tt>
	 * @return  Returns the password.
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter of the property <tt>password</tt>
	 * @param password  The password to set.
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @uml.property  name="profile"
	 */
	private String profile;

	/**
	 * Getter of the property <tt>profile</tt>
	 * @return  Returns the profile.
	 * @uml.property  name="profile"
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * Setter of the property <tt>profile</tt>
	 * @param profile  The profile to set.
	 * @uml.property  name="profile"
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @uml.property  name="karma"
	 */
	private float karma;

	/**
	 * Getter of the property <tt>karma</tt>
	 * @return  Returns the karma.
	 * @uml.property  name="karma"
	 */
	public float getKarma() {
		return karma;
	}

	/**
	 * Setter of the property <tt>karma</tt>
	 * @param karma  The karma to set.
	 * @uml.property  name="karma"
	 */
	public void setKarma(float karma) {
		this.karma = karma;
	}

	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="member:Review"
	 */
	private Collection review = new java.util.ArrayList();

	/** 
	 * Getter of the property <tt>review</tt>
	 * @return  Returns the review.
	 * @uml.property  name="review"
	 */
	public Collection getReview() {
		return review;
	}

	/** 
	 * Setter of the property <tt>review</tt>
	 * @param review  The review to set.
	 * @uml.property  name="review"
	 */
	public void setReview(Collection review) {
		this.review = review;
	}

		
		/**
		 */
		public Member(String login, String password, String profile){
			this.login=login;
			this.password=password;
			this.profile=profile;
			karma=0;
		}



}
