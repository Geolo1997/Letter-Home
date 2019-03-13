package team.dorm301.letterhome.network;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println(chain.request().toString());
        return chain.proceed(chain.request());
    }
}
