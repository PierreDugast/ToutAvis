package exceptions;
/**
 * 
 * @author Serigne Fall
 * This exception is raised when a review is inexisting 
 *
 */
public class NotReviewException extends Exception {
public NotReviewException(String message){
	super(message);
}
}
