package team.dorm301.letterhome.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mengyunzhi.core.service.CommonService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthControllerTest.class);
    private static final String LOGIN_URL = "/auth/login";
    private static final String LOGOUT_URL = "/auth/logout";
    private static final String TOKEN_KEY = "token";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void securityTest() throws Exception {
        logger.debug("初始化基础变量");
        String username;
        String password;
        byte[] encodedBytes;
        MvcResult mvcResult;

        logger.debug("1. 测试用户名不存在");
        username = CommonService.getRandomStringByLength(10);
        password = "admin";
        encodedBytes = Base64.encodeBase64((username + ":" + password).getBytes());

        logger.debug("断言401");
        this.mockMvc.perform(MockMvcRequestBuilders.get(LOGIN_URL)
                .header("Authorization", "Basic " + new String(encodedBytes)))
                .andExpect(status().isUnauthorized());

        logger.debug("2. 用户名存在，但密码错误");
        username = "admin";
        password = CommonService.getRandomStringByLength(10);
        encodedBytes = Base64.encodeBase64((username + ":" + password).getBytes());

        logger.debug("断言401");
        this.mockMvc.perform(MockMvcRequestBuilders.get(LOGIN_URL)
                .header("Authorization", "Basic " + new String(encodedBytes)))
                .andExpect(status().isUnauthorized());

        logger.debug("3. 用户名密码正确");
        username = "admin";
        password = "admin";
        encodedBytes = Base64.encodeBase64((username + ":" + password).getBytes());

        logger.debug("断言200");
        mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(LOGIN_URL)
                .header("Authorization", "Basic " + new String(encodedBytes)))
                .andExpect(status().isOk())
                .andReturn();

        logger.debug("从返回体中获取token");
        String json = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(json);
        String token = jsonObject.getString("token");

        logger.debug("空的token请求后台，断言401");
        this.mockMvc.perform(MockMvcRequestBuilders.get(LOGIN_URL)
                .header(TOKEN_KEY, ""))
                .andExpect(status().isUnauthorized());

        logger.debug("加上token请求后台，断言200");
        this.mockMvc.perform(MockMvcRequestBuilders.get(LOGIN_URL)
                .header(TOKEN_KEY, token))
                .andExpect(status().isOk());

        logger.debug("用户注销");
        this.mockMvc.perform(MockMvcRequestBuilders.get(LOGOUT_URL)
                .header(TOKEN_KEY, token))
                .andExpect(status().isOk());

        logger.debug("注销后，断言该token失效");
        this.mockMvc.perform(MockMvcRequestBuilders.get(LOGIN_URL)
                .header(TOKEN_KEY, token))
                .andExpect(status().isUnauthorized());
    }
}