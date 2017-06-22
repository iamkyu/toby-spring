package springbook.user.sqlservice.updatable;

import springbook.user.exception.SqlNotFountException;
import springbook.user.exception.SqlUpdateFailureException;
import springbook.user.sqlservice.UpdatableSqlRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kj Nam
 * @since 2017-06-16
 */
public class ConcurrentHashMapSqlRegistry implements UpdatableSqlRegistry {
    private Map<String, String> sqlMap = new ConcurrentHashMap<>();

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public String findSql(String key) throws SqlNotFountException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SqlNotFountException(key + "를 이용해서 SQL을 찾을 수 없습니다.");
        } else {
            return sql;
        }
    }

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {
        if (sqlMap.get(key) == null) {
            throw new SqlUpdateFailureException(key + "에 해당하는 SQL을 찾을 수 없습니다.");
        }
        sqlMap.put(key, sql);
    }

    @Override
    public void updateSql(Map<String, String> sqlMap) throws SqlUpdateFailureException {
        for (Map.Entry<String, String> entry : sqlMap.entrySet()) {
            updateSql(entry.getKey(), entry.getValue());
        }
    }
}
