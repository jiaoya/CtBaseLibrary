package com.cactus.ctbaselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cactus.ctbaselibrary.widget.DialogHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     Authour      : JiaoYa
 *     Created Time : 2017/7/26.
 *     Desc         : Fragment基类
 *     Alter By     :
 * </pre>
 */
public abstract class BaseFragment extends Fragment {

    protected View mView = null;
    protected DialogHelper mDialogHelper;
    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        unbinder = ButterKnife.bind(this, mView);
        mDialogHelper = new DialogHelper();
        initViews(view);
        setListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDialogHelper != null) {
            mDialogHelper.stopLoadingDialog();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * view初始化
     *
     * @param rootView
     */
    protected abstract void initViews(View rootView);

    /**
     * 设置监听
     */
    protected abstract void setListeners();

}
