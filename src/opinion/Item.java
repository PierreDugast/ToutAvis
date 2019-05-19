package opinion;


import java.util.Collection;
import java.util.LinkedList;

public abstract class Item {
	
	protected float karmas=0;
	protected int ID;
	public Item(String title,String kind,int ID){
		this.title=title;
		this.kind=kind;
		this.ID=ID;
		karmas=0;
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
		int i;
		
		//Check if the member hasn't already reviewed the item
		for(i=0;i<nbReviews && reviews.get(i).getMember().getLogin().equals(r.getMember().getLogin())==false;i++);
		
		//If he never did :
		if(i==nbReviews){											
			reviews.add(r);
			nbReviews++;
			karmas+=r.getMember().getKarma();//Update the total of karmas to  compute the mean mark
			
			if(nbReviews==1) mark=r.getMark();// This is the  first  review of the item
			
			//Calculate The mean without revisiting the whole list
			else mark=(mark* (karmas-  r.getMember().getKarma()  )+r.getMark()*r.getMember().getKarma())/(karmas);
		}
		//If the user has already reviewed we remove his old review by the new one
		else{
			float oldMark=reviews.get(i).getMark()*reviews.get(i).getMember().getKarma();//His previous mark
			float oldKarma=reviews.get(i).getMember().getKarma();
			reviews.remove(i);//removing his old review
			reviews.add(r);//Adding the new one
			
			if(nbReviews==1) mark=r.getMark();
			
			//Calculate The mean without revisiting the whole list

			else mark=(mark*karmas-oldMark+r.getMark()*r.getMember().getKarma())/(karmas+r.getMember().getKarma()-oldKarma);
			
			karmas=karmas-oldKarma+r.getMember().getKarma();//Update the total of karmas to  compute the mean mark
		
		}
	}
	

	public String toString(){
		
		String retour="--Identifiant : "+ID+" --Titre : " + getTitle() + " --Genre : " + getKind()+" --Note attribuée : "+getMark();
		return retour;
	}
	
	public int getID(){
		return ID;
	}
	
	
/*	public void removeReview(Review r){
		for (Review tmp :reviews){
			if(tmp==r) {
				reviews.remove(tmp);
				if(nbReviews==1) {mark =0;nbReviews=0;}
				else{
					mark=(mark*(nbReviews)-r.getMark())/(nbReviews-1);
					nbReviews--;
				}
				break;
			}
		}
	}
*/
	
}
