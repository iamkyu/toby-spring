package springbook.user.service;

import springbook.user.dao.UserDao;

/**
 * @author Kj Nam
 * @since 2017-06-01
 */
public class UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
