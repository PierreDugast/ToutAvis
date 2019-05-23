package opinion;

import java.util.LinkedList;

public class Member {

	private String login;
	private String password;
	private String profile;
	private float karma;

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

	public float getKarma() {
		return karma;
	}

	public void setKarma(int note, float reviewerKarma) {
		switch (note) {
		case 0:
			karma -= 0.03 * reviewerKarma;
			break;
		case 1:
			karma -= 0.02 * reviewerKarma;
			break;
		case 2:
			karma -= 0.01 * reviewerKarma;
			break;
		case 3:
			break;
		case 4:
			karma += 0.02 * reviewerKarma;
			break;
		case 5:
			karma += 0.03 * reviewerKarma;
			break;
		}
		if (karma < 0)
			karma = 0;
		else if (karma > 2)
			karma = 2;

	}

	private LinkedList<Review> reviews;

	public LinkedList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(LinkedList<Review> review) {
		this.reviews = review;
	}

	public void addReview(Review r) {
		reviews.add(r);
	}

	public Member(String login, String password, String profile) {
		this.login = login;
		this.password = password;
		this.profile = profile;
		reviews = new LinkedList<Review>();
		karma = 1;
	}

	public String toString() {
		return "--Pseudo : " + getLogin() + "  --Profile :" + getProfile()
				+ " --Karma actuel : " + getKarma();
	}
}
