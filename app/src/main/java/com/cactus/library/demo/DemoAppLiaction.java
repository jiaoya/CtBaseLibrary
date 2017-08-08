package com.cactus.library.demo;

import android.app.Application;

import com.cactus.ctbaselibrary.CtBaselib;
import com.squareup.leakcanary.LeakCanary;

/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/7/27.
 *     Desc         :
 *     Alter By     :
 * </pre>
 */
public class DemoAppLiaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initCrash();

        CtBaselib.init(this)
                .setHttpUserAgent("demoTV")
                .setHttpVsItp("xtlive-android-", "X-API-Version")
                .setHttpBaseUrl("http://www.baidu.com/")
                .setCompanyId("598942016861412483");

        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    private void initCrash() {
        /*CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(true) //default: true
                .showErrorDetails(true) //default: true
                .showRestartButton(true) //default: true
                .trackActivities(false) //default: false
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .restartActivity(MainActivity.class) //default: null (your app's launch activity)
                .errorActivity(DefaultErrorActivity.class) //default: null (default error activity)
                .apply();*/
    }
}
