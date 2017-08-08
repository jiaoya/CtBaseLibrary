package com.cactus.ctbaselibrary.http.exception;

import android.net.ParseException;

import com.cactus.ctbaselibrary.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import retrofit2.HttpException;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/7/3.
 * <br>Description:   服务端接口的所有异常进行统一处理
 * <br>Alter By:
 */
public class ServerApiExceptionEngine {

    private static final String server_exception_name = "服务异常：";
    private static ServerApiExceptionCode error_code = new ServerApiExceptionCode();

    public static ServerApiException handleException(Throwable e) {
        ServerApiException ex;
        try {
            if (e instanceof HttpException) {             //HTTP错误
                HttpException httpException = (HttpException) e;
                int code = httpException.code();
                ex = new ServerApiException(e, code);
                if (code == ServerApiExceptionCode.ERROR_400) {//这里服务端如果报错，错误信息统一写在400里，这里和结果是分开的，
                    // 当然也可以不,在200里进行统一处理，这时就要也要在onNext里处理这个类，可以继承ServerApiObserve类在写个观察者
                    String errorBodyString = httpException.response().errorBody().string();
                    ServerApiExceptionModel errorModel = new Gson().fromJson(errorBodyString, ServerApiExceptionModel.class);
                    if (errorModel != null && errorModel.getError() != null && !StringUtils.checkNull(errorModel.getError().getDescription())) {
                        ex.setDisplayMessage(errorModel.getError().getDescription());
                    }

                } else if (code == ServerApiExceptionCode.ERROR_401) {//一般登录问题处理/禁止访问
                    //ToastUtil.show(R.string.need_login);
                    //Intent intent = new Intent(LiveApplication.getContext(), LoginActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //LiveApplication.getContext().startActivity(intent);

                } else if (code == ServerApiExceptionCode.ERROR_403) {
                    ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_403);
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_403);

                } else if (code == ServerApiExceptionCode.ERROR_404) {
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_404);

                } else if (code == ServerApiExceptionCode.ERROR_408) {
                    ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_408);
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_408);

                } else if (code == ServerApiExceptionCode.ERROR_504) {
                    ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_504);
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_504);

                } else if (code == ServerApiExceptionCode.ERROR_500) {
                    ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_500);
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_500);

                } else if (code == ServerApiExceptionCode.ERROR_502) {
                    ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_502);
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_502);

                } else if (code == ServerApiExceptionCode.ERROR_503) {
                    ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_503);
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_503);

                } else {
                    ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_600);
                    ex.setDisplayMessage(server_exception_name + ServerApiExceptionCode.ERROR_600);  //均视为网络错误

                }
                return ex;

            } else if (e instanceof SocketTimeoutException) {    //服务器无响应请求超时
                ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_21002);
                ex.setDisplayMessage(error_code.getAPP_SHOW_ERROR_MSG().get(ServerApiExceptionCode.ERROR_21002));
                return ex;

            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {
                ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_20006);
                ex.setDisplayMessage("json解析异常");            //均视为解析错误
                return ex;

            } else if (e instanceof BadPaddingException
                    || e instanceof IllegalBlockSizeException
                    || e instanceof UnsupportedEncodingException) {
                ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_20002);
                ex.setDisplayMessage("加密解密异常");            //加密解密异常
                return ex;

            } else if (e instanceof NoSuchAlgorithmException
                    || e instanceof NoSuchPaddingException
                    || e instanceof InvalidAlgorithmParameterException
                    || e instanceof InvalidKeyException) {
                ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_20003);
                ex.setDisplayMessage("加密初始化错误");            //加密解密初始化异常
                return ex;

            } else if (e instanceof ConnectException
                    || e instanceof UnknownHostException) {
                ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_21001);
                ex.setDisplayMessage(error_code.getAPP_SHOW_ERROR_MSG().get(ServerApiExceptionCode.ERROR_21001));  //均视为网络错误
                return ex;

            } else if (e instanceof NullPointerException) {
                ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_20001);
                ex.setDisplayMessage("ERROR:20001");
                return ex;

            } else if (e instanceof ClassCastException) {
                ex = new ServerApiException(e, ServerApiExceptionCode.ERROR_20004);
                ex.setDisplayMessage("类型转换异常");
                return ex;
            } else {
                ex = new ServerApiException(e, ServerApiExceptionCode.UNKNOWN);
                ex.setDisplayMessage("未知错误");          //未知错误
                return ex;
            }
        } catch (Exception e1) {
            ex = new ServerApiException(e, ServerApiExceptionCode.UNKNOWN);
            ex.setDisplayMessage(e1.getMessage());
            return ex;
        }
    }


}
