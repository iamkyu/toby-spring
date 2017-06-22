package springbook.user.exception;

/**
 * @author Kj Nam
 * @since 2017-06-14
 */
public class SqlNotFountException extends RuntimeException {
    public SqlNotFountException(String message) {
        super(message);
    }

    public SqlNotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}
