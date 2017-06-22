package springbook.user.sqlservice;

import springbook.user.exception.SqlNotFountException;

/**
 * @author Kj Nam
 * @since 2017-06-14
 */
public interface SqlRegistry {
    void registerSql(String key, String sql);

    String findSql(String key) throws SqlNotFountException;
}
