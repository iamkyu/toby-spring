package springbook.user.sqlservice.updatable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import springbook.user.exception.SqlNotFountException;
import springbook.user.exception.SqlUpdateFailureException;
import springbook.user.sqlservice.UpdatableSqlRegistry;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Kj Nam
 * @since 2017-06-17
 */
public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry {
    JdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public void registerSql(String key, String sql) {
        template.update("INSERT INTO SQLMAP(KEY_, SQL_) VALUES(?, ?)", key, sql);
    }

    @Override
    public String findSql(String key) throws SqlNotFountException {
        try {
            return template.queryForObject("SELECT SQL_ FROM SQLMAP WHERE KEY_ = ?", String.class, key);
        } catch (EmptyResultDataAccessException e) {
            throw new SqlNotFountException(key + "에 해당하는 SQL을 찾지 못함", e);
        }
    }

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {
        int affected = template.update("UPDATE SQLMAP SET SQL_ = ? WHERE KEY_ = ?", sql, key);
        if (affected == 0) {
            throw new SqlUpdateFailureException(key + "에 해당하는 SQL을 찾지 못함");
        }
    }

    @Override
    public void updateSql(Map<String, String> sqlMap) throws SqlUpdateFailureException {
        for (Map.Entry<String, String> entry : sqlMap.entrySet()) {
            updateSql(entry.getKey(), entry.getValue());
        }
    }
}
