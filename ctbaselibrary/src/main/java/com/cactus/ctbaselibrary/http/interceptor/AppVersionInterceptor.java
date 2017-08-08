package com.cactus.ctbaselibrary.http.interceptor;

import com.cactus.ctbaselibrary.http.HttpConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/6/30.
 * <br>Description:  app版本信息，设置在请求头里
 * <br>Alter By:
 */
public class AppVersionInterceptor implements Interceptor {

    /*private static final String APP_VERSION_HEADER_NAME = "X-API-Version";
    private String prefix = "xxxxx-android-";*/
    private final String appVersionHeaderValue;

    public AppVersionInterceptor(String appVersionHeaderValue) {
        this.appVersionHeaderValue = appVersionHeaderValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originalRequest = chain.request();
        final Request requestWithUserAgent = originalRequest.newBuilder()
                .removeHeader(HttpConfig.APP_VERSION_HEADER_NAME)
                .addHeader(HttpConfig.APP_VERSION_HEADER_NAME, HttpConfig.PREFIX + appVersionHeaderValue)
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
