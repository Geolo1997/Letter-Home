package team.dorm301.letterhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.repository.LetterRepository;

import java.util.List;

@Service
public class LetterServiceImpl implements LetterService {

    private static final Logger logger = LoggerFactory.getLogger(LetterServiceImpl.class);

    private final LetterRepository letterRepository;
    private final MailService mailService;
    private final UserService userService;

    @Autowired
    public LetterServiceImpl(LetterRepository letterRepository, MailService mailService, UserService userService) {
        this.letterRepository = letterRepository;
        this.mailService = mailService;
        this.userService = userService;
    }

    @Override
    public void send(Letter letter) {
        logger.debug("设置发信人");
        letter.setUser(userService.getCurrentLoginUser());
        letterRepository.save(letter);

        logger.debug("如果存在目标地址，发送电子邮件");
        if (letter.getEmailTarget() != null && !letter.getEmailTarget().isEmpty()) {
            mailService.sendEMail(letter.getEmailTarget(), letter.getSubject(), letter.getContent());
        }
    }

    @Override
    public List<Letter> getLetterList() {
        User currentLoginUser = userService.getCurrentLoginUser();
        return currentLoginUser.getLetters();
    }
}
