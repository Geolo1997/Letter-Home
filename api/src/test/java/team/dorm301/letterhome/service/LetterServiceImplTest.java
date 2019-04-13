package team.dorm301.letterhome.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import team.dorm301.letterhome.LetterhomeApplicationTests;
import team.dorm301.letterhome.entity.Letter;

public class LetterServiceImplTest extends LetterhomeApplicationTests {

    @Autowired
    private LetterService letterService;

    @Test
    public void send() {
        Letter letter = new Letter();
        letter.setEmailTarget("2843649052@qq.com");
        letter.setContent("测试邮件");
        letterService.send(letter);
    }
}
