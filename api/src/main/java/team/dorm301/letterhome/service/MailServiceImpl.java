package team.dorm301.letterhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEMail(String target, String subject, String content) {
        logger.debug("新建邮件");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(target);
        message.setFrom(sender);
        message.setSubject(subject);
        message.setText(content);

        logger.debug("发送邮件");
        javaMailSender.send(message);
    }
}
