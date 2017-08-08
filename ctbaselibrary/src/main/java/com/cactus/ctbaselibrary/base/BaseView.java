package com.cactus.ctbaselibrary.base;

/**
 * <pre>
 *     Authour      : JiaoYa
 *     Created Time : 2017/7/26.
 *     Desc         : activity或fragment实现的接口，presenter通过此，回调到界面上去实现相应的操作
 *     Alter By     :
 * </pre>
 */
public interface BaseView<T> {

    void setPresenter(T presenter);
}
