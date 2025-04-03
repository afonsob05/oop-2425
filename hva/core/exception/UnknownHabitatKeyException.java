package hva.core.exception;

/**
 * Exception thrown when an operation is attempted with an unknown habitat key.
 */
public class UnknownHabitatKeyException extends Exception {
    public UnknownHabitatKeyException(String message) {
        super(message);
    }
    public UnknownHabitatKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
