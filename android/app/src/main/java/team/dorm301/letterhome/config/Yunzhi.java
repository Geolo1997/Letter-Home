package team.dorm301.letterhome.config;

/**
 * 云智，全局配置辅助类
 */
public class Yunzhi {

    public static Configuration init() {
        return Configuration.getInstance();
    }

    public static String getApi() {
        return Configuration.getInstance().getApi();
    }

    public static Long getTimeout() {
        return Configuration.getInstance().getTimeout();
    }
}
