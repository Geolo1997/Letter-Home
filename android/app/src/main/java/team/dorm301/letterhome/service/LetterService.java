package team.dorm301.letterhome.service;

import io.reactivex.Observable;
import java.util.List;
import team.dorm301.letterhome.entity.Letter;

public interface LetterService {

    Observable<Void> sendLetter(Letter letter);

    Observable<List<Letter>> getSentLetters();
}
