package team.dorm301.letterhome.service;

import team.dorm301.letterhome.entity.Letter;

import java.util.List;

public interface LetterService {

    void send(Letter letter);

    List<Letter> getLetterList();
}
