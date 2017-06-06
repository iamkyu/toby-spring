package springbook.user.dao;

import springbook.user.domain.User;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-31
 */
public interface UserDao {
    void add(User user);

    User get(String userId);

    int getCount();

    void deleteAll();

    List<User> getAll();

    void update(User user);
}
