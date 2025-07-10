package exception;

public class UsernameOrEmailAlreadyExistsException extends RuntimeException {
    public UsernameOrEmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
