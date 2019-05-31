package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;
import exceptions.SameMemberException;
import java.util.Collection;

/**
 * Skeleton for the SocialNetwork
 * 
 */
public class SocialNetwork implements ISocialNetworkPremium {

	private int nbMembers = 0;
	private int nbItems = 0;
	private int nbBooks = 0;
	private int nbFilms = 0;
	private int reviewID = 0;

	private LinkedList<Member> members;

	private LinkedList<ItemBook> itemBooks;
	private LinkedList<ItemFilm> itemFilms;

	public SocialNetwork() {
		members = new LinkedList<Member>();
		itemBooks = new LinkedList<ItemBook>();
		itemFilms = new LinkedList<ItemFilm>();
	}

	@Override
	public int nbMembers() {
		// TODO Auto-generated method stub
		return nbMembers;
	}

	@Override
	public int nbFilms() {
		// TODO Auto-generated method stub
		return nbFilms;
	}

	@Override
	public int nbBooks() {
		// TODO Auto-generated method stub
		return nbBooks;
	}

	@Override
	public void addMember(String login, String password, String profile)
			throws BadEntryException, MemberAlreadyExistsException {

		if (login == null || login.trim().length() < 1
				|| login.trim().equals("") || password == null
				|| password.trim().equals("") || password.trim().length() < 4
				|| profile == null) {
			throw new BadEntryException(
					"The input is not correct  at addMember()");
		}

		else {

			int i = 0;
			for (i = 0; i < nbMembers && members.get(i).getLogin().trim() // Testing
																			// the
																			// existence
																			// of
																			// the
																			// member
					.compareToIgnoreCase(login.trim()) != 0; i++)
				;
			if (i == nbMembers) {
				nbMembers++;
				members.add(new Member(login, password, profile));
			} else
				throw new MemberAlreadyExistsException();

		}

	}

	@Override
	public void addItemFilm(String login, String password, String title,
			String kind, String director, String scenarist, int duration)
			throws BadEntryException, NotMemberException,
			ItemFilmAlreadyExistsException {
		if (login == null || login.trim().length() < 1
				|| login.trim().equals("") || password == null
				|| password.trim().equals("") || password.trim().length() < 4
				|| title == null || title.trim().length() < 1 || kind == null
				|| director == null || scenarist == null || duration <= 0)
			throw new BadEntryException(
					"The input is not correct at addItemFilm()");

		else {

			int i;
			for (i = 0; i < nbMembers()
					&& members.get(i).getLogin().trim()
							.compareToIgnoreCase(login) != 0; i++)
				;
			if (i == nbMembers) {
				throw new NotMemberException("The member " + login
						+ " does not exist !!");
			}

			else {

				if (members.get(i).getPassword().equals(password) == false)
					throw new NotMemberException("The member " + login
							+ " entered wrong password");

				for (i = 0; i < nbFilms
						&& itemFilms.get(i).getTitle().trim()
								.compareToIgnoreCase(title.trim()) != 0; i++)
					;
				if (i == nbFilms) {
					nbItems++;
					nbFilms++;
					itemFilms.add(new ItemFilm(title, kind, director,
							scenarist, duration, nbItems));
				} else
					throw new ItemFilmAlreadyExistsException();

			}

		}

	}

	@Override
	public void addItemBook(String login, String password, String title,
			String kind, String author, int nbPages) throws BadEntryException,
			NotMemberException, ItemBookAlreadyExistsException {
		if (login == null || login.trim().length() < 1
				|| login.trim().equals("") || password == null
				|| password.trim().equals("") || password.trim().length() < 4
				|| title == null || title.trim().length() < 1 || kind == null
				|| author == null || nbPages <= 0) {
			throw new BadEntryException(
					"The input is not correct at addItemBook()");
		}

		else {
			int i;
			for (i = 0; i < nbMembers()
					&& members.get(i).getLogin().trim()
							.compareToIgnoreCase(login) != 0; i++)
				;
			if (i == nbMembers) {
				throw new NotMemberException("The member " + login
						+ " does not exist !");
			}

			else {
				if (members.get(i).getPassword().equals(password) == false)
					throw new NotMemberException("The member " + login
							+ " entered wrong password");
				for (i = 0; i < nbBooks
						&& itemBooks.get(i).getTitle().trim()
								.compareToIgnoreCase(title.trim()) != 0; i++)
					;
				if (i == nbBooks) {
					nbItems++;
					nbBooks++;
					itemBooks.add(new ItemBook(title, kind, author, nbPages,
							nbItems));
				} else
					throw new ItemBookAlreadyExistsException();

			}
		}

	}

	@Override
	public float reviewItemFilm(String login, String password, String title,
			float mark, String comment) throws BadEntryException,
			NotMemberException, NotItemException {
		if (login == null || login.trim().length() < 1
				|| login.trim().equals("") || password == null
				|| password.trim().equals("") || password.trim().length() < 4
				|| title == null || title.trim().length() < 1 || mark < 0
				|| mark > 5 || comment == null) {
			throw new BadEntryException(
					"The input is not correct at reviewItemBook()");
		}

		else {
			int i;
			for (i = 0; i < nbMembers()
					&& members.get(i).getLogin().trim()
							.compareToIgnoreCase(login) != 0; i++)
				;
			if (i == nbMembers) {
				throw new NotMemberException("The member " + login
						+ " does not exist !!");
			}

			else {
				if (members.get(i).getPassword() != password)
					throw new NotMemberException("The member " + login
							+ " entered wrong password");
				int j;
				for (j = 0; j < nbFilms
						&& itemFilms.get(j).getTitle().trim()
								.compareToIgnoreCase(title.trim()) != 0; j++)
					;
				if (j == nbFilms)
					throw new NotItemException("The Item " + title
							+ " does not exist!!");
				else {
					Review r = new Review(members.get(i), mark, comment,
							itemFilms.get(j));
					itemFilms.get(j).addReview(r);
					return itemFilms.get(j).getMark();
				}

			}
		}

	}

	@Override
	public float reviewItemBook(String login, String password, String title,
			float mark, String comment) throws BadEntryException,
			NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		if (login == null || login.trim().length() < 1
				|| login.trim().equals("") || password == null
				|| password.trim().equals("") || password.trim().length() < 4
				|| title == null || title.trim().length() < 1 || mark < 0
				|| mark > 5 || comment == null) {
			throw new BadEntryException(
					"The input is not correct at reviewItemBook()");
		}

		else {
			int i;
			for (i = 0; i < nbMembers()
					&& members.get(i).getLogin().trim()
							.compareToIgnoreCase(login) != 0; i++)
				;
			if (i == nbMembers) {
				throw new NotMemberException("The member " + login
						+ " does not exist !!");
			}

			else {
				if (members.get(i).getPassword().equals(password) == false)
					throw new NotMemberException("The member " + login
							+ " entered wrong password");
				int j;
				for (j = 0; j < nbBooks
						&& itemBooks.get(j).getTitle().trim()
								.compareToIgnoreCase(title.trim()) != 0; j++)
					;
				if (j == nbBooks)
					throw new NotItemException("The Item " + title
							+ " does not exist!!");
				else {
					Review r = new Review(members.get(i), mark, comment,
							itemBooks.get(j));
					itemBooks.get(j).addReview(r);
					members.get(i).addReview(r);
					return itemBooks.get(j).getMark();
				}

			}
		}

	}

	@Override
	public LinkedList<String> consultItems(String title)
			throws BadEntryException {
		if (title == null || title.trim().length() < 1)
			throw new BadEntryException(
					"The input is not correct at consultItems(" + title + ")!");
		else {
			LinkedList<String> retour = new LinkedList<String>();
			for (ItemBook i : itemBooks) {
				if (i.getTitle().toLowerCase()
						.equals(title.trim().toLowerCase()))
					retour.add(i.toString());
			}
			for (ItemFilm i : itemFilms) {
				if (i.getTitle().trim().toLowerCase()
						.equals(title.trim().toLowerCase()))
					retour.add(i.toString());
			}
			return retour;
		}
	}

	public LinkedList<Member> getMembers() {
		return members;
	}

	public void setMembers(LinkedList<Member> members_) {
		members = members_;
	}

	public String toString() {
		String retour = "There are " + nbMembers + " members ;" + nbBooks
				+ " books;" + nbFilms + " films Total " + nbItems + "\n";
		retour += "Members of the Social Network :\n";
		for (Member m : members) {
			retour += m.toString() + "\n";
		}
		retour += "Items of the Social Network :\n-- Books --\n";
		for (ItemBook i : itemBooks) {
			retour += i.toString() + "\n";
		}
		retour += "\n-- Films --\n";
		for (ItemFilm i : itemFilms) {
			retour += i.toString() + "\n";
		}
		return retour;
	}

	/*
	 * This function is used by reviewOpinion It takes the type 'f' or 'b'
	 * (without case sensitivity) for films and books respectively and return
	 * the corresponding Item Object If the item is not found it returns null
	 */

	public Item searchItem(char type, String title) throws NotItemException {
		if (type == 'f' || type == 'F') {
			int i;
			for (i = 0; i < nbFilms
					&& itemFilms.get(i).getTitle().trim().toLowerCase().equals(title.toLowerCase().trim()) == false; i++)
				;
			if (i < nbFilms)
				return itemFilms.get(i);
			else
				throw new NotItemException(
						"Unexisting Film in the Social Network " + title);
		}

		else if (type == 'b' || type == 'B') {
			int i;
			for (i = 0; i < nbBooks
					&& itemBooks.get(i).getTitle().trim().toLowerCase().equals(title.toLowerCase().trim()) == false; i++)
				;
			if (i < nbBooks)
				return itemBooks.get(i);
			else
				throw new NotItemException(
						"Unexisting Book in the Social Network " + title);
		} else
			throw new NotItemException("Unexisting Type in the Social Network "
					+ type);
	}

	/**
	 * This function will review the opinion given on a specific item by a
	 * member Note that one member cannot rate his own opinion
	 * (SameMemberException) The type parameter takes 'f' or 'b' as value
	 * (without case sensitivity) respectively for books and films The
	 * loginAuthor parameter will be the login of a member who reviewed the item
	 * "title"
	 */
	public void reviewOpinion(String login, String password, int mark,
			char type, String title, String loginAuthor)
			throws BadEntryException, NotMemberException, NotItemException,
			SameMemberException,NotReviewException {

		if (login == null || login.trim().length() < 1
				|| login.trim().equals("") || password == null
				|| password.trim().equals("") || password.trim().length() < 4
				|| title == null || title.trim().length() < 1 || mark < 0
				|| mark > 5 || loginAuthor == null || loginAuthor.equals("")) {
			throw new BadEntryException(
					"The input is not correct at reviewOpinion()");
		}

		else {
			int i;
			for (i = 0; i < nbMembers()
					&& members.get(i).getLogin().trim()
							.compareToIgnoreCase(login.trim()) != 0; i++)
				;
			if (i == nbMembers) {
				throw new NotMemberException("The member " + login
						+ " does not exist !!");
			}

			else {
				if (members.get(i).getPassword().equals(password) == false)
					throw new NotMemberException("The member " + login
							+ " entered wrong password");
				if (login.equals(loginAuthor))
					throw new SameMemberException("The member " + login
							+ " shouldn't review himself");
				else {

					Item item = searchItem(type, title);
					Member m = null;
					for (Review r : item.getReviews()) {
						if (r.getMember().getLogin().equals(loginAuthor)) {
							m = r.getMember();
							break;
						}
						;
					}
					if (m != null)
						m.setKarma(mark, m.getKarma());
					else
						throw new NotReviewException("The member "
								+ loginAuthor + " never reviewed " + title);
				}
			}
		}

	}

	/*
	 * This method will search a film title in the social network without taking
	 * the case into account The descriptions of all the corresponding books
	 * will be returned
	 */

	public LinkedList<String> searchItemBook(String title) {
		LinkedList<String> retour = new LinkedList<String>();
		for (int i = 0; i < nbBooks; i++) {
			if (itemBooks.get(i).getTitle().toLowerCase()
					.contains(title.toLowerCase())) {
				retour.add(itemBooks.get(i).toString());// The description of
														// the itemBook is added

			}

		}
		return retour;
	}

	/*
	 * This method will search a film title in the social network without taking
	 * the case into account The descriptions of all the corresponding films
	 * will be returned
	 */

	public LinkedList<String> searchItemFilm(String title) {
		LinkedList<String> retour = new LinkedList<String>();

		for (int i = 0; i < nbFilms; i++) {
			if (itemFilms.get(i).getTitle().toLowerCase()
					.contains(title.toLowerCase())) {
				retour.add(itemFilms.get(i).toString());// The description of
														// the itemFilm is added
			}

		}
		return retour;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ISocialNetworkPremium sn = new SocialNetwork();
/*		
		  try { sn.addMember("Pierre", "pass", "FIP"); sn.addMember("Serigne",
		  "pass", "FIP"); sn.addMember("Pierre1", "pass", "FIP");
		  sn.addMember("Serigne1", "pass", "FIP"); sn.addMember("Pierre2",
		  "pass", "FIP"); sn.addMember("Serigne2", "pass", "FIP");
		  
		  
		  sn.addItemBook("Pierre", "pass", "1000 et une nuits", "Contes",
		  "Inconnu", 3); sn.addItemBook("Serigne", "pass", "Sous l'orage",
		  "Histoire", "Seydou Badian", 300);
		  
		  sn.addItemFilm("Pierre", "pass", "GOT ", "Moyen Age", "DB WEISS",
		  "BENIOFF", 20); sn.addItemBook("Serigne1", "pass", "2BDMangas2",
		  "ANIME", "Aut", 3); sn.addItemFilm("Pierre2", "pass",
		  "DOUBLE Dragon", "ANIME", "Direct", "Scenarist", 2);
		  sn.addItemBook("Pierre", "pass", "3BDMangas", "ANIME", "Aut", 3);
		  sn.addItemBook("Serigne2", "pass", "3BDMangas2", "ANIME", "Aut", 3);
		  sn.addItemFilm("Pierre1", "pass", "3Film", "ANIME", "Direct",
		  "Scenarist", 2);
		  
		  
		  sn.reviewItemFilm("Serigne", "pass", "GOT", 2, "Mediocre !");
		  sn.reviewItemFilm("Serigne", "pass", "GOT", 3, "Film Pertinent  !");
		  sn.reviewItemFilm("Serigne", "pass", "GOT", 5, "Excellent!");
		  sn.reviewItemFilm("Serigne1", "pass", "GOT", 4, "Tres bien :)");
		  
		  sn.reviewItemBook("Pierre", "pass", "Sous l'orage", 2,
		  "Quel bon livre !"); sn.reviewItemBook("Serigne", "pass",
		  "Sous l'orage", 3, "Quel bon livre !"); sn.reviewItemBook("Serigne1",
		  "pass", "Sous l'orage", 4, "Quel bon livre !");
		  
		  
		  
		  sn.reviewOpinion("Pierre", "pass", 4, 'f', "GOT", "Serigne");
		  sn.reviewOpinion("Serigne", "pass", 4, 'B', "Sous l'orage",
		  "Pierre");
		  
		  
		  sn.reviewItemBook("Serigne", "pass", "3BDMangas", 5,
		  "Quel bon livre !"); sn.reviewItemBook("Pierre", "pass", "3BDMangas",
		  4, "Quel bon livre !"); sn.reviewItemBook("Serigne", "pass",
		  "3BDMangas", 5, "Quel bon livre !");
		  
		  sn.reviewItemBook("Pierre1", "pass", "3BDMangas", 4,
		  "Quel bon livre !");
		  
		  sn.reviewItemBook("Serigne1", "pass", "3BDMangas", 4,
		  "Quel bon livre !");
		  
		  
		  System.out.println(sn.toString());
		  
		  System.out.println(sn.searchItemFilm("GOT").get(0));

		  
		  }
		 
*/
		// Tests de rendement

		try {
			System.out.println("Ajout de 500 Membres et 5000 items....");
			for (int i = 1; i < 501; i++)
				sn.addMember("pseudo" + i, "pass" + i, "FIP");
			for (int i = 1; i < 2801; i++)
				sn.addItemBook("pseudo1", "pass1", "Book" + i, "Fantastique",
						"Author" + i, 300);
			for (int i = 1; i < 2201; i++)
				sn.addItemFilm("pseudo1", "pass1", "Film" + i, "Fantastique",
						"Director" + i, "Scenarist" + i, 300);
			System.out.println("Fait");

			long deb = System.currentTimeMillis();
			sn.reviewItemBook("pseudo1", "pass1", "book2800", 5, "NICE");
			long fin = System.currentTimeMillis();
			System.out.println((fin - deb) / 1000F
					+ " secondes maximum pour noter un item book");

			deb = System.currentTimeMillis();
			sn.reviewItemBook("pseudo1", "pass1", "book2800", 5, "NICE");
			fin = System.currentTimeMillis();
			System.out.println((fin - deb) / 1000F
					+ " secondes maximum pour noter un item film");

			System.out.println("EndReview");
			deb = System.currentTimeMillis();
			LinkedList<String> str = sn.consultItems("film2200");
			fin = System.currentTimeMillis();
			System.out.println((fin - deb) / 1000F
					+ " secondes maximum pour consulter  un item ");
			System.out.println("EndConsult");

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		;

	}
}
