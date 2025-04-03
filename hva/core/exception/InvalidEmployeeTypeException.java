package hva.core.exception;

/**
 * Exception thrown when an invalid employee type is assigned during registration or updates.
 */
public class InvalidEmployeeTypeException extends Exception {
    public InvalidEmployeeTypeException(String message) {
        super(message);
    }
    public InvalidEmployeeTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
