package exception;

public class UsernameDoesNotExistsException extends RuntimeException{
    public UsernameDoesNotExistsException(String msg) {
        super(msg);
    }
}
