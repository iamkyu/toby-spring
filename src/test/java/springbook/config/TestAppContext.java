package springbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import springbook.user.domain.CommonLevelUpgradePolicy;
import springbook.user.domain.UserLevelUpgradePolicy;
import springbook.user.service.DummyMailSender;
import springbook.user.service.UserService;
import springbook.user.service.UserServiceTest.TestUserService;

/**
 * @author Kj Nam
 * @since 2017-06-17
 */
@Configuration
public class TestAppContext {

    @Bean
    public UserService testUserService() {
        return new TestUserService();
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

    @Bean
    public UserLevelUpgradePolicy userLevelUpgradePolicy() {
        return new CommonLevelUpgradePolicy();
    }
}
