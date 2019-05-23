package opinion;
/*
 * This class defines a review in the social network
 * It contains the reviewer's identity, the item which is reviewed
 * and the mark it has been given
 */
public class Review {
	private String comment;
	private float mark;
	private Item item;
	private Member member = null;

	public Review(Member member, float mark, String comment, Item item) {
		this.member = member;
		this.mark = mark;
		this.comment = comment;
		this.item = item;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Item getValue() {
		return item;
	}

	public void setValue(Item item) {
		this.item = item;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public String toString(){
		return member.getLogin()+"has rated "+mark+":\n"+comment;
	}

}
