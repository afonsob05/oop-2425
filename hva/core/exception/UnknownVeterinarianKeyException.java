package hva.core.exception;

/**
 * Exception thrown when an operation is attempted with an unknown veterinarian key.
 */
public class UnknownVeterinarianKeyException extends Exception {
    public UnknownVeterinarianKeyException(String message) {
        super(message);
    }
    public UnknownVeterinarianKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}