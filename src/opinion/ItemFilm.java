package opinion;

public class ItemFilm extends Item {
	private String scenarist;
	private int duration;
	private String director;

	public ItemFilm(String title, String kind, String director,
			String scenarist, int duration, int ID) {
		super(title, kind, ID);
		this.director = director;
		this.scenarist = scenarist;
		this.duration = duration;
	}

	/**
	 * @uml.property name="director"
	 */

	/**
	 * Getter of the property <tt>director</tt>
	 * 
	 * @return Returns the director.
	 * @uml.property name="director"
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Setter of the property <tt>director</tt>
	 * 
	 * @param director
	 *            The director to set.
	 * @uml.property name="director"
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @uml.property name="scenarist"
	 */

	/**
	 * Getter of the property <tt>scenarist</tt>
	 * 
	 * @return Returns the scenarist.
	 * @uml.property name="scenarist"
	 */
	public String getScenarist() {
		return scenarist;
	}

	/**
	 * Setter of the property <tt>scenarist</tt>
	 * 
	 * @param scenarist
	 *            The scenarist to set.
	 * @uml.property name="scenarist"
	 */
	public void setScenarist(String scenarist) {
		this.scenarist = scenarist;
	}

	/**
	 * @uml.property name="duration"
	 */

	/**
	 * Getter of the property <tt>duration</tt>
	 * 
	 * @return Returns the duration.
	 * @uml.property name="duration"
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Setter of the property <tt>duration</tt>
	 * 
	 * @param duration
	 *            The duration to set.
	 * @uml.property name="duration"
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String toString() {
		String retour = super.toString() + " Type : FILM " + " --Director : "
				+ getDirector() + " --Scenariste  : " + getScenarist()
				+ " --Durée : " + getDuration() + " heures";
		return retour;
	}
}
