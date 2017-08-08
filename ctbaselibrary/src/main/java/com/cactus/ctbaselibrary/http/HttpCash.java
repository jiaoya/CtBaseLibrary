package com.cactus.ctbaselibrary.http;

import com.cactus.ctbaselibrary.utils.SharedPreferencesUtils;

/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/7/27.
 *     Desc         :
 *     Alter By     :
 * </pre>
 */
public class HttpCash {

    public static String getUserAgent() {
        return SharedPreferencesUtils.getString("UserAgent", "");
    }

    public static void setUserAgent(String userAgent) {
        SharedPreferencesUtils.save("UserAgent", userAgent);
    }
}
