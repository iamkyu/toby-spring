package springbook.user.sqlservice.updatable;

import springbook.user.sqlservice.UpdatableSqlRegistry;

/**
 * @author Kj Nam
 * @since 2017-06-16
 */
public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }
}
