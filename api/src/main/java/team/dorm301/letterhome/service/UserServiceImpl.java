package team.dorm301.letterhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.exception.UsernameDuplicateException;
import team.dorm301.letterhome.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        User persistUser = userRepository.findUserByUsername(user.getUsername());
        if (persistUser != null) {
            throw new UsernameDuplicateException("用户名已被占用");
        }
        userRepository.save(user);
    }
}
