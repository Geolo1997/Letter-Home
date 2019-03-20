package team.dorm301.letterhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7 * 24 * 60 * 60)            // 启用Redis, Session一星期失效
public class LetterhomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetterhomeApplication.class, args);
	}
}
