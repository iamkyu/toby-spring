package springbook.user.sqlservice;

/**
 * @author Kj Nam
 * @since 2017-06-13
 */
public class SqlRetrievalFailureException extends RuntimeException {
    public SqlRetrievalFailureException(String message) {
        super(message);
    }

    public SqlRetrievalFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
