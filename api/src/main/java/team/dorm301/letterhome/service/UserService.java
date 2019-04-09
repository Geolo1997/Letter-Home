package team.dorm301.letterhome.service;

import org.springframework.web.multipart.MultipartFile;
import team.dorm301.letterhome.entity.User;

public interface UserService {

    void register(User user);

    User getCurrentLoginUser();

    void update(User user);

    String forget(String username);

    void reset(String username, String password);

    void uploadAvatar(MultipartFile file) throws Exception;

    void loginWithRandomUser();
}
