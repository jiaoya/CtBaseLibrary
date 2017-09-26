package com.cactus.library.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cactus.ctbaselibrary.GlideApp;
import com.cactus.ctbaselibrary.base.BaseActivity;
import com.cactus.ctbaselibrary.utils.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    String url = "http://image.trc.com/3f/db/3b/aebc38e855cabec354d490985c6f787d98723490.jpg_t.jpg";
    private ImageView ivTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        findViewById(R.id.tv_test).setOnClickListener(this);
        ivTest = (ImageView) findViewById(R.id.iv_test);
    }

    @Override
    protected void setListeners() {
        ToastUtils.showLong("111111");
    }

    @Override
    public void onClick(View view) {
        GlideApp.with(this)
                .load(url)
                .into(ivTest);
    }
}
