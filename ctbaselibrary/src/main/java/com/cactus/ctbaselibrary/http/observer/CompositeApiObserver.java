package com.cactus.ctbaselibrary.http.observer;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/1.
 * <br>Description:   appSever接口的观察者统一管理
 * <br>Alter By:
 */
public class CompositeApiObserver {

    CompositeDisposable mDisposable;

    public CompositeApiObserver() {
        mDisposable = new CompositeDisposable();
    }

    /**
     * 添加到管理里
     *
     * @param disposable
     */
    public void add(@NonNull Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        if (disposable != null && !disposable.isDisposed()) {
            mDisposable.add(disposable);
        }
    }

    public void remove(@NonNull Disposable disposable) {
        if (disposable != null && !disposable.isDisposed() && mDisposable != null) {
            mDisposable.remove(disposable);
        }
    }

    /**
     * 移除其他的观察者，然后在调用{@link #clear()}，这样在界面消失后仍然能接收到回调
     *
     * @param disposable 此观察者不移除
     */
    public void clearOther(@NonNull Disposable disposable) {
        if (disposable != null && !disposable.isDisposed() && mDisposable != null)
            mDisposable.delete(disposable);
    }

    /**
     * 移除其他的观察者，然后在调用{@link #clear()}，这样在界面消失后仍然能接收到回调
     *
     * @param disposables 此观察者数组不移除
     */
    public void clearOther(@NonNull Disposable... disposables) {
        if (disposables != null && mDisposable != null)
            for (int i = 0; i < disposables.length; i++) {
                if (disposables[i] != null && !disposables[i].isDisposed())
                    mDisposable.delete(disposables[i]);
            }
    }

    /**
     * 清楚所有观察者，将不再接受被观察者的消息
     */
    public void clear() {
        if (mDisposable != null)
            mDisposable.clear();
        mDisposable = null;
    }
}
