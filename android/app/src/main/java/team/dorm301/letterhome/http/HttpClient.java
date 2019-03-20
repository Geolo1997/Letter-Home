package team.dorm301.letterhome.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import team.dorm301.letterhome.config.Yunzhi;

public class HttpClient {

    private static class RetrofitHelper {
        private static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Yunzhi.getApi())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(OkHttpClientBuilder.build())
                .build();

        private static Retrofit getRetrofit() {
            return retrofit;
        }
    }

    public static <T> T request(Class<T> clazz) {
        return RetrofitHelper.getRetrofit().create(clazz);
    }
}
