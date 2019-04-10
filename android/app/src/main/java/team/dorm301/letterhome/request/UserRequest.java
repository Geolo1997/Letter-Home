package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import team.dorm301.letterhome.entity.User;

public interface UserRequest {

    @POST("user")
    Observable<Void> register(@Body User user);

    @PUT("user/password")
    Observable<Void> updatePassword(@Body User user);

    @GET("user/currentLoginUser")
    Observer<User> getMyProfile();

    @PUT("user")
    Observable<Void> updateProfile(@Body User user);

    @PUT("user/forget")
    Observable<String> forgetPassword(@Query("username") String username);
}
