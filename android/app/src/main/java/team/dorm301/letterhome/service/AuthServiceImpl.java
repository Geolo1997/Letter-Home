package team.dorm301.letterhome.service;

import android.util.Base64;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import team.dorm301.letterhome.entity.Auth;
import team.dorm301.letterhome.http.HttpClient;
import team.dorm301.letterhome.request.AuthRequest;

public class AuthServiceImpl implements AuthService {

    private static final String TAG = "AuthServiceImpl";

    @Override
    public Observable<Auth> login(String username, String password) {
        Log.d(TAG, "BASIC 认证");
        String credentials = username + ":" + password;
        String basicAuth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        Log.d(TAG, "请求登录");
        return HttpClient.request(AuthRequest.class)
                .login(basicAuth)
                .subscribeOn(Schedulers.io())                   // 在IO线程发起网络请求
                .observeOn(AndroidSchedulers.mainThread());     // 在主线程处理
    }
}
