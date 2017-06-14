package springbook.user.sqlservice;

import springbook.user.exception.SqlNotFountException;
import springbook.user.exception.SqlRetrievalFailureException;

import javax.annotation.PostConstruct;

/**
 * @author Kj Nam
 * @since 2017-06-14
 */
public class BaseSqlService implements SqlService {
    protected SqlReader sqlReader;
    protected SqlRegistry sqlRegistry;

    public void setSqlReader(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
    }

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    @PostConstruct
    public void loadSql() {
        this.sqlReader.read(this.sqlRegistry);
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        try {
            return this.sqlRegistry.findSql(key);
        } catch (SqlNotFountException e) {
            throw new SqlRetrievalFailureException(e);
        }
    }
}
