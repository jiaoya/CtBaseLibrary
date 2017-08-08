package com.cactus.ctbaselibrary;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cactus.ctbaselibrary.http.HttpConfig;
import com.cactus.ctbaselibrary.utils.SharedPreferencesUtils;
import com.cactus.ctbaselibrary.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/12.
 * <br>Description:   基础要在自定义的application初始化
 * <br>Alter By:
 */
public class CtBaselib {
    public static Context mAppContext;

    private CtBaselib(Context context) {
        mAppContext = context;

    }

    /**
     * 初始化
     *
     * @param context application的context
     * @return
     */
    public static CtBaselib init(Application context) {
        Utils.init(context);
        SharedPreferencesUtils.init(context);
        //HttpConfig.init(context);
        return new CtBaselib(context);
    }

    /**
     * 初始化附带用户token
     *
     * @param context
     * @param token
     * @return
     */
    /*public static CtBaselib init(Application context, String token) {
        Utils.init(context);
        SharedPreferencesUtils.init(context);
        if (StringUtils.checkNull(token)) {
            HttpConfig.init(context);
        } else {
            HttpConfig.init(context, token);
        }
        return new CtBaselib(context);
    }*/

    /**
     * 设置okhttp请求超时时间 默认20秒
     *
     * @param time
     * @return
     */
    public CtBaselib setHttpTimeOut(int time) {
        HttpConfig.CONNECT_TIMEOUT = time;
        return this;
    }

    /**
     * 设置okhttp UserAgent  默认不需要，为""
     *
     * @param productCode 和服务器约定放在http请求头里的userAgent里的product代号{@link HttpConfig}
     * @return
     */
    public CtBaselib setHttpUserAgent(String productCode) {
        HttpConfig.isNeadUserAgent = true;
        HttpConfig.mProductCode = productCode;
        HttpConfig.setUserAgent();
        return this;
    }

    /**
     * 设置okhttp AppVersionInterceptor
     *
     * @param prefix             前缀
     * @param appVersionHeadName 名字
     * @return
     */
    public CtBaselib setHttpVsItp(String prefix, String appVersionHeadName) {
        HttpConfig.PREFIX = prefix;
        HttpConfig.APP_VERSION_HEADER_NAME = appVersionHeadName;
        return this;
    }

    /**
     * 设置网络请求地址
     *
     * @param baseurl
     * @return
     */
    public CtBaselib setHttpBaseUrl(@NonNull String baseurl) {
        try {
            HttpConfig.BASE_URL = baseurl;
            URL url = new URL(baseurl);
            HttpConfig.COOKIE_KEY_URL = url.getHost();
            HttpConfig.init();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置公司ID
     *
     * @param appKey
     * @return
     */
    public CtBaselib setCompanyId(@NonNull String appKey) {
        HttpConfig.APP_KEY = appKey;
        return this;
    }

    public static void UpdateToken(@NonNull String userToken) {
        HttpConfig.init(userToken);
    }

    /**
     * 清除Cookie
     */
    public static void clearCookie() {
        HttpConfig.clearCookie();
    }
}
