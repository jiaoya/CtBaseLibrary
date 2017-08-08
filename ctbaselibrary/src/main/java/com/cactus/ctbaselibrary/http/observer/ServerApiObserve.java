package com.cactus.ctbaselibrary.http.observer;


import android.support.annotation.NonNull;

import com.cactus.ctbaselibrary.http.exception.ServerApiException;
import com.cactus.ctbaselibrary.http.exception.ServerApiExceptionEngine;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/1.
 * <br>Description:   服务端接口观察者
 * <br>Alter By:
 */
public abstract class ServerApiObserve<T> implements Observer<T> {

    private Disposable tDisposable = null;
    private CompositeApiObserver mComposite = null;

    public ServerApiObserve(CompositeApiObserver composite) {
        mComposite = composite;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (d != null && mComposite != null) {//添加到观察者统一管理
            tDisposable = d;
            mComposite.add(d);
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        onNext1(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e != null) {//进行服务端报错的统一处理
            ServerApiException serverApiException = ServerApiExceptionEngine.handleException(e);
            onError1(serverApiException);
        }
    }

    @Override
    public void onComplete() {
        onComplete1();
    }

    public abstract void onError1(@NonNull ServerApiException e);

    public abstract void onNext1(@NonNull T t);

    public abstract void onComplete1();

    public Disposable gettDisposable() {
        return tDisposable;
    }
}
