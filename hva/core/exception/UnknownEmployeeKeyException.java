package hva.core.exception;

/**
 * Exception thrown when an operation is attempted with an unknown employee key.
 */
public class UnknownEmployeeKeyException extends Exception {
    public UnknownEmployeeKeyException(String message) {
        super(message);
    }
    public UnknownEmployeeKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
