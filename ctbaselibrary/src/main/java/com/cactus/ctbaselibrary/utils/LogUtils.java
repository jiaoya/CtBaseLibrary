package com.cactus.ctbaselibrary.utils;

import android.text.TextUtils;
import android.util.Log;

import com.cactus.ctbaselibrary.BuildConfig;

/**
 * <pre>
 *     Authour      : JiaoYa
 *     Created Time : 2017/7/26.
 *     Desc         : 图片相关工具类
 *     Alter By     : 简单的日志工具类
 * </pre>
 */
public class LogUtils {

    public static boolean enableLog = BuildConfig.ENABLE_LOG;

    public static void warn(String tag, String message) {
        if (enableLog && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.w(tag, message);
        }
    }

    public static void info(String tag, String message) {
        if (enableLog && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.i(tag, message);
        }
    }

    public static void debug(String tag, String message) {
        if (enableLog && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.d(tag, message);
        }
    }

    public static void verbose(String tag, String message) {
        if (enableLog && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.v(tag, message);
        }
    }


    public static void error(String tag, String message) {
        if (enableLog && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
            Log.e(tag, message);
        }
    }

}
