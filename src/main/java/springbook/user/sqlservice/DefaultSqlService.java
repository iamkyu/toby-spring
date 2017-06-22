package springbook.user.sqlservice;

/**
 * @author Kj Nam
 * @since 2017-06-14
 */
public class DefaultSqlService extends BaseSqlService {
    public DefaultSqlService() {
        setSqlReader(new JaxbXmlSqlReader());
        setSqlRegistry(new HashMapSqlRegistry());
    }
}
