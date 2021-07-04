package core.exceptions;

public class SportException extends RuntimeException{

    public SportException(String message) {
        super(message);
    }

    public SportException(String message, Throwable cause) {
        super(message, cause);
    }

    public SportException(Throwable cause) {
        super(cause);
    }
}
