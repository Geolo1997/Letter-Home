package team.dorm301.letterhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.repository.LetterRepository;

@Service
public class LetterServiceImpl implements LetterService {

    private final LetterRepository letterRepository;
    private final UserService userService;

    @Autowired
    public LetterServiceImpl(LetterRepository letterRepository, UserService userService) {
        this.letterRepository = letterRepository;
        this.userService = userService;
    }

    @Override
    public void send(Letter letter) {
        letter.setUser(userService.getCurrentLoginUser());
        letterRepository.save(letter);
    }
}
