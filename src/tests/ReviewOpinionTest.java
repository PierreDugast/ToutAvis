package tests;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.SameMemberException;
import opinion.ISocialNetworkPremium;
import opinion.SocialNetwork;

public class ReviewOpinionTest {
	
	
	private static int ReviewOpinionBadEntryTest(ISocialNetworkPremium sn,
			String login, String password, int mark, char type, String title,
			String loginAuthor, String testId, String errorMessage) {

		try {
			sn.reviewOpinion(login, password, mark, type, title, loginAuthor);// Trying
																				// to
																				// review
																				// this
																				// opinion
			// Reaching this point means that no exception was thrown by
			// reviewOpinion()
			System.out.println("Err " + testId + " : " + errorMessage); // display
																		// the
																		// error
																		// message
			return 1; // and return the "error" value
		} 
		
		catch (BadEntryException e) {
			return 0;
		} 
		
		catch (Exception e) { // An exception was thrown by addMember(), but
			// it was not the expected exception BadEntry
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
	}
	
	private static int ReviewOpinionNotItemTest(ISocialNetworkPremium sn,
			String login, String password, int mark, char type, String title,
			String loginAuthor, String testId, String errorMessage){
		try{
			sn.reviewOpinion(login, password, mark, type, title, loginAuthor);
			
			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		}
		
		catch(NotItemException e){
			return 0;
		}
		
		catch (Exception e) { // An exception was thrown by addMember(), but
			// it was not the expected exception BadEntry
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
	}
	
	
	private static int ReviewOpinionNotMemberTest(ISocialNetworkPremium sn,
			String login, String password, int mark, char type, String title,
			String loginAuthor, String testId, String errorMessage){
		try{
			sn.reviewOpinion(login, password, mark, type, title, loginAuthor);
			
			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		}
		
		catch(NotMemberException e){
			return 0;
		}
		
		catch (Exception e) { // An exception was thrown by addMember(), but
			// it was not the expected exception BadEntry
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
	}
	
	private static int ReviewOpinionSameMemberTest(ISocialNetworkPremium sn,
			String login, String password, int mark, char type, String title,
			String loginAuthor, String testId, String errorMessage){
		try{
			sn.reviewOpinion(login, password, mark, type, title, loginAuthor);
			
			System.out.println("Err " + testId + " : " + errorMessage);

			return 1;
		}
		
		catch(SameMemberException e){
			return 0;
		}
		
		catch (Exception e) { // An exception was thrown by addMember(), but
			// it was not the expected exception BadEntry
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
	}
	
	private static int ReviewOpinionOKTest(ISocialNetworkPremium sn,
			String login, String password, int mark, char type, String title,
			String loginAuthor, String testId){
		try{
			sn.reviewOpinion(login, password, mark, type, title, loginAuthor);
			return 0;
		}
		catch(Exception e){
			System.out.println("Err " + testId + " : unexpected exception. "
					+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
	}

	public static TestReport test(){
		int nbErrors=0,nbTests=0;
		ISocialNetworkPremium sn=new SocialNetwork();
		try {
			sn.addMember("Pierre", "pass", "FIP");
			sn.addMember("Serigne", "pass", "FIP");	
			
			sn.addItemBook("Pierre", "pass", "1000 et une nuits", "Contes", "Inconnu", 3);
			sn.addItemBook("Serigne", "pass", "Sous l'orage", "Histoire", "Seydou Badian", 300);
			
			sn.addItemFilm("Pierre", "pass", "GOT ", "Moyen Age", "DB WEISS",
					"BENIOFF", 20);
			sn.addItemFilm("Pierre", "pass", "DOUBLE Dragon", "ANIME", "Direct",
					"Scenarist", 2);
			
			sn.reviewItemBook("Pierre","pass", "Sous l'orage", 3, "Pas mal comme livre!");
			
			sn.reviewItemFilm("Serigne", "pass", "GOT", 5, "Film Excellent!!");
		}
		catch(Exception e){
			System.out.println("Unhandled Exception "+e.getMessage());
			System.exit(0);
		}
		
		System.out.println("Testing reviewOpinion()");
		
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,null,"pass",2,'b',"Sous l'orage","Pierre","1.1","Reviews by null login" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne",null,2,'b',"Livre ","test","1.2","Null passwords" +
				" are accepted !!!");
		

		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne","pass",2,'b',null,"Serigne","1.3","Reviews for null titles" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne","pass",2,'b',"Livre ",null,"1.4","Reviews of null authors" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne","pass",-2,'b',"Livre ","Serigne","1.5","Reviews with invalid marks" +
				" are accepted !!!");
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne","pass",7,'b',"Livre ","Serigne","1.6","Reviews with invalid marks" +
				" are accepted !!!");
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"","pass",3,'b',"Livre ","Serigne","1.7","Reviews with empty logins" +
				" are accepted !!!");
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne","",3,'b',"Livre ","Serigne","1.8","Reviews with empty passwords" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne","pass",3,'b'," ","Serigne","1.9","Reviews with empty titles" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionBadEntryTest(sn,"Serigne","pass",3,'b',"Livre ","","1.10","Reviews of empty logins" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionNotMemberTest(sn,"Member","pass",5,'b',"Sous l'orage","Serigne","2.1","Reviews by inexisting members" +
				" are accepted !!!");
		nbTests++;
		nbErrors+=ReviewOpinionNotMemberTest(sn,"Serigne","wrong",5,'b',"Sous l'orage","Serigne","2.2","Reviews with wrong passwords" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionNotMemberTest(sn,"Serigne","pass",5,'b',"Sous l'orage","member","2.3","Reviews of inexisting members" +
				" are accepted !!!");
		nbTests++;
		nbErrors+=ReviewOpinionNotMemberTest(sn,"Serigne","pass",5,'f',"GOT","Pierre","5.1","Reviews of wrong members for wrong items" +
				" are accepted !!!");
		nbTests++;
		nbErrors+=ReviewOpinionNotItemTest(sn,"Serigne","pass",5,'f',"book","member","3.1","Reviews for inexisting items" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionNotItemTest(sn,"Serigne","pass",5,'f',"Sous l'orage","member","3.2","Reviews for existing items but wrong types" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionNotItemTest(sn,"Serigne","pass",5,'g',"Sous l'orage","member","3.3","Reviews for inexisting types" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionSameMemberTest(sn,"Serigne","pass",5,'b',"Sous l'orage","Serigne","4.1","Reviews of opinion by same members" +
				" are accepted !!!");
		
		nbTests++;
		nbErrors+=ReviewOpinionOKTest(sn,"Serigne","pass",5,'b',"Sous l'orage","Pierre","5.1");
		
		
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("ReviewOpinionTest : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
		
			System.out.println("Unexpected error in ReviewOpinionTest test code - Can't return valuable test results");
			return null;
			
		}
		
	}
	
	public static void main(String[] args) {
		test();
	}
	
	
	


}