package team.dorm301.letterhome.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import team.dorm301.letterhome.config.Yunzhi;

public class HttpClient {

    public static <T> T request(Class<T> clazz) {
        return RetrofitHelper.getRetrofit().create(clazz);
    }

    private static class RetrofitHelper {
        //gson converter
        private static final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        private static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Yunzhi.getApi())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(FastJsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(OkHttpClientBuilder.build())
                .build();

        private static Retrofit getRetrofit() {
            return retrofit;
        }
    }
}
