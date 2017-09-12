package com.cactus.library.demo;

import java.util.List;

/**
 * <br>Copyright:    Copyright (c) 2017
 * <br>Author:       焦亚
 * <br>Time:         2017/4/28 11:28
 * <br>ByAlter:
 * <br>Description:  主页三个页面信息model
 */
public class LiveOrVideListInfoModel {


    private List<ReplayRoomListBean> replayRoomList;
    private List<PlayRoomListBean> playRoomList;

    public List<ReplayRoomListBean> getReplayRoomList() {
        return replayRoomList;
    }

    public void setReplayRoomList(List<ReplayRoomListBean> replayRoomList) {
        this.replayRoomList = replayRoomList;
    }

    public List<PlayRoomListBean> getPlayRoomList() {
        return playRoomList;
    }

    public void setPlayRoomList(List<PlayRoomListBean> playRoomList) {
        this.playRoomList = playRoomList;
    }

    public static class ReplayRoomListBean extends RoomListBean {

    }

    public static class PlayRoomListBean extends RoomListBean {

    }
}
