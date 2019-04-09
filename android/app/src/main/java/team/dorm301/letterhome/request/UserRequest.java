package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import retrofit2.http.GET;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.entity.UserMessage;

public interface UserRequest {

    @GET("user")
    Observable<UserMessage> getProfile();

}
