package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-05-11
 */
public class UserDaoTest {
    private UserDao dao;

    @Before
    public void setUp() throws SQLException {
        dao = new UserDao();
        DataSource dataSource = new SingleConnectionDataSource(
                "jdbc:h2:~/apps/h2db/crm;AUTO_SERVER=TRUE", "sa", "sa", true
        );
        dao.setDataSource(dataSource);

        dao.deleteAll();
    }

    @Test
    public void add() throws Exception {
        User user = new User();
        user.setId("iamkyu");
        user.setName("kyukyu");
        user.setPassword("password");

        dao.add(user);
        assertThat(dao.getCount(), is(1));

        User user2 = dao.get(user.getId());
        assertThat(user.getId(), is(user2.getId()));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        //given when
        dao.get("unknown_id");

        //then
        //exception
    }
}
