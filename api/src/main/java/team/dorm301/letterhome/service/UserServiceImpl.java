package team.dorm301.letterhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.exception.UsernameDuplicateException;
import team.dorm301.letterhome.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        User persistUser = userRepository.findUserByUsername(user.getUsername());
        if (persistUser != null) {
            throw new UsernameDuplicateException("用户名已被占用");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getCurrentLoginUser() {
        logger.debug("初始化用户");
        User user = null;

        logger.debug("获取用户认证信息");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        logger.debug("根据认证信息查询用户");
        if (authentication != null && authentication.isAuthenticated()) {
            user = userRepository.findUserByUsername(authentication.getName());
        }

        return user;
    }
}
