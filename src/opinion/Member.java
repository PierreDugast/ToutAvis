package opinion;

import java.util.LinkedList;

public class Member {
	
	private String login;
	private String password;
	private String profile;
	private float karma;
	private LinkedList<Review> reviews;//Opinions emitted
	private LinkedList<Review> reviewedOpinions;//List of the opinions rated b this member
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * 
	 * @return the karma of this member
	 */
	public float getKarma() {
		return karma;
	}

	/**
	 * This method will set/update the karma of this member
	 * using the mark given and the karma of the member reviewing
	 * It is summoned inside the reviewOpinion method
	 * *
	 */
	public void setKarma(int mark, float reviewerKarma) {
		switch (mark) {//We choose arbitrary values to increase or decrease the karma depending on the reviewer
						//The more he is confirmed, the higher the impact is
		case 0:
			karma -= 0.15 * reviewerKarma;
			break;
		case 1:
			karma -= 0.1 * reviewerKarma;
			break;
		case 2:
			karma -= 0.05 * reviewerKarma;
			break;
		case 3:
			break;
		case 4:
			karma += 0.1 * reviewerKarma;
			break;
		case 5:
			karma += 0.15 * reviewerKarma;
			break;
		}
		if (karma < 0)
			karma = 0;
		else if (karma > 2)
			karma = 2;
		
	}


	public LinkedList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(LinkedList<Review> review) {
		this.reviews = review;
	}

	public void addReview(Review r) {
		reviews.add(r);
	}
/**
 * 
 * @param login the Login of this member
 * @param password  His password
 * @param profile   A String to describe his profile
 */
	public Member(String login, String password, String profile) {
		this.login = login;
		this.password = password;
		this.profile = profile;
		reviews = new LinkedList<Review>();
		reviewedOpinions = new LinkedList<Review>();
		karma = 1;
	}
	
	/**
	 * Returns The Pseudo, Profile and Karma of this member in a Single String
	 */
	
	/**
	 * This method will add a review to the reviewedOpinions attribute .
	 * That action will allow later to check if one review has already been rated by the member 
	 * @param r
	 */

	public void addOpinion(Review r){
		reviewedOpinions.add(r);
	}
	
	public String toString() {
		return "--Pseudo : " + getLogin() + "  --Profile :" + getProfile()
				+ " --Karma actuel : " + getKarma();
	}

	/**
	 * 
	 * @param reviewToBerated is the review we want to check 
	 * @return true if the review is already rated 
	 * Allows the reviewOpinion() method to avoid multiple reviews on a same opinion by one member 
	 */
	public boolean hasAlreadyReviewed(Review reviewToBerated) {
		// TODO Auto-generated method stub
		return reviewedOpinions.contains(reviewToBerated);
	}
}
