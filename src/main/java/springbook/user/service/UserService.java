package springbook.user.service;

import springbook.user.domain.User;

/**
 * @author Kj Nam
 * @since 2017-06-06
 */
public interface UserService {
    void upgradeLevels();

    void add(User user);
}
