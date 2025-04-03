package hva.core.exception;

/**
 * Exception thrown when there is an attempt to register a vaccine with a duplicate key.
 */
public class DuplicateVaccineKeyException extends Exception {
    public DuplicateVaccineKeyException(String message) {
        super(message);
    }
    public DuplicateVaccineKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
