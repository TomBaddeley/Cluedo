package Java;
/**
 * This class extends Exception and is invoked when a user attempts an illegal move.
 *
 * @author Tom Baddeley
 * @version 1.0
 */
public class IllegalMoveException extends Exception{
    IllegalMoveException(String message){
        super(message);
        System.out.println(message);
    }
}
