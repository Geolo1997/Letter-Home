package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.*;
import team.dorm301.letterhome.entity.User;

public interface UserRequest {

    @POST("user")
    Call<Void> register(@Body User user);

    @PUT("user/password")
    Call<Void> updatePassword(@Body User user);

    @GET("user/currentLoginUser")
    Observable<User> getMyProfile();

    @PUT("user")
    Call<Void> updateProfile(@Body User user);

    @PUT("user/forget")
    Observable<String> forgetPassword(@Query("username") String username);
}
