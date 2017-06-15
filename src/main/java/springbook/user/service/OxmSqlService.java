package springbook.user.service;

import org.springframework.oxm.Unmarshaller;
import springbook.user.exception.SqlRetrievalFailureException;
import springbook.user.sqlservice.BaseSqlService;
import springbook.user.sqlservice.HashMapSqlRegistry;
import springbook.user.sqlservice.SqlReader;
import springbook.user.sqlservice.SqlRegistry;
import springbook.user.sqlservice.SqlService;
import springbook.user.sqlservice.jaxb.SqlType;
import springbook.user.sqlservice.jaxb.Sqlmap;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2017-06-15
 */
public class OxmSqlService implements SqlService {
    private final BaseSqlService baseSqlService = new BaseSqlService();
    private final OxmSqlReader oxmSqlReader = new OxmSqlReader();

    private SqlRegistry sqlRegistry = new HashMapSqlRegistry();

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.oxmSqlReader.unmarshaller = unmarshaller;
    }

    public void setSqlMapFile(String sqlMapFile) {
        this.oxmSqlReader.sqlMapFile = sqlMapFile;
    }

    @PostConstruct
    public void loadSql() {
        baseSqlService.setSqlReader(oxmSqlReader);
        baseSqlService.setSqlRegistry(sqlRegistry);
        baseSqlService.loadSql();
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        return baseSqlService.getSql(key);
    }


    private class OxmSqlReader implements SqlReader {
        private Unmarshaller unmarshaller;
        private final static String DEFAULT_SQLMAP_FILE = "sqlmap.xml";
        private String sqlMapFile = DEFAULT_SQLMAP_FILE;

        public void setUnmarshaller(Unmarshaller unmarshaller) {
            this.unmarshaller = unmarshaller;
        }

        public void setSqlMapFile(String sqlMapFile) {
            this.sqlMapFile = sqlMapFile;
        }

        @Override
        public void read(SqlRegistry sqlRegistry) {
            try {
                Source source = new StreamSource(this.getClass().getClassLoader().getResourceAsStream(sqlMapFile));
                Sqlmap sqlmap = (Sqlmap) this.unmarshaller.unmarshal(source);

                for (SqlType sql : sqlmap.getSql()) {
                    sqlRegistry.registerSql(sql.getKey(), sql.getValue());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(this.sqlMapFile + "을 가져올 수 없습니다.", e);
            }
        }
    }
}
