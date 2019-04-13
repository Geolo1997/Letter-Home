package team.dorm301.letterhome.network;

import java.io.IOException;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import team.dorm301.letterhome.config.Yunzhi;

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
//        System.out.println(chain.request().toString());
        Request origin = chain.request();
        Log.d("interceptor", origin.toString());
        String token = Yunzhi.getToken();
        if (token != null) {
            Request request = origin.newBuilder()
                    .addHeader("token", Yunzhi.getToken())
                    .build();
            Log.d("interceptor", request.toString());
            Response response = chain.proceed(request);
            Log.d("interceptor", response.toString());
            return response;
        } else {
            return chain.proceed(origin);
        }
    }
}
