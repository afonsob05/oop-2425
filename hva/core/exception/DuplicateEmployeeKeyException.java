package hva.core.exception;

/**
 * Exception thrown when there is an attempt to register an employee with a duplicate key.
 */
public class DuplicateEmployeeKeyException extends Exception {
    public DuplicateEmployeeKeyException(String message) {
        super(message);
    }
    public DuplicateEmployeeKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
