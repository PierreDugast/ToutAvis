package tests;

import exceptions.BadEntryException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.NotItemException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;


public class ReviewItemBookTest{
	
	private static int reviewItemBookBadEntryTest(ISocialNetwork sn, String login,
			String password, String title, float mark,String comment, String testId, String errorMessage) {

	
		int nbBooks = sn.nbBooks();		
		try {
			sn.reviewItemBook(login, password, title, mark, comment); 
			System.out.println("Err " + testId + " : " + errorMessage); 
			return 1; 
		} catch (BadEntryException e) { 
			if (sn.nbBooks() != nbBooks) { 
				System.out
						.println("Err "
								+ testId
								+ " : BadEntry was thrown but the number of books was changed"); 
				return 1;
			} else
				
				return 0; 
		} catch (Exception e) { 
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); 
			e.printStackTrace();
			return 1; 
		}
	}

	private static int rewiewItemBookNotMemberTest(ISocialNetwork sn, String login,
			String password, String title, float mark,String comment, String testId, String errorMessage) {

		int nbBooks = sn.nbBooks();	
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage); 
			return 1; 
		} catch (NotMemberException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out
						.println("Err "
								+ testId
								+ " : reviewItemBooksNotMember was thrown, but the number of books was changed"); 
				return 1;
			} else
				return 0; 
		} catch (Exception e) { 
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); 
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
			if (sn.nbBooks() != nbBooks) { 
				System.out.println("Err " + testId
						+ " : the number of books (" + nbBooks
						+ ") was not incremented"); 
				return 1; 
			} else
				return 0; 
		} catch (Exception e) {
			System.out
					.println("Err " + testId + " : unexpected exception " + e); 
			e.printStackTrace(); 
			return 1;
		}
	}

	
		
	private static int reviewItemBookOKTest(ISocialNetwork sn, String login,
			String password, String title, float mark,String comment, String testId) {
		int nbBooks = sn.nbBooks(); 
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			if (sn.nbBooks() != nbBooks + 1) { 
				System.out.println("Err " + testId
						+ " : the number of books (" + nbBooks
						+ ") was not incremented"); 
				return 1; 
			} else
				return 0; 
		} catch (Exception e) {
			System.out
					.println("Err " + testId + " : unexpected exception " + e); 
			e.printStackTrace(); 
			return 1;
		}
	}
	

	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();

		int nbBooks = sn.nbBooks(); // number of books in 'sn' (should be 0
									// here)
		int nbFilms = sn.nbFilms(); // number of films in 'sn' (should be 0
									// here)

		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing reviewItemBook()");

		// <=> test n°1

		// check if incorrect parameters cause reviewItemBook() to throw BadEntry
		// exception

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, null, "pass", "titre",3,"comment", "4.1",
				"reviewItemBooks() doesn't reject null logins");
	
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(
				sn,
				" ",
				"pass", "titre",3,"comment", "4.2",
				"reviewItemBooks() doesn't reject logins that don't contain at least one character other than space");
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "B", null,"titre",3,"comment", "4.3",
				"reviewItemBooks() doesn't reject null passwords");
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(
				sn,
				"B",
				"   qwd ","titre",3,"comment", "4.4",
				"reviewItemBooks() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "BBBB", "bbbb", null,3,"comment", "4.5",
				"reviewItemBooks() doesn't reject null titles");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "BBBB", "bbbb", " ",3,"comment", "4.6",
				"reviewItemBooks() doesn't reject titles that don't contain at least one character other than space");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "BBBB", "bbbb", "titre",-1,"comment", "4.7",
				"reviewItemBooks() doesn't reject marks that isn't greater or equals to 0");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "BBBB", "bbbb", "titre",6,"comment", "4.8",
				"reviewItemBooks() doesn't reject marks that isn't lesser or equals to 5");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "BBBB", "bbbb", "titre",4,null, "4.9",
				"reviewItemBooks() doesn't reject null comments");

		// <=> test n°2
		//populate with 1 member

		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "BBBB", "bbbb","titre",3,"comment", "5.0");

		
		nbTests++;
		nbErrors += rewiewItemBookNotMemberTest(sn, new String("AAAA"), "bbbb","titre",3,"comment", "5.1",
				"The login of the first member was accepted as login for a new book");
		
		nbTests++;
		nbErrors += rewiewItemBookNotMemberTest(sn, "BBBB", new String("aaaa"),"titre",3,"comment", "5.2",
				"The password of the first member was accepted as password for a new book");
		
		// <=> test n°3
		
		
		nbTests++;
		nbErrors += reviewItemBookNotItemTest(sn, "BBBB", "bbbb",new String("rouge"),3,"comment", "6.1",
				"The title of the first book was accepted as title for a new book");


		
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