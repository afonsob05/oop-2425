package hva.core.exception;

/**
 * Exception thrown when there is an attempt to register an animal with a duplicate key.
 */
public class DuplicateAnimalKeyException extends Exception {
    public DuplicateAnimalKeyException(String message) {
        super(message);
    }
    public DuplicateAnimalKeyException(String message, Throwable cause) {
        super(message, cause); // Call the parent constructor with the message and cause
    }
}
