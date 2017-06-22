package springbook.user.exception;

/**
 * @author Kj Nam
 * @since 2017-06-16
 */
public class SqlUpdateFailureException extends RuntimeException {
    public SqlUpdateFailureException(String message) {
        super(message);
    }

    public SqlUpdateFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
