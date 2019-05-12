package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import java.util.Collection;

/**
 * Skeleton for the SocialNetwork
 * 
 */
public class SocialNetwork implements ISocialNetwork {

	private int nbMembers = 0;
	private int nbItems = 0;
	private int nbBooks = 0;
	private int nbFilms = 0;

	public SocialNetwork() {
		members = new LinkedList<Member>();
		items = new LinkedList<Item>();

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

		if (login == null || login.trim().length() < 1 || login.trim() == ""
				|| password == null || password.trim() == ""
				|| password.trim().length() < 4 || profile == null) {
			throw new BadEntryException("The input is not correct  at addMember()");
		}

		else {

			int i = 0;
			for (i = 0; i < nbMembers
					&& members.get(i).getLogin().trim() //Testing the existence of the member
							.compareToIgnoreCase(login.trim()) != 0; i++)
				;
			if (i == nbMembers) {
				nbMembers++;
				members.add(new Member(login, password, profile));
			} else
				throw new MemberAlreadyExistsException();

		}
		;
		// TODO Auto-generated method stub

	}

	@Override
	public void addItemFilm(String login, String password, String title,
			String kind, String director, String scenarist, int duration)
			throws BadEntryException, NotMemberException,
			ItemFilmAlreadyExistsException {
		if (login == null || login.trim().length() < 1 || login.trim() == ""
				|| password == null || password.trim() == ""
				|| password.trim().length() < 4 || title == null
				|| title.trim().length() < 1 || kind == null
				|| director == null || scenarist == null || duration <= 0)
			throw new BadEntryException("The input is not correct at addItemFilm()");

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
						
					for (i = 0; i < nbItems
							&& items.get(i).getTitle().trim()
									.compareToIgnoreCase(title.trim()) != 0; i++)
						;
					if (i == nbItems) {
						nbItems++;
						nbFilms++;
						items.add(new ItemFilm(title, kind, director,
								scenarist, duration));
					} else
						throw new ItemFilmAlreadyExistsException();

				
			}

		}
		// TODO Auto-generated method stub

	}

	@Override
	public void addItemBook(String login, String password, String title,
			String kind, String author, int nbPages) throws BadEntryException,
			NotMemberException, ItemBookAlreadyExistsException {
		if (login == null || login.trim().length() < 1 || login.trim() == ""
				|| password == null || password.trim() == ""
				|| password.trim().length() < 4 || title == null
				|| title.trim().length() < 1 || kind == null || author == null
				|| nbPages <= 0) {
			throw new BadEntryException("The input is not correct at addItemBook()");
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
				if (members.get(i).getPassword() != password)
					throw new NotMemberException("The member " + login
							+ " entered wrong password");
				for (i = 0; i < nbItems
						&& items.get(i).getTitle().trim()
								.compareToIgnoreCase(title.trim()) != 0; i++)
					;
				if (i == nbItems) {
					nbItems++;
					nbBooks++;
					items.add(new ItemBook(title, kind, author, nbPages));
				} else
					throw new ItemBookAlreadyExistsException();

			}
		}

		// TODO Auto-generated method stub

	}

	@Override
	public float reviewItemFilm(String login, String password, String title,
			float mark, String comment) throws BadEntryException,
			NotMemberException, NotItemException {
		if (login == null || login.trim().length() < 1 || login.trim() == ""
				|| password == null || password.trim() == ""
				|| password.trim().length() < 4 || title == null
				|| title.trim().length() < 1 || mark < 0 || mark > 5
				|| comment == null) {
			throw new BadEntryException("The input is not correct at reviewItemBook()");
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
				for (j = 0; j < nbItems
						&& items.get(j).getTitle().trim()
								.compareToIgnoreCase(title.trim()) != 0; j++)
					;
				if (j == nbItems)
					throw new NotItemException("The Item " + title
							+ " does not exist!!");
				else {
					Review r=new Review(members.get(i),mark,comment,items.get(j));
					items.get(j).addReview(r);
				}

			}
		}

		return 0;
	}

	@Override
	public float reviewItemBook(String login, String password, String title,
			float mark, String comment) throws BadEntryException,
			NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		if (login == null || login.trim().length() < 1 || login.trim() == ""
				|| password == null || password.trim() == ""
				|| password.trim().length() < 4 || title == null
				|| title.trim().length() < 1 || mark < 0 || mark > 5
				|| comment == null) {
			throw new BadEntryException("The input is not correct at reviewItemBook()");
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
				for (j = 0; j < nbItems
						&& items.get(j).getTitle().trim()
								.compareToIgnoreCase(title.trim()) != 0; j++)
					;
				if (j == nbItems)
					throw new NotItemException("The Item " + title
							+ " does not exist!!");
				else {
					Review r=new Review(members.get(i),mark,comment,items.get(j));
					items.get(j).addReview(r);
					members.get(i).addReview(r);
				}

			}
		}

		return 0;
	}

	@Override
	public LinkedList<String> consultItems(String title)
			throws BadEntryException {
		if (title == null || title.trim().length() < 1)
			throw new BadEntryException("The input is not correct at consultItems("+title+")!");
		else {
			LinkedList<String> retour = new LinkedList<String>();
			for (Item i : items) {
				if (i.getTitle().toLowerCase().contains(title.toLowerCase()))
					retour.add(i.getTitle());
			}
			return retour;
		}
	}


	/**
	 * @uml.property name="members"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="socialNetwork:Member"
	 */
	private LinkedList<Member> members;

	/**
	 * @uml.property name="items"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="socialNetwork:Item"
	 */
	private LinkedList<Item> items;

	/**
	 * Getter of the property <tt>items</tt>
	 * 
	 * @return Returns the items.
	 * @uml.property name="items"
	 */
	public Collection getItems() {
		return items;
	}

	/**
	 * Setter of the property <tt>items</tt>
	 * 
	 * @param items
	 *            The items to set.
	 * @uml.property name="items"
	 */
	public void setItems(LinkedList<Item> items) {
		this.items = items;
	}

	/**
	 * Getter of the property <tt>members</tt>
	 * 
	 * @return Returns the member.
	 * @uml.property name="members"
	 */
	public Collection getMembers() {
		return members;
	}

	/**
	 * Setter of the property <tt>members</tt>
	 * 
	 * @param members
	 *            The member to set.
	 * @uml.property name="members"
	 */
	public void setMembers(LinkedList<Member> members_) {
		members = members_;
	}

	public String toString() {
		String retour = "";
		retour += "Members of the Social Network :\n";
		for (Member m : members) {
			retour += m.toString()+"\n";
		}
		retour += "Items of the Social Network :\n";
		for (Item i : items) {
			retour +=i.toString()+"\n";
		}
		return retour;
	}
	
	
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ISocialNetwork sn = new SocialNetwork();
		try {
			sn.addMember("Pierre", "pass", "FIP");
			sn.addMember("Serigne", "pass", "FIP");
			sn.addMember("Pierre1", "pass", "FIP");
			sn.addMember("Serigne1", "pass", "FIP");
			sn.addMember("Pierre2", "pass", "FIP");
			sn.addMember("Serigne2", "pass", "FIP");
			
			
			sn.addItemBook("Pierre", "pass", "1BDMangas", "ANIME", "Aut", 3);
			sn.addItemBook("Serigne", "pass", "1BDMangas2", "ANIME", "Aut", 300);
			sn.addItemFilm("Pierre", "pass", "1Film", "ANIME", "Direct",
					"Scenarist", 2);
			sn.addItemBook("Serigne1", "pass", "2BDMangas2", "ANIME", "Aut", 3);
			sn.addItemFilm("Pierre2", "pass", "2Film", "ANIME", "Direct",
					"Scenarist", 2);
			sn.addItemBook("Pierre", "pass", "3BDMangas", "ANIME", "Aut", 3);
			sn.addItemBook("Serigne2", "pass", "3BDMangas2", "ANIME", "Aut", 3);
			sn.addItemFilm("Pierre1", "pass", "3Film", "ANIME", "Direct",
					"Scenarist", 2);
			
			
			sn.reviewItemFilm("Serigne", "pass", "1Film", 2, "Mediocre !");
			sn.reviewItemFilm("Serigne", "pass", "1Film", 3, "Film Pertinent  !");
			sn.reviewItemFilm("Serigne", "pass", "1Film", 5, "Excellent!");
			sn.reviewItemFilm("Serigne1", "pass", "1Film", 4, "Tres bien :)");
			
			sn.reviewItemBook("Pierre", "pass", "1BDMangas2", 2, "Quel bon livre !");
			sn.reviewItemBook("Serigne", "pass", "1BDMangas2", 3, "Quel bon livre !");
			sn.reviewItemBook("Serigne", "pass", "1BDMangas2", 5, "Quel bon livre !");
			sn.reviewItemBook("Serigne1", "pass", "1BDMangas2", 4, "Quel bon livre !");

			/*LinkedList<String> search = sn.consultItems("BD");
			for (int i = 0; i < search.size(); i++) {
				System.out.println(search.get(i));
			}*/
			System.out.println(sn.toString());
			
		} 
		catch(MemberAlreadyExistsException e){System.out.println("There is an existing member in the test");}
		catch (Exception e) {	System.out.println(e.getMessage());}
		;

	}
}
