package team.dorm301.letterhome.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.repository.UserRepository;

@Component
public class YunzhiAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public YunzhiAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(YunzhiAuthService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("根据用户名查询用户");
        User user = userRepository.findUserByUsername(username);

        logger.debug("用户为空，则抛出异常");
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        logger.debug("构建用户");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("admin"));
    }
}
