package springbook.user.service;

import springbook.user.domain.User;
import springbook.user.exception.TestUserServiceException;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-06-18
 */
public class TestUserService extends UserServiceImpl {
    private String id = "miller";

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) {
            throw new TestUserServiceException();
        }
        super.upgradeLevel(user);
    }

    @Override
    public List<User> getAll() {
        for (User user : super.getAll()) {
            // 트랜잭션 테스트를 위해 read-only 가 적용된 메소드 안에서 update 시도
            super.update(user);
        }
        return null;
    }
}
