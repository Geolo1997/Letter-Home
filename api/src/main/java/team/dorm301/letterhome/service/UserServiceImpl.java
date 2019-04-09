package team.dorm301.letterhome.service;

import com.mengyunzhi.core.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.exception.UsernameDuplicateException;
import team.dorm301.letterhome.repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${attachment.image.directory}")
    private String imageDirectory;

    private User currentLoginUser;

    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(MailService mailService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public User getCurrentLoginUser() {
        if (currentLoginUser != null) {
            return currentLoginUser;
        }

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

    @Override
    public void update(User user) {
        User currentLoginUser = this.getCurrentLoginUser();
        currentLoginUser.setName(user.getName());
        currentLoginUser.setSex(user.getSex());
        currentLoginUser.setTelephone(user.getTelephone());
        currentLoginUser.setEmail(user.getEmail());
        userRepository.save(currentLoginUser);
    }

    @Override
    public String forget(String username) {
        User user = userRepository.findUserByUsername(username);
        String code = CommonService.getRandomStringByLength(6);
        mailService.sendEMail(user.getEmail(), "重置密码", "您的验证码为: " + code);
        return code;
    }

    @Override
    public void reset(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void uploadAvatar(MultipartFile avatar) throws Exception {
        String fileName = avatar.getOriginalFilename();
        String ext = fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
        String sha1 = CommonService.encrypt(avatar, "SHA-1");
        String avatarFileName = sha1 + "." + ext;
        Files.copy(avatar.getInputStream(), this.getPathByImageSaveName(avatarFileName), StandardCopyOption.REPLACE_EXISTING);
        User user = this.getCurrentLoginUser();
        user.setAvatar(avatarFileName);
        userRepository.save(user);
    }

    private Path getPathByImageSaveName(String name) {
        return this.getImagePath().resolve(name);
    }

    private Path getImagePath() {
        return Paths.get(this.getImageDirectory());
    }

    private String getImageDirectory() {
        return imageDirectory;
    }

    @Override
    public void loginWithRandomUser() {
        User user = new User();
        user.setName(CommonService.getRandomStringByLength(10));
        this.currentLoginUser = user;
    }
}
