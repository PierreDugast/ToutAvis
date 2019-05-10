package opinion;


import java.util.Collection;
import java.util.LinkedList;

public abstract class Item {

	public Item(String title,String kind){
		this.title=title;
		this.kind=kind;
		reviews=new LinkedList<Review>();
	}
	/** 
	 * @uml.property name="title"
	 */
	protected String title;
	
	protected int nbReviews=0;

	/** 
	 * Getter of the property <tt>title</tt>
	 * @return  Returns the title.
	 * @uml.property  name="title"
	 */
	public String getTitle() {
		return title;
	}

	/** 
	 * Setter of the property <tt>title</tt>
	 * @param title  The title to set.
	 * @uml.property  name="title"
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 
	 * @uml.property name="kind"
	 */
	protected String kind;

	/** 
	 * Getter of the property <tt>kind</tt>
	 * @return  Returns the kind.
	 * @uml.property  name="kind"
	 */
	public String getKind() {
		return kind;
	}

	/** 
	 * Setter of the property <tt>kind</tt>
	 * @param kind  The kind to set.
	 * @uml.property  name="kind"
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="item:Review"
	 */
	private LinkedList<Review> reviews;

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
	public void setReviews(LinkedList<Review> reviews_) {
		this.reviews = reviews_;
	}
	
	protected float mark=0;
	public float getMark(){
		return mark;
	}
	
	public void addReview(Review r ){
		reviews.add(r);
		nbReviews++;
		mark=(mark*(nbReviews-1)+r.getMark())/nbReviews;
	}
	
	public String toString(){
		String retour="--Titre : " + getTitle() + " --Genre : " + getKind()+" --Note attribuée : "+getMark();
		return retour;
	}
	
}