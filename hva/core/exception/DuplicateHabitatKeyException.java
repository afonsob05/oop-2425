package hva.core.exception;

/**
 * Exception thrown when there is an attempt to register a habitat with a duplicate key.
 */
public class DuplicateHabitatKeyException extends Exception {
    public DuplicateHabitatKeyException(String message) {
        super(message);
    }

    // Constructor with a message and cause
    public DuplicateHabitatKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
