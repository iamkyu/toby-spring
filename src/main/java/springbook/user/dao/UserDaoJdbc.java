package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-11
 */
public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

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

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user) {
        this.jdbcTemplate.update("INSERT INTO USERS(id, name, password, email, level, login, recommend) VALUES(?,?,?,?,?,?,?)",
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
        return this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE id = ?", new Object[]{userId}, this.userRowMapper);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject("SELECT count(*) FROM USERS", Integer.class);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM users");
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM USERS", this.userRowMapper);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update("UPDATE USERS SET name = ?, password = ?, email = ?, level = ?, login = ?, recommend= ? WHERE id = ?",
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getId());
    }
}

