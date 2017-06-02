package springbook.user.domain;

import static springbook.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static springbook.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;

/**
 * @author Kj Nam
 * @since 2017-06-02
 */
public class CommonLevelUpgradePolicy implements UserLevelUpgradePolicy {
    @Override
    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC  : return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER : return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD   : return false;
            default     : throw new IllegalArgumentException("Unknown Level : " + currentLevel);
        }
    }
}