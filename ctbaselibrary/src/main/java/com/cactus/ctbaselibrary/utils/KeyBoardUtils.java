package com.cactus.ctbaselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/8/5.
 *     Desc         :
 *     Alter By     :
 * </pre>
 */
public class KeyBoardUtils {

    public static void observeSoftKeyboard(final Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousKeyboardHeight = -1;
            boolean keyBoardIsShow = false;

            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int displayHeight = rect.bottom - rect.top;
                int height = decorView.getHeight();
                int keyboardHeight = height - displayHeight;
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (double) displayHeight / height > 0.8;
                    if (hide && !keyBoardIsShow) {
                        listener.onSoftKeyBoardChange(keyboardHeight, false);
                        keyBoardIsShow = true;
                    } else if (!hide && keyBoardIsShow) {
                        listener.onSoftKeyBoardChange(keyboardHeight, true);
                        keyBoardIsShow = false;
                    }

                    //listener.onSoftKeyBoardChange(keyboardHeight, !hide);
                }
                previousKeyboardHeight = height;
            }
        });
    }

    public interface OnSoftKeyboardChangeListener {
        void onSoftKeyBoardChange(int softKeybardHeight, boolean visible);
    }


    /**
     * 获取焦点并打开键盘
     *
     * @param view
     */
    public static void openEditTextKeyBoard(final EditText view) {
        //获取焦点
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        //打开软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(view, 0);
                           }
                       },
                100);
    }


    /**
     * 关闭整队输入框的输入法
     */
    public static void hideKeyboard(Activity activity, View view) {
        ((InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).
                hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
