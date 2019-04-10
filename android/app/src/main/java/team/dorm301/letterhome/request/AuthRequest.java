package team.dorm301.letterhome.request;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import team.dorm301.letterhome.entity.Auth;

/**
 * 认证Service 登录注销
 */
public interface AuthRequest {

    @GET("auth/login")
    Observable<Auth> login(@Header("Authorization") String basicAuth);

    @GET("auth/logout")
    Observable<Void> logout();
}
