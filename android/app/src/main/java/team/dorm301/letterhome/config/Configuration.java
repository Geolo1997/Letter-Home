package team.dorm301.letterhome.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 */
public class Configuration {

    private static final String API_KEY = "API";
    private static final String TIMEOUT_KEY = "TIMEOUT";

    private static Map<String, Object> config = new HashMap<>();

    private static final class Holder {
        private static final Configuration INSTANCE = new Configuration();
    }

    public static Configuration getInstance() {
        return Holder.INSTANCE;
    }

    public Configuration setApi(String API) {
        config.put(API_KEY, API);
        return this;
    }

    public String getApi() {
        return (String) config.get(API_KEY);
    }

    public Configuration setTimeout(Long timeout) {
        config.put(TIMEOUT_KEY, timeout);
        return this;
    }

    public Long getTimeout() {
        return (Long) config.get(TIMEOUT_KEY);
    }
}
