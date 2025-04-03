package hva.core.exception;

/**
 * Exception thrown when an animal with a given ID is not found.
 */
public class UnknownAnimalKeyException extends Exception {
  public UnknownAnimalKeyException(String message) {
    super(message);
}
public UnknownAnimalKeyException(String message, Throwable cause) {
    super(message, cause);
}
}
