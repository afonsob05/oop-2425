package hva.core.exception;

/**
 * Exception thrown when there is an attempt to register a tree with a duplicate key.
 */
public class DuplicateTreeKeyException extends Exception {
    public DuplicateTreeKeyException(String message) {
        super(message);
    }
    public DuplicateTreeKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
