package springbook.user.sqlservice;

/**
 * @author Kj Nam
 * @since 2017-06-13
 */
public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
