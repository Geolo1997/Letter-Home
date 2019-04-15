package team.dorm301.letterhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

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
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(target);
//        message.setFrom(sender);
//        message.setSubject(subject);
//        message.setText(content);
//
//        logger.debug("发送邮件");
//        javaMailSender.send(message);
        try {
            sendMail(target, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sendMail(String target, String title, String centent) throws Exception {
        Properties properties = new Properties();// 创建Properties对象
        properties.setProperty("mail.transport.protocol", "smtp");// 设置传输协议
        properties.put("mail.smtp.host", "smtp.syin.xyz");
        properties.put("mail.debug", "true");//便于调试
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        final String em = "geolo1997@163.com";
        final String ep = "letterhome";
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(em, ep);
            }
        };// 使用验证，创建一个Authenticator
        Session session = Session.getDefaultInstance(properties, auth);// 根据Properties，Authenticator创建Session
        session.setDebug(true);
        Message message = new MimeMessage(session);// Message存储发送的电子邮件信息
        message.setFrom(new InternetAddress("geolo1997@163.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(target));// 设置收信邮箱
        // 指定邮箱内容及ContentType和编码方式
        message.setContent(centent, "text/html;charset=utf-8");
        message.setSubject(title);// 设置主题
        message.setSentDate(new Date());// 设置发信时间
        Transport.send(message);// 发送
    }
}
