package team.dorm301.letterhome.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 使用Basic认证方式进行验证进行验证
                .httpBasic()
                // 添加排除路由
                .and().authorizeRequests().antMatchers("/user/forget", "/user/password", "/user").permitAll()
                // 要求SpringSecurity对后台的任何请求进行认证保护
                .and().authorizeRequests().anyRequest().authenticated()
                // 关闭Security的Session，使用Spring Session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                // 设置frameOptions为sameOrigin，否则看不见h2控制台
                .and().headers().frameOptions().sameOrigin()
                // 禁用csrf，否则403. 这个在上线的时候判断是否需要开启
                .and().csrf().disable();
    }
}
