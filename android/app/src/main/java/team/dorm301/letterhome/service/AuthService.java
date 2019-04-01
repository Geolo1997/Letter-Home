package team.dorm301.letterhome.service;

import io.reactivex.Observable;
import team.dorm301.letterhome.entity.Auth;

public interface AuthService {

    Observable<Auth> login(String username, String password);
}
