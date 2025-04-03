package hva.core.exception;

/**
 * Exception thrown when an operation is attempted with an unknown species key.
 */
public class UnknownSpeciesKeyException extends Exception {
    public UnknownSpeciesKeyException(String message) {
        super(message);
    }
    public UnknownSpeciesKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
