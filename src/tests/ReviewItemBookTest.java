package tests;

import exceptions.BadEntryException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.NotItemException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;
/* 
 * Tests for opinion.SocialNetwork.reviewItemBook method
 * @author Pierre Dugast
 * Date 01/05/2019
 * */

public class ReviewItemBookTest{
	
	private static int reviewItemBookBadEntryTest(ISocialNetwork sn, String login,
			String password, String title, float mark,String comment, String testId, String errorMessage) {

		int nbBooks = sn.nbBooks();		
		
		try {
			sn.reviewItemBook(login, password, title, mark, comment); 
			System.out.println("Err " + testId + " : " + errorMessage); 
			return 1; 
		} 
		
		catch (BadEntryException e) { 
			if (sn.nbBooks() != nbBooks) { 
				System.out
						.println("Err "+ testId+ " : BadEntry was thrown but the number of books was changed"); 
				return 1;
			} 
			else return 0; 
			
		} 
		
		catch (Exception e) { 
			System.out.println("Err " + testId + " : unexpected exception. "+ e); 
			e.printStackTrace();
			return 1; 
		}
	}

	private static int reviewItemBookNotMemberTest(ISocialNetwork sn, String login,
			String password, String title, float mark,String comment, String testId, String errorMessage) {

		int nbBooks = sn.nbBooks();	
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage); 
			return 1; 
		} 
		
		catch (NotMemberException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Err "+ testId+ " : reviewItemBooksNotMember was thrown, but the number of books was changed"); 
				return 1;
			} 
			else return 0; 
		} 
		
		catch (Exception e) { 
			System.out.println("Err " + testId + " : unexpected exception. "+ e); 
			e.printStackTrace();
			return 1; 
		}
	}

	private static int reviewItemBookNotItemTest(ISocialNetwork sn, String login,
			String password, String title, float mark,String comment, String testId, String errorMessage) {
		
		int nbBooks = sn.nbBooks(); 
		
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			if (sn.nbBooks() == nbBooks+1) { 
				System.out.println("Err " + testId+ " : the number of books (" + nbBooks+ ") was  incremented"); 
				return 1; 
			} 
			else return 0; 
		} 
		catch(NotItemException e){return 0;}
		catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e); 
			e.printStackTrace(); 
			return 1;
		}
	}

	
		
	private static int reviewItemBookOKTest(ISocialNetwork sn, String login,
			String password, String title, float mark,String comment, String testId) {
		int nbBooks = sn.nbBooks(); 
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			if (sn.nbBooks() == nbBooks + 1) { 
				System.out.println("Err " + testId+ " : the number of books (" + nbBooks+ ") was  incremented"); 
				return 1; 
			} 
			
			else return 0; 
		} 

		catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e); 
			e.printStackTrace(); 
			return 1;
		}
	}
	

	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();
		try{sn.addMember("A new member", "a password", "test profile");}
		catch(Exception e) {System.out.println("User not created!!");};
		try{sn.addItemBook("A new member", "a password", "un titre","un genre","auteur",15);}
		catch(Exception e) {System.out.println("lIVRE NON AJOUTÉ !!");};
		int nbBooks = sn.nbBooks(); // number of books in 'sn' (should be 0
		// here)
		int nbFilms = sn.nbFilms(); // number of films in 'sn' (should be 0
//here)
		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing reviewItemBook()");

		// <=> test n°1

		// check if incorrect parameters cause reviewItemBook() to throw BadEntry
		// exception

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, null, "password", "un titre",3,"un commentaire", "4.1",
				"reviewItemBooks() doesn't reject null logins");
	
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn," ","password", "un titre",3,"un commentaire", "4.2",
				"reviewItemBooks() doesn't reject logins that don't contain at least one character other than space");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "login", null,"un titre",3,"un commentaire", "4.3",
				"reviewItemBooks() doesn't reject null passwords");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn,"login","   qwd ","un titre",3,"un commentaire", "4.4",
				"reviewItemBooks() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "login", "password", null,3,"un commentaire", "4.5",
				"reviewItemBooks() doesn't reject null titles");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "login", "password", " ",3,"un commentaire", "4.6",
				"reviewItemBooks() doesn't reject titles that don't contain at least one character other than space");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "login", "password", "un titre",-1,"un commentaire", "4.7",
				"reviewItemBooks() doesn't reject marks that aren't greater or equals to 0");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "login", "password", "un titre",6,"un commentaire", "4.8",
				"reviewItemBooks() doesn't reject marks that aren't lesser or equals to 5");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "login", "password", "un titre",4,null, "4.9",
				"reviewItemBooks() doesn't reject null comments");

		// <=> test n°2
		//populate with 1 member
/*
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "BBBB", "bbbb","titre",3,"comment", "5.0");
		*/

		
		nbTests++;
		nbErrors += reviewItemBookNotMemberTest(sn, "Unregistered", "a password","titre",3,"comment", "5.1",
				"reviewItemBook does not reject unregistered members");
		
		nbTests++;
		nbErrors += reviewItemBookNotMemberTest(sn, "A new member", "wrong password","titre",3,"comment", "5.2",
				"reviewItemBook does not reject member with wrong password");
		
		
		
		// <=> test n°3
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "A new member", "a password","un titre",3,"un commentaire", "6.0");
		
		nbTests++;
		nbErrors += reviewItemBookNotItemTest(sn, "A new member", "a password","un autre titre",3,"un commentaire", "6.1",
				"reviewItemBook does not reject items with wrong title");


		
		nbTests++;
		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out
					.println("Error : the number of films was unexepectedly changed by reviewItemBook()");
			nbErrors++;
		}
		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out
					.println("Error : the number of books was unexepectedly changed by reviewItemBook()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		// Print a summary of the tests and return test results
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("ReviewItemBookTest : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in ReviewItemBookTest test code - Can't return valuable test results");
			return null;
			}
		}
	
	
	/**
	 * Launches test()
	 * @param args not used
	 */
	public static void main(String[] args) {
		test();
	}

}
