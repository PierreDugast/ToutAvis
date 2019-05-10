package opinion;


import java.util.Collection;

public class Review {

	/**
	 * @uml.property  name="mark"
	 */
	private float mark;

	/**
	 * Getter of the property <tt>mark</tt>
	 * @return  Returns the mark.
	 * @uml.property  name="mark"
	 */
	public float getMark() {
		return mark;
	}

	/**
	 * Setter of the property <tt>mark</tt>
	 * @param mark  The mark to set.
	 * @uml.property  name="mark"
	 */
	public void setMark(float mark) {
		this.mark = mark;
	}

	/**
	 * @uml.property  name="comment"
	 */
	private String comment;

	/**
	 * Getter of the property <tt>comment</tt>
	 * @return  Returns the comment.
	 * @uml.property  name="comment"
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Setter of the property <tt>comment</tt>
	 * @param comment  The comment to set.
	 * @uml.property  name="comment"
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/** 
	 * @uml.property name="item"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="review:Item"
	 */
	private Item item = null;

	/** 
	 * Getter of the property <tt>item</tt>
	 * @return  Returns the item.
	 * @uml.property  name="item"
	 */
	public Item getItem() {
		return item;
	}

	/** 
	 * Setter of the property <tt>item</tt>
	 * @param item  The item to set.
	 * @uml.property  name="item"
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/** 
	 * @uml.property name="member"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="review:Member"
	 */
	private Member member = null;

	/** 
	 * Getter of the property <tt>member</tt>
	 * @return  Returns the member.
	 * @uml.property  name="member"
	 */
	public Member getMember() {
		return member;
	}

	/** 
	 * Setter of the property <tt>member</tt>
	 * @param member  The member to set.
	 * @uml.property  name="member"
	 */
	public void setMember(Member member) {
		this.member = member;
	}

}
