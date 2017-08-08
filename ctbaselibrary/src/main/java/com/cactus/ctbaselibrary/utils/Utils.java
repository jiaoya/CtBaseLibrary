package com.cactus.ctbaselibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/26.
 * <br>Description:
 * <br>Alter By:
 */
public class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    /**
     * 将整个json字符串解析，并放置到map<String,Object>中
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> getJosn(String jsonStr) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!TextUtils.isEmpty(jsonStr)) {
            JSONObject json = new JSONObject(jsonStr);
            Iterator i = json.keys();
            while (i.hasNext()) {
                String key = (String) i.next();
                String value = json.getString(key);
                if (value.indexOf("{") == 0) {
                    map.put(key.trim(), getJosn(value));
                } else if (value.indexOf("[") == 0) {
                    map.put(key.trim(), getList(value));
                } else {
                    map.put(key.trim(), value.trim());
                }
            }
        }
        return map;
    }

    /**
     * 将单个json数组字符串解析放在list中
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> getList(String jsonStr)
            throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONArray ja = new JSONArray(jsonStr);
        for (int j = 0; j < ja.length(); j++) {
            String jm = ja.get(j) + "";
            if (jm.indexOf("{") == 0) {
                Map<String, Object> m = getJosn(jm);
                list.add(m);
            }
        }
        return list;
    }

    /**
     * 将Map转换成Javabean
     *
     * @param javabean javaBean
     * @param data     Map数据
     */
    public static Object toJavaBean(Object javabean, Map<String, Object> data) {
        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(javabean, new Object[]{data.get(field)});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return javabean;
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
