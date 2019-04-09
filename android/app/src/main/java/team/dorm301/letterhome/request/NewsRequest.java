package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import team.dorm301.letterhome.entity.News;

public interface NewsRequest {

    @GET("/news/all")
    Observable<List<News>> getNewsList();
}
