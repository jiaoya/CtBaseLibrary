package com.cactus.library.demo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <pre>
 *     Copyright    : Copyright (c) 2017.
 *     Authour      : JiaoYa.
 *     Created Time : 2017/7/31.
 *     Desc         :
 *     Alter By     :
 * </pre>
 */
public interface LiveService {

    @GET("room/roomInfoList/getRoomList")
    Observable<LiveOrVideListInfoModel> getListOrVideoList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);


}
