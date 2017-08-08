package com.cactus.ctbaselibrary.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cactus.ctbaselibrary.widget.DialogHelper;

import butterknife.ButterKnife;

/**
 * <pre>
 *     Authour      : JiaoYa
 *     Created Time : 2017/7/26.
 *     Desc         : Activity基类
 *     Alter By     :
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityStackManager mActivityStackManager = null;
    protected DialogHelper mDialogHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将当前Activity加入栈管理
        mActivityStackManager = mActivityStackManager.getAppManager();
        mActivityStackManager.addActivity(this);
        mDialogHelper = new DialogHelper();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initViews();
        setListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        if (mActivityStackManager != null)
            mActivityStackManager.finishActivity(this);
        if (mDialogHelper != null) {
            mDialogHelper.stopLoadingDialog();
        }
    }

    /**
     * view初始化,必须调用setContentView才能生效
     */
    protected abstract void initViews();

    /**
     * 设置监听，必须调用setContentView才能生效
     */
    protected abstract void setListeners();

    protected <T> T bindView(View rootView, int id) {
        View view = rootView.findViewById(id);
        return (T) view;
    }
}
