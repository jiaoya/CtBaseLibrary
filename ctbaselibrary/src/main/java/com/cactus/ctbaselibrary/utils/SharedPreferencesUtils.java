package com.cactus.ctbaselibrary.utils;

import android.content.Context;

import net.grandcentrix.tray.AppPreferences;


/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/7/27.
 *     Desc         : SharedPreferenc处理
 *     Alter By     :
 * </pre>
 */
public class SharedPreferencesUtils {

    private static AppPreferences appPreferences = null;

    public static void init(Context context) {
        if (appPreferences == null && context != null) {
            appPreferences = new AppPreferences(context);
        }
    }

    public static void save(String key, boolean value) {
        appPreferences.put(key, value);
    }

    public static void save(String key, String value) {
        appPreferences.put(key, value);
    }

    public static void save(String key, int value) {
        appPreferences.put(key, value);
    }

    public static void save(String key, float value) {
        appPreferences.put(key, value);
    }

    public static void save(String key, long value) {
        appPreferences.put(key, value);
    }


    public static boolean getBoolean(String key, boolean defValue) {
        return appPreferences.getBoolean(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return appPreferences.getString(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return appPreferences.getInt(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return appPreferences.getFloat(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return appPreferences.getLong(key, defValue);
    }


    public static void remove(String key) {
        appPreferences.remove(key);
    }

    public static void removeAll() {
        appPreferences.clear();
    }
}
