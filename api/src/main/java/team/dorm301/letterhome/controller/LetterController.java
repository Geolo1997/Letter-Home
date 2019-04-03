package team.dorm301.letterhome.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.service.LetterService;

@RestController
@RequestMapping("letter")
public class LetterController {

    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @PostMapping
    public void send(@RequestBody Letter letter) {
        letterService.send(letter);
    }
}
