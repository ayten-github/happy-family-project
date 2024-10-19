package az.edu.strangers.exception;

public class FamilyOverflowException extends RuntimeException{
    public FamilyOverflowException() {
        super();
    }

    public FamilyOverflowException(String message) {
        super(message);
    }
}
