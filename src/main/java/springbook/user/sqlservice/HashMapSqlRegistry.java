package springbook.user.sqlservice;

import springbook.user.exception.SqlNotFountException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kj Nam
 * @since 2017-06-14
 */
public class HashMapSqlRegistry implements SqlRegistry {
    private Map<String, String> sqlMap = new HashMap<>();

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public String findSql(String key) throws SqlNotFountException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SqlNotFountException(key + "에 대한 SQL을 찾을 수 없습니다");
        } else {
            return sql;
        }
    }
}
