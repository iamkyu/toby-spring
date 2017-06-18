package springbook.user.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.sqlservice.SqlService;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-11
 */
@Repository
public class UserDaoJdbc implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired private SqlService sqlService;

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setLevel(Level.valueOf(rs.getInt("level")));
        user.setLogin(rs.getInt("login"));
        user.setRecommend(rs.getInt("recommend"));
        return user;
    };

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user) {
        logger.debug("Add User: {}", user);
        this.jdbcTemplate.update(this.sqlService.getSql("userAdd"),
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend());
    }

    @Override
    public User get(String userId) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGet"),
                new Object[]{userId}, this.userRowMapper);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGetCount"),
                Integer.class);
    }

    @Override
    public void deleteAll() {
         this.jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userGetAll"),
                this.userRowMapper);
    }

    @Override
    public void update(User user) {
        logger.debug("Update user: {}", user);
        this.jdbcTemplate.update(this.sqlService.getSql("userUpdate"),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getId());
    }
}

