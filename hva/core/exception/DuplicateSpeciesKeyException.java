package hva.core.exception;

/**
 * Exception thrown when there is an attempt to register a species with a duplicate key.
 */
public class DuplicateSpeciesKeyException extends Exception {
    public DuplicateSpeciesKeyException(String message) {
        super(message);
    }
    public DuplicateSpeciesKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
