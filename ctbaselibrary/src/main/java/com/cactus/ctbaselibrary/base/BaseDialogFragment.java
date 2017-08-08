package com.cactus.ctbaselibrary.base;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * <pre>
 *     Authour      : JiaoYa
 *     Created Time : 2017/7/26.
 *     Desc         :
 *     Alter By     :
 * </pre>
 */
public abstract class BaseDialogFragment extends DialogFragment {
    private static final float DEFAULT_DIM = 0.2f;
    protected String TAG = getClass().getSimpleName();

    /**
     * 点击返回键是否消失
     */
    public static final String DIALOG_BACK = "dialog_back";
    /**
     * 是否取消
     */
    public static final String DIALOG_CANCELABLE = "dialog_cancelable";
    /**
     * 点击外部是否消失
     */
    public static final String DIALOG_CANCELABLE_TOUCH_OUT_SIDE = "dialog_cancelable_touch_out_side";
    protected Bundle bundle;
    protected Activity activity;
    protected boolean isCancelableTouchOutSide = true, isCancelable = true, isBack = true;
    private View.OnClickListener mItemsOnClick;
    protected BaseDialogFragment fragment;
    protected View rootView;
    /**
     * dialog上的控件点监听
     */
    public DialogFragmentViewClick mViewLiserner;
    /**
     * dialog返回键点击监听
     */
    public DialogBackLiserner backLiserner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    /**
     * 返回当前DialogFragment的实例，使用Bundle传递参数
     *
     * @param clazz 当前DialogFragment.class
     * @param args  携带参数的Bundle对象
     * @param <T>   返回类型参数控制，必须是BaseDialogFragment的子类
     * @return 当前Fragment的实例
     */
    public static <T extends BaseDialogFragment> T newInstance(Class clazz, Bundle args) {
        T fragment = null;
        try {
            fragment = (T) clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);

        return fragment;
    }

    public static <T extends BaseDialogFragment> T newInstance(Class clazz) {
        T fragment = null;
        try {
            fragment = (T) clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        setBundle(bundle);
    }

    public void setBundle(Bundle bundle) {
        if (bundle != null) {
            isBack = bundle.getBoolean(DIALOG_BACK, true);
            isCancelable = bundle.getBoolean(DIALOG_CANCELABLE, true);
            isCancelableTouchOutSide = bundle.getBoolean(DIALOG_CANCELABLE_TOUCH_OUT_SIDE, true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        rootView = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, rootView);
        initView();
        setDialogDissPlayStytle();
        onBackPressed();
        return rootView;
    }

    /*
    * dialog开始是对位置进行设置
    * */
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        setParams(params);
        window.setAttributes(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.bind(this, rootView).unbind();
    }

    /*
        * 给dialogFragment设置位置参数
        * */
    protected abstract void setParams(WindowManager.LayoutParams params);

    /**
     * @return LayoutId
     */
    protected abstract int getLayoutResId();

    /**
     * 数据和视图的处理
     */
    protected abstract void initView();

    /**
     * 当对话框消失时的监听事件
     */
    protected abstract void onDialogDismiss();

    /*
    * 设置弹窗的显示风格
    * */
    protected void setDialogDissPlayStytle() {
    }

    /**
     * 监听返回键
     */
    private void onBackPressed() {
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (isBack) {
                        onDialogDismiss();
                    }
                    if (backLiserner != null) {
                        backLiserner.DialogFragmentBackClickListerner(isBack);
                    }
                    return !isBack;
                }
                return false;
            }
        });
    }

    public void setDialogFragmentViewClickListerner(DialogFragmentViewClick liserner) {
        this.mViewLiserner = liserner;
    }

    public interface DialogFragmentViewClick {
        void DialogFragmentViewClickListerner(View view);
    }

    public void setOnBackClickLiserner(DialogBackLiserner liserner) {
        backLiserner = liserner;
    }

    public interface DialogBackLiserner {
        /**
         * 点击返回键 dialog是否消失
         *
         * @param isDialogBack
         */
        void DialogFragmentBackClickListerner(boolean isDialogBack);
    }

}
