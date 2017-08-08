package com.cactus.ctbaselibrary.http.exception;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/3.
 * <br>Description:   服务端接口错误统一的model
 * <br>Alter By:
 */
public class ServerApiExceptionModel {
    /**
     * error : {"code":13,"description":"密码错误，请重新输入，还可以重试4次"}
     */

    private ErrorBean error;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class ErrorBean {
        /**
         * code : 13
         * description : 密码错误，请重新输入，还可以重试4次
         */

        private int code;
        private String description;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
