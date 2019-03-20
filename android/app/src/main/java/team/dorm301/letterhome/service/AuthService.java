package team.dorm301.letterhome.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * 认证Service 登录注销
 */
public interface AuthService {

    @GET("/auth/login")
    Observable<Void> login(@Header("Authorization") String basicAuth);
}
