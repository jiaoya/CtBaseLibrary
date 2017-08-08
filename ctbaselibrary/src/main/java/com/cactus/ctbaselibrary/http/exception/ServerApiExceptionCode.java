package com.cactus.ctbaselibrary.http.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/3.
 * <br>Description:   统一错误码
 * <br>Alter By:
 */
public class ServerApiExceptionCode {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 10000;

    //APP Server Error Code

    //提示：错误码（Error Code）(10001~11000)，根据错误码进行相应的操作
    public static final int ERROR_10001 = 10001;//服务器请求参数错误
    public static final int ERROR_10002 = 10002;//找不到令牌对应的配置文件
    public static final int ERROR_10003 = 10003;//找不到签名密钥
    public static final int ERROR_10004 = 10004;//协议数据异常，签名错误
    public static final int ERROR_10005 = 10005;//找不到加密密钥
    public static final int ERROR_10006 = 10006;//data字符串无法解析
    public static final int ERROR_10007 = 10007;//data 字符串无法转为Json字符串
    public static final int ERROR_10008 = 10008;//发送的协议数据格式错误
    public static final int ERROR_10009 = 10009;//版本信息错误，找不到配置文件
    public static final int ERROR_10010 = 10010;//找不到对应功能
    public static final int ERROR_10011 = 10011;//对应功能不存在
    public static final int ERROR_10012 = 10012;//token令牌错误
    public static final int ERROR_10013 = 10013;//发送的协议错误
    public static final int ERROR_10014 = 10014;//未登录,请进行登录
    public static final int ERROR_10015 = 10015;//直播已暂停
    public static final int ERROR_10016 = 10016;//直播已禁止
    public static final int ERROR_10017 = 10017;//已经进入过，无需竞猜


    //提示：错误说明 (11001~12000)
    public static final int ERROR_11001 = 11001;//您的账号已经在其他设备上登录
    public static final int ERROR_11002 = 11002;//直播已结束
    public static final int ERROR_11003 = 11003;//验证码信息错误，登录失败
    public static final int ERROR_11004 = 11004;//无法获取验证码，登录失败
    public static final int ERROR_11005 = 11005;//您已被禁用，请与管理员联系
    public static final int ERROR_11006 = 11006;//系统维护中
    public static final int ERROR_11007 = 11007;//蜜播App版本过低，请更新到最新版本！
    public static final int ERROR_11008 = 11008;//签约主播必须进行认证才能直播！
    public static final int ERROR_11009 = 11009;//签约主播认证正在审核中
    public static final int ERROR_11010 = 11010;//登录信息验证错误
    public static final int ERROR_11011 = 11011;//接口api协议异常错误 （所有接口返回错误）
    public static final int ERROR_11012 = 11012;//检测版本验证错误


    //App Error Code

    //特殊码提示 （Error Code）300~599
    public static final int ERROR_302 = 302;//(Found/找到)
    public static final int ERROR_400 = 400;//(Unauthorized/未授权)
    public static final int ERROR_401 = 401;//(Unauthorized/未授权)
    public static final int ERROR_403 = 403;//(Forbidden/禁止)
    public static final int ERROR_404 = 404;//(Not Found/未找到)
    public static final int ERROR_405 = 405;//(Method Not Allowed/方法未允许)
    public static final int ERROR_408 = 408;//(Request Timeout/请求超时)
    public static final int ERROR_500 = 500;//(Internal Server Error/内部服务器错误)
    public static final int ERROR_502 = 502;//(Bad Gateway/错误的网关)
    public static final int ERROR_503 = 503;//(Service Unavailable/服务无法获得)
    public static final int ERROR_504 = 504;//(Gateway Timeout/网关超时)
    public static final int ERROR_600 = 600;//默认特殊错误，非上面的码，就统一600

    //提示：错误码（Error Code）20001~21000
    public static final int ERROR_20001 = 20001;//空指针异常
    public static final int ERROR_20002 = 20002;//加密或解密异常
    public static final int ERROR_20003 = 20003;//加密初始化异常
    public static final int ERROR_20004 = 20004;//类型转换异常
    public static final int ERROR_20005 = 20005;//内存溢出
    public static final int ERROR_20006 = 20006;//json解析异常

    //提示：错误说明 21000~22000
    public static final int ERROR_21001 = 21001;//网络链接失败
    public static final int ERROR_21002 = 21002;//网络连接超时或请求超时


    /**
     * 存放app显示的错误码和对应的内容，便于查找对应
     */
    private Map<Integer, String> APP_SHOW_ERROR_MSG;

    public ServerApiExceptionCode() {
        APP_SHOW_ERROR_MSG = new HashMap<>();
        APP_SHOW_ERROR_MSG.put(ERROR_21001, "网络连接失败");
        APP_SHOW_ERROR_MSG.put(ERROR_21002, "网络连接超时或请求超时");
    }

    public Map<Integer, String> getAPP_SHOW_ERROR_MSG() {
        return APP_SHOW_ERROR_MSG;
    }


    /**
     * 判断错误码的范围
     *
     * @param code
     * @return true：显示错误码，false：显示错误码对应的内容
     */
    public static boolean errorCodeRange(int code) {
        if (10001 <= code && code <= 11000 || 300 <= code && code <= 599 || 20001 <= code && code <= 21000) {
            return true;
        } else {
            return false;
        }
    }

}
