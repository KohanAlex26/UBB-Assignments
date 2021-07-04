package lab8.core.exceptions;

/**
 * @author oprea
 * Exception class related to validating entities
 */

public class ValidatorException extends MyException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
