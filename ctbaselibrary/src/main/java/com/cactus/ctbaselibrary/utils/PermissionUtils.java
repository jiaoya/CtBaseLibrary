package com.cactus.ctbaselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by Jack on 2016/1/15.
 */
public class PermissionUtils {

    /**
     * 检查权限是否已经被允许
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public static boolean checkPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                LogUtils.debug("permission", permission);
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }


    public static void showPermissionsDescDialog(final Activity activity, String message, final boolean needFinishActivity) {
        new AlertDialog.Builder(activity)
                .setTitle("权限申请")
                .setMessage(message)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Uri packageURI = Uri.parse("package:" + "live.xiaotai.live");
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (needFinishActivity) {
                            activity.finish();
                        }
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     * 显示弹窗
     *
     * @param activity
     * @param message
     * @param needFinishActivity
     * @param listener
     */
    public static void showPermissionsDescDialog(final Activity activity, String message, final boolean needFinishActivity, final OnCancelClickListener listener) {
        new AlertDialog.Builder(activity)
                .setTitle("权限申请")
                .setMessage(message)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Uri packageURI = Uri.parse("package:" + "live.xiaotai.live");
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (needFinishActivity) {
                            activity.finish();
                        } else {
                            listener.click();
                        }
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     * 是否要告知用户请求权限的原因
     * 1. 第一次请求权限时，用户拒绝了，下一次：shouldShowRequestPermissionRationale()  返回 true，应该显示一些为什么需要这个权限的说明
     * 2.第二次请求权限时，用户拒绝了，并选择了“不在提醒”的选项时：shouldShowRequestPermissionRationale()  返回 false
     * 3. 设备的策略禁止当前应用获取这个权限的授权：shouldShowRequestPermissionRationale()  返回 false
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public interface OnCancelClickListener {
        void click();
    }
}
