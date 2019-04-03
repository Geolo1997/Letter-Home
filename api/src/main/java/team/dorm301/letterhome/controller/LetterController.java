package team.dorm301.letterhome.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.serialize.LetterJsonView;
import team.dorm301.letterhome.service.LetterService;

import java.util.List;

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

    @GetMapping
    @JsonView(LetterJsonView.getLetterList.class)
    public List<Letter> getLetterList() {
        return letterService.getLetterList();
    }
}
