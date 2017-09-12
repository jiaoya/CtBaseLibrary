package com.cactus.ctbaselibrary.http;


import android.support.annotation.NonNull;

import com.cactus.ctbaselibrary.http.cookie.PersistentCookieStore;
import com.cactus.ctbaselibrary.utils.StringUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/6/30.
 * <br>Description:
 * <br>Alter By:
 */
public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Retrofit retroftString = null;

    public static Retrofit getRetrofit(@NonNull String baseUrl) {

        if (retrofit == null || (HttpConfig.isNeadUserAgent && StringUtils.checkNull(HttpCash.getUserAgent()))
                || PersistentCookieStore.tokenChange) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(HttpClient.INSTANCE.getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofit() {
        return getRetrofit(HttpConfig.BASE_URL + HttpConfig.API_VERSION);
    }

    public static Retrofit getRetrofitString() {
        if (retroftString == null || (HttpConfig.isNeadUserAgent && StringUtils.checkNull(HttpCash.getUserAgent()))
                || PersistentCookieStore.tokenChange) {
            retroftString = new Retrofit.Builder()
                    .baseUrl(HttpConfig.BASE_URL + HttpConfig.API_VERSION)
                    .client(HttpClient.INSTANCE.getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retroftString;
    }
}
