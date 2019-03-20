package team.dorm301.letterhome.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import team.dorm301.letterhome.config.Yunzhi;

public class OkHttpClientBuilder {

    /**
     * 构建OkHttpClient
     */
    public static OkHttpClient build() {
        return new OkHttpClient.Builder()
                .connectTimeout(Yunzhi.getTimeout(), TimeUnit.SECONDS)     // 连接 超时时间
                .writeTimeout(Yunzhi.getTimeout(), TimeUnit.SECONDS)       // 写操作 超时时间
                .readTimeout(Yunzhi.getTimeout(), TimeUnit.SECONDS)        // 读操作 超时时间
                .retryOnConnectionFailure(true)                            // 错误重连
                .build();
    }
}
