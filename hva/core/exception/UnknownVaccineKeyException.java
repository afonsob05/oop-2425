package hva.core.exception;

/**
 * Exception thrown when an operation is attempted with an unknown vaccine key.
 */
public class UnknownVaccineKeyException extends Exception {
    public UnknownVaccineKeyException(String message) {
        super(message);
    }
    public UnknownVaccineKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}