package springbook.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Kj Nam
 * @since 2017-06-06
 */
public class DummyMailSender implements MailSender {
    private static final Logger logger = LoggerFactory.getLogger(DummyMailSender.class);

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {
        logger.debug(simpleMailMessage.toString());
    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

    }
}
