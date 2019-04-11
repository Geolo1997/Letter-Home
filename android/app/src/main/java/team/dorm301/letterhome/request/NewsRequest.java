package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import team.dorm301.letterhome.entity.News;

public interface NewsRequest {

    @GET("/news")
    Observable<List<News>> getNewsList(@Query("size") int size);
}
