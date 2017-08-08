package com.cactus.ctbaselibrary.http.cookie;

import android.text.TextUtils;
import android.webkit.CookieManager;

import com.cactus.ctbaselibrary.http.HttpConfig;
import com.cactus.ctbaselibrary.utils.SharedPreferencesUtils;

/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/8/1.
 *     Desc         :
 *     Alter By     :
 * </pre>
 */
public class TokenCache {
    private static String tokenMemoryCache = "";

    public static void saveToken(String token) {
        tokenMemoryCache = token;
        SharedPreferencesUtils.save("tokenCache", token);
    }

    public static String getToken() {
        String a = SharedPreferencesUtils.getString("tokenCache", "");
        return TextUtils.isEmpty(tokenMemoryCache) ? SharedPreferencesUtils.getString("tokenCache", "") : tokenMemoryCache;
    }


    public static void clearToken() {
        tokenMemoryCache = "";
        SharedPreferencesUtils.save("tokenCache", "");
        HttpConfig.clearCookie();

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(HttpConfig.BASE_URL, "token=");
    }
}
