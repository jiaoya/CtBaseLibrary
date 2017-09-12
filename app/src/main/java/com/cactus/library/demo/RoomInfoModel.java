package com.cactus.library.demo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/5/24.
 * <br>Description:
 * <br>Alter By:
 */
public class RoomInfoModel implements Parcelable {


    /**
     * anchorGrade : 主播等级
     * avatar : 头像
     * birthday : 生日
     * coCompanyId : 公司id
     * coverPhoto : 直播封面照
     * createDate : 创建时间
     * endDate : 结束日期
     * hlsUrl : hls拉流地址
     * imRoomid : 聊天室
     * isPortrait :  播放方式是否为竖屏 0为横屏 1为竖屏
     * isTop : false
     * mp4DelDate : mp4回放删除时间
     * mp4Status : 回放记录状态（0未生成，1已生成，2已删除)
     * mp4Url : 回放地址
     * needPwd : 是否需要房间密码，0不需要, 1需要
     * nickName : 昵称
     * onLineNum : 在线人数，前端展示的人数
     * onLineNumIm : 在线人数，网易im统计，不序列化
     * onLineNumReal : 真实人数
     * password : 房间密码
     * playTime : 开播时间，endDate减去startDate，单位分钟
     * publishURL : 推流地址
     * roomInfoId : 直播间主键
     * roomInfoIdStr : string
     * roomStatus : 直播间状态：0未开始, 1直播中, 2已结束, 3被禁播
     * roomTitle : 房间标题
     * roomType :  房间类型（0:普通，1：公司内部房间，2：导购房间）
     * rtmpURL : rtmp拉流地址
     * sex : 性别：0男; 1女
     * signature : 个性签名
     * startDate : 开播日期
     * updateDate : 修改时间
     * userId : 用户Id(主播Id)
     * userNo : 用户号
     * version : 版本号
     * viewNum : 观看次数
     */

    private String anchorGrade;
    private String avatar;
    private String birthday;
    private String coCompanyId;
    private String coverPhoto;
    private String createDate;
    private String endDate;
    private String hlsUrl;
    private String imRoomid;
    private String isPortrait;
    private boolean isTop;
    private String mp4DelDate;
    private String mp4Status;
    private String mp4Url;
    private String needPwd;
    private String nickName;
    private String onLineNum;
    private String onLineNumIm;
    private String onLineNumReal;
    private String password;
    private String playTime;
    private String publishURL;
    private String roomInfoId;
    private String roomInfoIdStr;
    private String roomStatus;
    private String roomTitle;
    private String roomType;
    private String rtmpURL;
    private String sex;
    private String signature;
    private String startDate;
    private String updateDate;
    private String userId;
    private String userNo;
    private String version;
    private String viewNum;

    public String getAnchorGrade() {
        return anchorGrade;
    }

    public void setAnchorGrade(String anchorGrade) {
        this.anchorGrade = anchorGrade;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCoCompanyId() {
        return coCompanyId;
    }

    public void setCoCompanyId(String coCompanyId) {
        this.coCompanyId = coCompanyId;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    public String getImRoomid() {
        return imRoomid;
    }

    public void setImRoomid(String imRoomid) {
        this.imRoomid = imRoomid;
    }

    public String getIsPortrait() {
        return isPortrait;
    }

    public void setIsPortrait(String isPortrait) {
        this.isPortrait = isPortrait;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public String getMp4DelDate() {
        return mp4DelDate;
    }

    public void setMp4DelDate(String mp4DelDate) {
        this.mp4DelDate = mp4DelDate;
    }

    public String getMp4Status() {
        return mp4Status;
    }

    public void setMp4Status(String mp4Status) {
        this.mp4Status = mp4Status;
    }

    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    public String getNeedPwd() {
        return needPwd;
    }

    public void setNeedPwd(String needPwd) {
        this.needPwd = needPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOnLineNum() {
        return onLineNum;
    }

    public void setOnLineNum(String onLineNum) {
        this.onLineNum = onLineNum;
    }

    public String getOnLineNumIm() {
        return onLineNumIm;
    }

    public void setOnLineNumIm(String onLineNumIm) {
        this.onLineNumIm = onLineNumIm;
    }

    public String getOnLineNumReal() {
        return onLineNumReal;
    }

    public void setOnLineNumReal(String onLineNumReal) {
        this.onLineNumReal = onLineNumReal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getPublishURL() {
        return publishURL;
    }

    public void setPublishURL(String publishURL) {
        this.publishURL = publishURL;
    }

    public String getRoomInfoId() {
        return roomInfoId;
    }

    public void setRoomInfoId(String roomInfoId) {
        this.roomInfoId = roomInfoId;
    }

    public String getRoomInfoIdStr() {
        return roomInfoIdStr;
    }

    public void setRoomInfoIdStr(String roomInfoIdStr) {
        this.roomInfoIdStr = roomInfoIdStr;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRtmpURL() {
        return rtmpURL;
    }

    public void setRtmpURL(String rtmpURL) {
        this.rtmpURL = rtmpURL;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.anchorGrade);
        dest.writeString(this.avatar);
        dest.writeString(this.birthday);
        dest.writeString(this.coCompanyId);
        dest.writeString(this.coverPhoto);
        dest.writeString(this.createDate);
        dest.writeString(this.endDate);
        dest.writeString(this.hlsUrl);
        dest.writeString(this.imRoomid);
        dest.writeString(this.isPortrait);
        dest.writeByte(this.isTop ? (byte) 1 : (byte) 0);
        dest.writeString(this.mp4DelDate);
        dest.writeString(this.mp4Status);
        dest.writeString(this.mp4Url);
        dest.writeString(this.needPwd);
        dest.writeString(this.nickName);
        dest.writeString(this.onLineNum);
        dest.writeString(this.onLineNumIm);
        dest.writeString(this.onLineNumReal);
        dest.writeString(this.password);
        dest.writeString(this.playTime);
        dest.writeString(this.publishURL);
        dest.writeString(this.roomInfoId);
        dest.writeString(this.roomInfoIdStr);
        dest.writeString(this.roomStatus);
        dest.writeString(this.roomTitle);
        dest.writeString(this.roomType);
        dest.writeString(this.rtmpURL);
        dest.writeString(this.sex);
        dest.writeString(this.signature);
        dest.writeString(this.startDate);
        dest.writeString(this.updateDate);
        dest.writeString(this.userId);
        dest.writeString(this.userNo);
        dest.writeString(this.version);
        dest.writeString(this.viewNum);
    }

    public RoomInfoModel() {
    }

    protected RoomInfoModel(Parcel in) {
        this.anchorGrade = in.readString();
        this.avatar = in.readString();
        this.birthday = in.readString();
        this.coCompanyId = in.readString();
        this.coverPhoto = in.readString();
        this.createDate = in.readString();
        this.endDate = in.readString();
        this.hlsUrl = in.readString();
        this.imRoomid = in.readString();
        this.isPortrait = in.readString();
        this.isTop = in.readByte() != 0;
        this.mp4DelDate = in.readString();
        this.mp4Status = in.readString();
        this.mp4Url = in.readString();
        this.needPwd = in.readString();
        this.nickName = in.readString();
        this.onLineNum = in.readString();
        this.onLineNumIm = in.readString();
        this.onLineNumReal = in.readString();
        this.password = in.readString();
        this.playTime = in.readString();
        this.publishURL = in.readString();
        this.roomInfoId = in.readString();
        this.roomInfoIdStr = in.readString();
        this.roomStatus = in.readString();
        this.roomTitle = in.readString();
        this.roomType = in.readString();
        this.rtmpURL = in.readString();
        this.sex = in.readString();
        this.signature = in.readString();
        this.startDate = in.readString();
        this.updateDate = in.readString();
        this.userId = in.readString();
        this.userNo = in.readString();
        this.version = in.readString();
        this.viewNum = in.readString();
    }

    public static final Creator<RoomInfoModel> CREATOR = new Creator<RoomInfoModel>() {
        @Override
        public RoomInfoModel createFromParcel(Parcel source) {
            return new RoomInfoModel(source);
        }

        @Override
        public RoomInfoModel[] newArray(int size) {
            return new RoomInfoModel[size];
        }
    };
}
