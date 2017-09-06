package com.cactus.ctbaselibrary.http;


import com.cactus.ctbaselibrary.http.cookie.CustomCookieJar;
import com.cactus.ctbaselibrary.http.cookie.TokenCache;
import com.cactus.ctbaselibrary.http.interceptor.AppVersionInterceptor;
import com.cactus.ctbaselibrary.http.interceptor.UserAgentInterceptor;
import com.cactus.ctbaselibrary.utils.StringUtils;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/6/30.
 * <br>Description:   okhttp单例
 * <br>Alter By:
 */
public enum HttpClient {

    INSTANCE;
    private OkHttpClient client;

    HttpClient() {
        init();
    }

    private void init() {
        if (!StringUtils.checkNull(HttpConfig.APP_KEY)) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(HttpConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(HttpConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(HttpConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                    .cookieJar(new CustomCookieJar(new CookieManager(HttpConfig.persistentCookieStore, CookiePolicy.ACCEPT_ALL)))
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(new UserAgentInterceptor(HttpConfig.userAgentString))
                    .addInterceptor(new AppVersionInterceptor(HttpConfig.appVersion))
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl originalHttpUrl = original.url();

                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .addQueryParameter("appKey", HttpConfig.APP_KEY)
                                    .build();

                            // Request customization: add request headers
                            Request.Builder requestBuilder = original.newBuilder()
                                    .url(url);
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .connectTimeout(HttpConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(HttpConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(HttpConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                    .cookieJar(new CustomCookieJar(new CookieManager(HttpConfig.persistentCookieStore, CookiePolicy.ACCEPT_ALL)))
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(new UserAgentInterceptor(HttpConfig.userAgentString))
                    .addInterceptor(new AppVersionInterceptor(HttpConfig.appVersion))
                    .build();
        }

    }

    public OkHttpClient getOkHttpClient() {
        try {
            UserAgentInterceptor userAgentInterceptor = (UserAgentInterceptor) client.interceptors().get(0);
            if (HttpConfig.isNeadUserAgent && StringUtils.checkNull(userAgentInterceptor.getUserAgentHeaderValue())) {//如果UserAgent为空，则重新初始化
                HttpConfig.setUserAgent();
                init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }


}
