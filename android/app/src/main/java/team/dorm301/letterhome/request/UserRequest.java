package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.*;
import team.dorm301.letterhome.entity.User;

public interface UserRequest {

    @POST("user")
    Observable<Void> register(@Body User user);

    @PUT("user/password")
    Observable<Void> updatePassword(@Body User user);

    @GET("user/currentLoginUser")
    Observable<User> getMyProfile();

    @PUT("user")
    Observable<Void> updateProfile(@Body User user);

    @PUT("user/forget")
    Observable<String> forgetPassword(@Query("username") String username);



}
