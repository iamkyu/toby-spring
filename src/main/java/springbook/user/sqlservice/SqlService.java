package springbook.user.sqlservice;

import springbook.user.exception.SqlRetrievalFailureException;

/**
 * @author Kj Nam
 * @since 2017-06-13
 */
public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
