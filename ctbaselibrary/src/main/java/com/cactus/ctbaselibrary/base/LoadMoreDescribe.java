package com.cactus.ctbaselibrary.base;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/3/24 13:56
 * <br>Description:   加载更多描述
 * <br>Alter By:
 */

public class LoadMoreDescribe {
    /**
     * 0--加载更多
     * 1--正在加载
     * 2--已经到最后
     * 3--错误
     * 4--还没有数据
     */
    public static final int loadMore = 0;//加载更多
    public static final int loading = 1;//正在加载
    public static final int loadEnd = 2;//加载到最后
    public static final int loadingError = 3;//加载错误
    public static final int zeroData = 4;//还没有数据
    private int type = 1;

    public LoadMoreDescribe(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
