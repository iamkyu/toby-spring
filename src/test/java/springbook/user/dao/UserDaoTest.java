package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-05-11
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;

    private UserDao dao;

    @Before
    public void setUp() {
        this.dao = context.getBean("userDao", UserDao.class);
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
