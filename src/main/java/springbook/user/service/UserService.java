package springbook.user.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.domain.UserLevelUpgradePolicy;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-06-01
 */
public class UserService {
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private UserDao userDao;

    private PlatformTransactionManager transactionManager;

    private UserLevelUpgradePolicy userLevelUpgradePolicy;

    private MailSender mailSender;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserLevelUpgradePolicy(UserLevelUpgradePolicy userLevelUpgradePolicy) {
        this.userLevelUpgradePolicy = userLevelUpgradePolicy;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void upgradeLevels() throws SQLException {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            List<User> users = userDao.getAll();
            for (User user : users) {
                if (userLevelUpgradePolicy.canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }
            }

            this.transactionManager.commit(status);

        } catch (Exception e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }

    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
        sendUpgradeMail(user);
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }

        userDao.add(user);
    }

    private void sendUpgradeMail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("admin@iamkyu.io");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText(String.format("사용자님의 등급이 %s 으로 조정되었습니다.", user.getLevel().name()));

        this.mailSender.send(mailMessage);
    }
}
