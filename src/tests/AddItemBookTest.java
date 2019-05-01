package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;


import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
/* 
 * Tests for opinion.SocialNetwork.addItemBook method
 * @author Serigne  Fall 
 * Date 01/05/2019
 * */
public class AddItemBookTest{
	
	private static int addItemBookBadEntryTest(ISocialNetwork sn, String login,
			String pwd, String title,String kind,String author,int nbPages,String testId, String errorMessage) {
		
		int nbBooks=sn.nbBooks();		
		
		try {
			sn.addItemBook(login, pwd, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}
		
		
		
		catch (BadEntryException e) {
			if(sn.nbBooks()!=nbBooks) {
				System.out.println("Err "+ testId+ " : BadEntry was thrown but the number of books was changed");
				return 1;
			}
			else return 0;
			
		} 
		
		catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
		
	}
	
	private static int addItemBookNotMemberTest(ISocialNetwork sn, String login,
			String pwd, String title,String kind,String author,int nbPages,String testId, String errorMessage) {

		int nbBooks=sn.nbBooks();
		
		try {
			sn.addItemBook(login, pwd, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}
		
		
		catch (NotMemberException e) {
			if(sn.nbBooks()!=nbBooks) {
				System.out.println("Err "+ testId+ " : NotMember was thrown but the number of books was changed");
				return 1;
			}
			else return 0;
		}
		
		
		catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
	}
	
	
	private static int addItemBookAlreadyExistsTest	(ISocialNetwork sn, String login,
			String pwd, String title,String kind,String author,int nbPages,String testId, String errorMessage) {
		int nbBooks=sn.nbBooks();
		
		try {
			sn.addItemBook(login, pwd, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}
		
		catch(ItemBookAlreadyExistsException e) {
			if(sn.nbBooks()!=nbBooks) {
				System.out.println("Err "+ testId+ " : ItemBookAlreadyExists was thrown but the number of books was changed");
				return 1;			
			}
			
			else return 0;
		}
		
		catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
				
	}
	
	private static int addItemBookOKTest(ISocialNetwork sn, String login,
			String pwd, String title,String kind,String author,int nbPages,String testId) {
			
		int nbBooks=sn.nbBooks();
		
		try {
			sn.addItemBook(login, pwd, title, kind, author, nbPages);
			if(sn.nbBooks()==nbBooks+1)	return 0;
			
			else {
				System.out.println("The number of books was not incremented");
				
				return 0;
			}
		}
		
		catch(Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
		
	}
	
	public static TestReport test(){
		
		ISocialNetwork sn = new SocialNetwork();

		
		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests
		
		
		System.out.println("Testing addItemBook()");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,null,"password","un titre","un genre","Un auteur",15,"1.1","addItemBook does not "
				+ "reject null logins");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"  ","password","un titre","un genre","Un auteur",15,"1.2","addItemBook does not "
				+ "reject logins that don't contain at least one character other than space");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"a login",null,"un titre","un genre","Un auteur",15,"1.3","addItemBook does not "
				+ "reject null passwords");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"a login","   ","un titre","un genre","Un auteur",15,"1.4","addItemBook does not "
				+ "reject passwords that don't contain at least one character other than space");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"a login","password",null,"un genre","Un auteur",15,"1.5","addItemBook does not "
				+ "reject null titles");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"a login","password","  ","un genre","Un auteur",15,"1.6","addItemBook does not "
				+ "reject titles that don't contain at least one character other than space");

		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"a login","password","un titre",null,"Un auteur",15,"1.7","addItemBook does not "
				+ "reject null kinds");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"a login","password","un titre","un genre",null,15,"1.8","addItemBook does not "
				+ "reject null authors");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn,"a login","password","un titre","un genre","Un auteur",-15,"1.9","addItemBook does not "
				+ "reject negative nbPages");
	
		
		try{sn.addMember("A new member", "a password", "test profile");}
		catch(Exception e) {System.out.println("User not created!!");};
		
		nbTests++;
		nbErrors+=addItemBookNotMemberTest(sn,"Unregistered","passwd","un titre","un genre","un auteur",200,"2.1","addItemBook does not"
				+ " reject unregistered members");
		
		nbTests++;
		nbErrors+=addItemBookNotMemberTest(sn,"A new member","blabla","un titre","un genre","un auteur",200,"2.2","addItemBook does not"
				+ " reject members with wrong passwords");
	
		
		nbTests++;
		nbErrors+=addItemBookOKTest(sn,"A new member","a password","un titre","un genre","un auteur",200,"3.1");
		
		
		nbTests++;
		nbErrors+=addItemBookAlreadyExistsTest(sn,"A new member","a password","un titre","un genre","un auteur",200,"3.2","addItemBook"
				+ " does not reject existing books");
		
		nbTests++;
		nbErrors+=addItemBookAlreadyExistsTest(sn,"A new member","a password","un       titre","un genre","un auteur",200,"3.3","addItemBook"
				+ " does not reject existing books");
		
		
		
		
		// Print a summary of the tests and return test results
				try{
					TestReport tr = new TestReport(nbTests, nbErrors);	
					System.out.println("AddItemBookTest : " + tr);
					return tr;	
				}
				catch (NotTestReportException e){ //This shouldn't happen
					System.out.println("Unexpected error in AddItemBookTest test code - Can't return valuable test results");
					return null;
					}
				}
		
	

	
	
	public static void main(String[] args) {
		test();
	}

	
	
	
}
