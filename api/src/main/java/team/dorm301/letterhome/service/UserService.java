package team.dorm301.letterhome.service;

import team.dorm301.letterhome.entity.User;

public interface UserService {

    void register(User user);

    User getCurrentLoginUser();

    void update(User user);

    String forget(String username);

    void reset(String username, String password);

    void loginWithRandomUser();
}
