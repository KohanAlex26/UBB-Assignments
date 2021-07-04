package lab8.core.exceptions;

/**
 * @author oprea
 * Main Exception class in the project, extending RuntimeException
 */

public class MyException extends RuntimeException{
    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }
}