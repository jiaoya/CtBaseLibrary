package com.cactus.ctbaselibrary.base;

import android.support.annotation.NonNull;

import com.cactus.ctbaselibrary.http.observer.CompositeApiObserver;

/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/7/28.
 *     Desc         : Presenter基类，实现presenter要集成此类还是实现接口{@link BasePresenter}
 *                    也可以不用集成此类，直接实现接口{@link BasePresenter}
 *     Alter By     :
 * </pre>
 */
public class ComPresenter<T extends BaseView> {

    public CompositeApiObserver compositeApiObserver = null;
    public T mView;

    public ComPresenter(@NonNull T view) {
        compositeApiObserver = new CompositeApiObserver();
        mView = view;
        mView.setPresenter(this);

    }

    /**
     * 清楚观察者
     */
    public void ClearObserver() {
        if (compositeApiObserver != null)
            compositeApiObserver.clear();
    }
}
