package springbook.user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import springbook.user.exception.SqlNotFountException;
import springbook.user.exception.SqlUpdateFailureException;
import springbook.user.sqlservice.UpdatableSqlRegistry;
import springbook.user.sqlservice.updatable.ConcurrentHashMapSqlRegistry;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-06-16
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ConcurrentHashMapSqlRegistryTest {
    UpdatableSqlRegistry sqlRegistry;

    @Before
    public void setUp() {
        sqlRegistry = new ConcurrentHashMapSqlRegistry();
        sqlRegistry.registerSql("KEY1", "SQL1");
        sqlRegistry.registerSql("KEY2", "SQL2");
        sqlRegistry.registerSql("KEY3", "SQL3");
    }


    @Test
    public void find() {
        checkFindResult("SQL1", "SQL2", "SQL3");
    }

    private void checkFindResult(String expected1, String expected2, String expected3) {
        assertThat(sqlRegistry.findSql("KEY1"), is(expected1));
        assertThat(sqlRegistry.findSql("KEY2"), is(expected2));
        assertThat(sqlRegistry.findSql("KEY3"), is(expected3));
    }

    @Test(expected = SqlNotFountException.class)
    public void unknownKey() {
        sqlRegistry.findSql("SQL9999!@#$");
    }

    @Test
    public void updateSingle() {
        sqlRegistry.updateSql("KEY2", "Modified2");
        checkFindResult("SQL1", "Modified2", "SQL3");
    }

    @Test
    public void updateMulti() {
        Map<String, String> sqlMap = new HashMap<>();
        sqlMap.put("KEY1", "Modified1");
        sqlMap.put("KEY3", "Modified3");

        sqlRegistry.updateSql(sqlMap);
        checkFindResult("Modified1", "SQL2", "Modified3");
    }

    @Test(expected = SqlUpdateFailureException.class)
    public void updateWithNotExistingKey() {
        sqlRegistry.updateSql("SQL9999!@#$", "Modified2");
    }
}
