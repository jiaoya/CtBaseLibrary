package com.cactus.library.demo;

import android.os.Bundle;
import android.view.View;

import com.cactus.ctbaselibrary.base.BaseActivity;
import com.cactus.ctbaselibrary.http.exception.ServerApiException;
import com.cactus.ctbaselibrary.http.observer.ServerApiObserve;
import com.cactus.ctbaselibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        findViewById(R.id.tv_test).setOnClickListener(this);
    }

    @Override
    protected void setListeners() {
        ToastUtils.showLong("111111");
    }

    @Override
    public void onClick(View view) {
    }
}
