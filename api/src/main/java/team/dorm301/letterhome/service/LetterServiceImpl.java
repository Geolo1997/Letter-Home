package team.dorm301.letterhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.repository.LetterRepository;

import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {

    private static final Logger logger = LoggerFactory.getLogger(LetterServiceImpl.class);

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;
    private final LetterRepository letterRepository;
    private final UserService userService;

    @Autowired
    public LetterServiceImpl(JavaMailSender javaMailSender, LetterRepository letterRepository, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.letterRepository = letterRepository;
        this.userService = userService;
    }

    @Override
    public void send(Letter letter) {
        logger.debug("设置发信人");
        letter.setUser(userService.getCurrentLoginUser());
        letterRepository.save(letter);

        logger.debug("如果存在目标地址，发送电子邮件");
        if (letter.getEmailTarget() != null && !letter.getEmailTarget().isEmpty()) {
            this.sendEmail(letter);
        }
    }

    @Override
    public List<Letter> getLetterList() {
        User currentLoginUser = userService.getCurrentLoginUser();
        return currentLoginUser.getLetters();
    }

    private void sendEmail(Letter letter) {
        logger.debug("新建邮件");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(letter.getEmailTarget());
        message.setFrom(sender);
        message.setText(letter.getContent());

        logger.debug("发送邮件");
        javaMailSender.send(message);
    }
}
