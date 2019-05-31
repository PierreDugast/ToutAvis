package opinion;

import java.util.LinkedList;

import exceptions.NotReviewException;
import exceptions.SameMemberException;
import exceptions.BadEntryException;
import exceptions.NotMemberException;
import exceptions.NotItemException;

public interface ISocialNetworkPremium extends ISocialNetwork {

	public void reviewOpinion(String login,String password,int mark,char type,String title,String loginAuthor)
		throws SameMemberException,BadEntryException,NotMemberException,NotItemException,NotReviewException;
	/*
	 * 
	 * 
	 */
	
	public LinkedList<String> searchItemBook(String title);
	public LinkedList<String> searchItemFilm(String title);

}
