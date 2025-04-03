package hva.core.exception;

public class VaccineDamageException extends Exception {
    public VaccineDamageException(String message) {
        super(message);
    }

    public VaccineDamageException(String message, Throwable cause) {
        super(message, cause);
    }

    public VaccineDamageException(Throwable cause) {
        super(cause);
    }
}
