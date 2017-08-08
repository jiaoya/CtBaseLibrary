package com.cactus.ctbaselibrary.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/3/21 14:27
 * <br>Description:   activity管理
 * <br>Alter By:
 */

public class ActivityStackManager {
    private static Stack<Activity> activityStack; // 保存activity的栈
    private static ActivityStackManager instance; // 当前类的实例

    private ActivityStackManager() {
    }

    /**
     * 返回当前类的实例（单例模式）
     */
    public static ActivityStackManager getAppManager() {
        if (instance == null) {
            instance = new ActivityStackManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
        activity = null;
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /***
     * 获取指定类名的Activity实例
     * @param cls
     * @return
     */
    public Activity getByActivity(Class<?> cls) {
        Activity activity = null;
        for (Activity a : activityStack) {
            if (a.getClass().equals(cls)) {
                activity = a;
            }
        }
        return activity;
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
//	        Intent startIntent = new Intent(context, MYGpsService.class);
//	        context.stopService(startIntent);
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
