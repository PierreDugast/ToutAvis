package opinion;

public class ItemBook extends Item {

	/**
	 * @uml.property name="author"
	 */
	private String author;
	private int nbPages;

	public ItemBook(String title, String kind, String author, int nbPages,
			int ID) {
		super(title, kind, ID);
		this.author = author;
		this.nbPages = nbPages;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter of the property <tt>author</tt>
	 * 
	 * @return Returns the author.
	 * @uml.property name="author"
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Setter of the property <tt>author</tt>
	 * 
	 * @param author
	 *            The author to set.
	 * @uml.property name="author"
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @uml.property name="nbPages"
	 */

	/**
	 * Getter of the property <tt>nbPages</tt>
	 * 
	 * @return Returns the nbPages.
	 * @uml.property name="nbPages"
	 */
	public int getNbPages() {
		return nbPages;
	}

	/**
	 * Setter of the property <tt>nbPages</tt>
	 * 
	 * @param nbPages
	 *            The nbPages to set.
	 * @uml.property name="nbPages"
	 */
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public String toString() {
		String retour = super.toString() + " Type : LIVRE " + " --Auteur  : "
				+ getAuthor() + " --Nombre de Pages : " + getNbPages();
		return retour;
	}

}
