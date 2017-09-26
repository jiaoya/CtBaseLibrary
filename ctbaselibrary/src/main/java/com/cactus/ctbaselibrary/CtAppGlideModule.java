package com.cactus.ctbaselibrary;

import android.content.Context;

import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.cactus.ctbaselibrary.widget.UnsafeOkHttpClient;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/17.
 * <br>Description:   如果使用glide v4以后的GilideApp就必须这样写，具体可以参考github
 * <br>Alter By:
 */
@GlideModule
public class CtAppGlideModule extends AppGlideModule {

    /**
     * 清单解析的开启
     * <p>
     * 这里不开启，避免添加相同的modules两次
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(Context context, Registry registry) {
        OkHttpClient client = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }
}
