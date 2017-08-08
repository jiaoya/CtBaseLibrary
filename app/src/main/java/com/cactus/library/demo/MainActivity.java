package com.cactus.library.demo;

import android.os.Bundle;

import com.cactus.ctbaselibrary.base.BaseActivity;
import com.cactus.ctbaselibrary.utils.ToastUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setListeners() {
        ToastUtils.showLong("111111");
    }
}
