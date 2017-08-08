package com.cactus.ctbaselibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/3/20 16:02
 * <br>Description:   toast工具，防止内存泄漏
 * <br>Alter By:
 */

public class ToastUtils {

    public static Toast mToast;

    /**
     * 传入文字
     */
    public static void show(String text) {
        try {
            if (StringUtils.checkNull(text)) {
                return;
            }
            if (mToast == null) {
                mToast = Toast.makeText(Utils.getContext(), text, Toast.LENGTH_SHORT);
            } else {
                //如果当前Toast没有消失， 直接显示内容，不需要重新设置
                mToast.setText(text);
            }
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        } catch (Exception e) {
            LogUtils.error("ToastUtil", "CtBaselib，没有在自定义的application中初始化");
        }

    }

    public static void showLong(String text) {
        try {
            if (StringUtils.checkNull(text)) {
                return;
            }
            if (mToast == null) {
                mToast = Toast.makeText(Utils.getContext(), text, Toast.LENGTH_LONG);
            } else {
                //如果当前Toast没有消失， 直接显示内容，不需要重新设置
                mToast.setText(text);
            }
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        } catch (Exception e) {
            LogUtils.error("ToastUtil", "CtBaselib，没有在自定义的application中初始化");
        }

    }

    /**
     * 传入资源文件
     */
    public static void show(int resId) {
        try {
            if (resId <= 0) {
                return;
            }
            try {
                if (mToast == null) {
                    mToast = Toast.makeText(Utils.getContext(), resId, Toast.LENGTH_SHORT);
                } else {
                    //如果当前Toast没有消失， 直接显示内容，不需要重新设置
                    mToast.setText(resId);
                }
                mToast.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            LogUtils.error("ToastUtil", "CtBaselib，没有在自定义的application中初始化");
        }
    }

    /**
     * 传入文字,在中间显示
     */
    public static void showCenter(String text) {
        try {
            if (StringUtils.checkNull(text)) {
                return;
            }
            if (mToast == null) {
                mToast = Toast.makeText(Utils.getContext(), text, Toast.LENGTH_SHORT);
            } else {
                //如果当前Toast没有消失， 直接显示内容，不需要重新设置
                mToast.setText(text);
            }
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        } catch (Exception e) {
            LogUtils.error("ToastUtil", "CtBaselib，没有在自定义的application中初始化");
        }

    }

    /**
     * 传入文字，带图片
     */
    public static void showImg(Context context, String text, int resImg) {
        if (context == null || StringUtils.checkNull(text)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        //添加图片的操作,这里没有设置图片和文字显示在一行的操作呢...
        LinearLayout view = (LinearLayout) mToast.getView();
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(resImg);
        view.addView(imageView);
        mToast.show();
    }
}
