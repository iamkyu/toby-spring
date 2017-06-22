package springbook.user.sqlservice;

import springbook.user.exception.SqlUpdateFailureException;

import java.util.Map;

/**
 * @author Kj Nam
 * @since 2017-06-16
 */
public interface UpdatableSqlRegistry extends SqlRegistry {
    void updateSql(String key, String sql) throws SqlUpdateFailureException;
    void updateSql(Map<String, String> sqlMap) throws SqlUpdateFailureException;
}
