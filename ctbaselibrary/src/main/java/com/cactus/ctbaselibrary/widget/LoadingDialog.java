package com.cactus.ctbaselibrary.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cactus.ctbaselibrary.R;
import com.cactus.ctbaselibrary.utils.StringUtils;


/**
 * Copyright:    Copyright (c) 2016
 * Author:       焦亚
 * Time:         2017/2/17 11:42
 * ByAlter:
 * Description: 正佳加载框
 */

public class LoadingDialog extends AlertDialog implements View.OnClickListener, DialogInterface.OnDismissListener {

    private View mContentView;
    private TextView message;
    private LinearLayout body, replace;
    private Button btSure;
    private String content;
    private TextView tvLoadingContent;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogThem);
    }

    public LoadingDialog(Context context, String content) {
        super(context, R.style.LoadingDialogThem);
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.loading_dialog_layout, null);
        message = (TextView) mContentView.findViewById(R.id.textView_showMsg);
        body = (LinearLayout) mContentView.findViewById(R.id.body);
        replace = (LinearLayout) mContentView.findViewById(R.id.replace);
        btSure = (Button) mContentView.findViewById(R.id.bt_sure);
        tvLoadingContent = (TextView) mContentView.findViewById(R.id.message);
        setContentView(mContentView);
        btSure.setOnClickListener(this);
        this.setOnDismissListener(this);
        if (!StringUtils.checkNull(content)) {
            tvLoadingContent.setText(content);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * 设置加载完成后的内容，默认1s后消失
     *
     * @param msg 内容
     */
    public void alterMsgIsShowing(String msg) {
        alterMsgIsShowing(msg, 1000);
    }

    /**
     * 设置加载完成后的内容，多少秒后消失
     *
     * @param msg  内容
     * @param time 时间
     */
    public void alterMsgIsShowing(String msg, int time) {
        message.setText(msg);
        body.setVisibility(View.GONE);
        replace.setVisibility(View.VISIBLE);
        message.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingDialog.this.dismiss();
            }
        }, time);
    }

    @Override
    public void onClick(View v) {
        if (this.isShowing()) {
            this.dismiss();
        }
        if (mLoadingDialogItf != null) {
            mLoadingDialogItf.onDismissListerner();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        body.setVisibility(View.VISIBLE);
        replace.setVisibility(View.GONE);
        if (mLoadingDialogItf != null) {
            mLoadingDialogItf.onDismissListerner();
        }
    }

    private LoadingDialogItf mLoadingDialogItf;

    /**
     * 设置回掉监听
     *
     * @param loadingDialogItf
     */
    public void setLoadingDialogItf(LoadingDialogItf loadingDialogItf) {
        mLoadingDialogItf = loadingDialogItf;
    }

    public interface LoadingDialogItf {
        void btSureListener();

        void onDismissListerner();
    }
}
