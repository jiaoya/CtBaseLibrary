package com.cactus.ctbaselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cactus.ctbaselibrary.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/6/13.
 * <br>Description:   系统状态栏工具类，如果下面有些方法不起作用，考虑从window入手
 * <br>Alter By:
 */
public class StatusBarUtils {

    /**
     * 设置状态栏透明,页面内容显示进状态栏区域
     *
     * @param activity
     */
    public static void setTranslucentMode(Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置状态栏透明,页面内容显示进状态栏区域
     *
     * @param activity
     */
    public static void setTranslucentMode(Activity activity, boolean darkIcon) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility()
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && darkIcon) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (isMiui()) {
                    setMiuiStatusBarDarkIcon(window);
                } else if (isFlyme()) {
                    setMeizuStatusBarDarkIcon(window);
                }
            } else {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility()
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    /**
     * 设置状态栏颜色 着色模式
     *
     * @param activity
     * @param color
     * @param compatStatusBarView
     * @param darkIcon
     */
    public static void setTintMode(Activity activity, int color, View compatStatusBarView, boolean darkIcon) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && darkIcon) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (isMiui()) {
                    setMiuiStatusBarDarkIcon(window);
                } else if (isFlyme()) {
                    setMeizuStatusBarDarkIcon(window);
                } else {
                    if (compatStatusBarView != null) {
                        compatStatusBarView.setVisibility(View.VISIBLE);
                        compatStatusBarView.getLayoutParams().height = getStatusBarHeight(activity);
//                        compatStatusBarView.setBackgroundResource(R.drawable.compat_status_bg);
                    }
                }
            } else {
                if (compatStatusBarView != null) {
                    compatStatusBarView.setVisibility(View.GONE);
                }
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility()
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }


    public static void setDarkStatusBar(Activity activity, View compatStatusBarView) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            compatStatusBarView.setVisibility(View.VISIBLE);
            compatStatusBarView.getLayoutParams().height = getStatusBarHeight(activity);
            if (isMiui()) {
                setMiuiStatusBarDarkIcon(window);
                compatStatusBarView.setBackgroundResource(R.color.white);
            } else if (isFlyme()) {
                setMeizuStatusBarDarkIcon(window);
                compatStatusBarView.setBackgroundResource(R.color.white);
            } else {
                compatStatusBarView.setBackgroundResource(R.color.colorPrimaryDark);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isMiui()) {
                compatStatusBarView.setVisibility(View.VISIBLE);
                compatStatusBarView.getLayoutParams().height = getStatusBarHeight(activity);
                setMiuiStatusBarDarkIcon(window);
            } else {
                compatStatusBarView.setVisibility(View.GONE);
            }
        }
    }

    private static boolean isMiui() {
        return Build.MANUFACTURER.equals("Xiaomi");
    }

    private static boolean setMeizuStatusBarDarkIcon(Window window) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                value |= bit;
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * miui系统
     *
     * @param window
     */
    private static void setMiuiStatusBarDarkIcon(Window window) {
        try {
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class clazz = window.getClass();
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            tranceFlag = field.getInt(layoutParams);
            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag | darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 魅族系统
     *
     * @return
     */
    private static boolean isFlyme() {
        boolean isflyme = false;
        try {
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            isflyme = true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            isflyme = false;
        }
        return isflyme;
    }


    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return int 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        try {
            int result = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 状态栏显示隐藏，显示是沉浸式-背景透明
     *
     * @param activity
     * @param enable
     */
    public static void statusBarFullScreen(Activity activity, boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(lp);
            //activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attr);
            //activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
