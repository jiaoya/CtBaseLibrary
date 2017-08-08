package com.cactus.ctbaselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;


import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/3/20 16:15
 * <br>Description:   app获取手机设备相关信息
 * <br>Alter By:
 */
public class DeviceUtils {

    public enum OSType {
        MIUI,
        FLYME,
        OTHER
    }

    /**
     * 获取手机系统类型
     *
     * @return
     */
    public static OSType getOSType() {
        OSType osType;
        if (Build.MANUFACTURER.equals("Xiaomi")) {
            osType = OSType.MIUI;
        } else if (isFlyMe()) {
            osType = OSType.FLYME;
        } else {
            osType = OSType.OTHER;
        }
        return osType;
    }

    private static boolean isFlyMe() {
        try {
            Method method = Class.forName("android.os.Build").getMethod("hasSmartBar");
            return (Boolean) method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }


    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String getIMEI() {
        return ((TelephonyManager) Utils.getContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * @return 当前系统版本号
     */
    public static int getAndroidSDKINT() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * @return 获取手机型号
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * @return 获取手机品牌
     */
    public static String getPhoneBrand() {
        return Build.MANUFACTURER;
    }


    /**
     * 获取当前网络IP
     *
     * @return
     */
    public static String getIpAddressString() {
        try {
            for (Enumeration<NetworkInterface> enNetI = NetworkInterface.getNetworkInterfaces(); enNetI.hasMoreElements(); ) {
                NetworkInterface netI = enNetI.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = netI
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String getVersionName() {
        try {
            PackageManager manager = Utils.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(Utils.getContext().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getVersionCode() {
        try {
            PackageManager manager = Utils.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(Utils.getContext().getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
