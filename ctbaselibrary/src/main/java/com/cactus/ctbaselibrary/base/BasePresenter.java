package com.cactus.ctbaselibrary.base;

/**
 * <pre>
 *     Authour      : JiaoYa
 *     Created Time : 2017/7/26.
 *     Desc         : activity或fragment通过此接口，调用present去请求数据，presenter要实现这个接口
 *     Alter By     :
 * </pre>
 */
public interface BasePresenter {

    void subscribe();

    void unsubscribe();

}
