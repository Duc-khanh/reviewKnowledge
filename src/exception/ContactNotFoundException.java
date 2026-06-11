package exception;

public class ContactNotFoundException extends AppException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
