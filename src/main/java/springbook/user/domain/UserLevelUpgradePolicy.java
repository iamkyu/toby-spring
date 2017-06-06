package springbook.user.domain;

/**
 * @author Kj Nam
 * @since 2017-06-02
 */
public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);
}
