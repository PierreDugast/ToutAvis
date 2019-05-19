package opinion;


import java.util.LinkedList;

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
	public void setKarma(int note, float reviewerKarma) {
		switch(note){
		case 0: karma-=0.03*reviewerKarma;break;
		case 1: karma-=0.02*reviewerKarma;break;
		case 2: karma-=0.01*reviewerKarma;break;
		case 3: break;
		case 4: karma+=0.02*reviewerKarma;break;
		case 5: karma+=0.03*reviewerKarma;break;
		}
		if(karma<0) karma=0;
		else if(karma >2) karma=2;
		/*float tmp=karma*100;
		int entier=(int)tmp;
		karma=entier/100;*/
	}

	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="member:Review"
	 */
	private LinkedList<Review> reviews ;

	/** 
	 * Getter of the property <tt>review</tt>
	 * @return  Returns the review.
	 * @uml.property  name="review"
	 */
	public LinkedList<Review> getReviews() {
		return reviews;
	}

	/** 
	 * Setter of the property <tt>review</tt>
	 * @param review  The review to set.
	 * @uml.property  name="review"
	 */
	public void setReviews(LinkedList<Review> review) {
		this.reviews = review;
	}

	public void addReview(Review r){
		reviews.add(r);
	}
		
		/**
		 */
		public Member(String login, String password, String profile){
			this.login=login;
			this.password=password;
			this.profile=profile;
			reviews=new LinkedList<Review>();
			karma=1;
		}
	public String toString(){
		return	"--Pseudo : " + getLogin() + "  --Profile :"
		+ getProfile() + " --Karma actuel : "+getKarma();
	}
}
