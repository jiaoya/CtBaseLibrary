package com.cactus.library.demo;

import com.cactus.ctbaselibrary.http.RetrofitClient;

import io.reactivex.Observable;

/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/7/31.
 *     Desc         :
 *     Alter By     :
 * </pre>
 */
public class AppServerApi {

    public static final int EDIT_NICK_NAME = 1;
    public static final int EDIT_BIRTHDAY = 2;
    public static final int EDIT_SEX = 3;
    public static final int EDIT_SIGNATURE = 4;
    public static final int EDIT_USER_TAGS = 5;
    public static final int EDIT_USER_AVATAR = 6;

    /**
     * 获取直播或视频
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static Observable<LiveOrVideListInfoModel> getLiveOrVideoList(int pageNum, int pageSize) {
        return RetrofitClient.getRetrofit().create(LiveService.class).getListOrVideoList(pageNum, pageSize);
    }


}
