package com.cactus.ctbaselibrary.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cactus.ctbaselibrary.CtBaselib;
import com.cactus.ctbaselibrary.http.cookie.PersistentCookieStore;
import com.cactus.ctbaselibrary.http.cookie.TokenCache;
import com.cactus.ctbaselibrary.utils.DeviceUtils;
import com.cactus.ctbaselibrary.utils.StringUtils;


/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/6/30.
 * <br>Description:   网络请求的配置
 * <br>Alter By:
 */
public class HttpConfig {

    public static int CONNECT_TIMEOUT = 20;
    public static int WRITE_TIMEOUT = 20;
    public static int READ_TIMEOUT = 20;
    public static PersistentCookieStore persistentCookieStore = null;
    public static boolean isNeadUserAgent = true;//是否需要UserAgent
    public static String userAgentString = HttpCash.getUserAgent();
    public static String appVersion = "";
    public static String PREFIX = "xtlive-android-";//前缀 xtlive-android-
    public static String APP_VERSION_HEADER_NAME = "X-API-Version";//X-API-Version
    public static String mProductCode = "";//产品名称/代号--和服务器约定的
    public static String BASE_URL = "";//网络请求地址
    public static String API_VERSION = "api/v1/live/";//服务器接口版本号
    public static String APP_KEY = "";//企业id，统一添加接口的请求参数中
    public static String COOKIE_KEY_URL = "*/";//Cookie根据对应的域名存储

    public static void init(Context context) {
        try {
            persistentCookieStore = new PersistentCookieStore(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        try {
            persistentCookieStore = new PersistentCookieStore(CtBaselib.mAppContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init(@NonNull String token) {

        if (CtBaselib.mAppContext != null) {
            TokenCache.saveToken(token);
            persistentCookieStore = new PersistentCookieStore(CtBaselib.mAppContext);
        } else {
            throw new NullPointerException("CtBaselib Context null");
        }

    }

    /**
     * 如果没有权限，会报出异常，这里捕获并抛弃,因为有权限并请求时会重新获取
     *
     * @throws Exception
     */
    public static void setUserAgent() {
        try {
            if (!StringUtils.checkNull(HttpCash.getUserAgent())) {
                return;
            }
            appVersion = DeviceUtils.getVersionName();

            String userAgent = "OS/Android "
                    + "OSVersion/" + DeviceUtils.getAndroidSDKINT() + " "
                    + "product/" + mProductCode + " "
                    + "IMEI/" + DeviceUtils.getIMEI() + " "
                    + "phoneBrand/" + DeviceUtils.getPhoneBrand() + " "
                    + "phoneModel/" + DeviceUtils.getPhoneModel() + " "
                    + "appVersionName/" + DeviceUtils.getVersionName() + " "
                    + "appChannel/official "
                    + "appVersionCode/" + DeviceUtils.getVersionCode();
            HttpCash.setUserAgent(userAgent);
            userAgentString = userAgent;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除Cookie
     */
    public static void clearCookie() {
        persistentCookieStore.removeAll();
    }

}
