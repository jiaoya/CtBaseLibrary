package com.cactus.ctbaselibrary;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

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
}
