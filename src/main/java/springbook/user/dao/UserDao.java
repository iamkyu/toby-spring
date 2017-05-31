package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-11
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) {
        this.jdbcTemplate.update("INSERT INTO USERS(id, name, password) VALUES(?,?,?)",
                user.getId(),
                user.getName(),
                user.getPassword());
    }

    public User get(String userId) throws SQLException {
        return this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE id = ?", new Object[]{userId}, this.userRowMapper);
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("SELECT count(*) FROM USERS", Integer.class);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM users");
    }

    public List<User> getAll() {

        return this.jdbcTemplate.query("SELECT * FROM USERS", this.userRowMapper);
    }
}

