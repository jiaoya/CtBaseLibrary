package com.cactus.ctbaselibrary.http.exception;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/3.
 * <br>Description:   对外暴露的服务端异常信息
 * <br>Alter By:
 */
public class ServerApiException extends Exception {

    /**
     * 服务器返回的异常码
     */
    private final int code;
    /**
     * 异常信息的内容
     */
    private String displayMessage;

    public ServerApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String msg) {
        this.displayMessage = msg;
    }
}
