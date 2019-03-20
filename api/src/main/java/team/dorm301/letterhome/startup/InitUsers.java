package team.dorm301.letterhome.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitUsers implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(InitUsers.class);

    private final UserRepository userRepository;

    @Autowired
    public InitUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            logger.info("用户不为空，退出");
            return;
        }

        List<User> userList = new ArrayList<>();

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setName("系统管理员");
        userList.add(admin);

        userRepository.saveAll(userList);
    }
}
