package team.dorm301.letterhome.service;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.Call;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.LetterRequest;

public class LetterServiceImpl implements LetterService {

    @Override
    public Call<Void> sendLetter(Letter letter) {
        return HttpClient.request(LetterRequest.class)
                .sendLetter(letter);
    }

    @Override
    public Observable<List<Letter>> getSentLetters() {
        return HttpClient.request(LetterRequest.class)
                .getLetterList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
