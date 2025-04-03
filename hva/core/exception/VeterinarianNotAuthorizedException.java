package hva.core.exception;

/**
 * Exception thrown when a veterinarian is not authorized to perform an operation.
 */
public class VeterinarianNotAuthorizedException extends Exception {
    public VeterinarianNotAuthorizedException(String message) {
        super(message);
    }
    public VeterinarianNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}