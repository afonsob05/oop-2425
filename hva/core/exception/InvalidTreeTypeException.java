package hva.core.exception;

/**
 * Exception thrown when an invalid tree type is assigned during registration or updates.
 */
public class InvalidTreeTypeException extends Exception {
    public InvalidTreeTypeException(String message) {
        super(message);
    }
    public InvalidTreeTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
