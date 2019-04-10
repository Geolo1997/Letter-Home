package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import team.dorm301.letterhome.entity.Letter;

public interface LetterRequest {

    @POST("letter")
    Observable<Void> sendLetter(@Body Letter letter);

    @GET("letter")
    Observable<List<Letter>> getLetterList();
}
