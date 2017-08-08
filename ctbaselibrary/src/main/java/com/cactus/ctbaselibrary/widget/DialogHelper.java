package com.cactus.ctbaselibrary.widget;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * <pre>
 *     Authour      : JiaoYa
 *     Created Time : 2017/7/26.
 *     Desc         : diloag弹框工具，原生风格，比如正在加载
 *     Alter By     :
 * </pre>
 */
public class DialogHelper {

    private LoadingDialog dialog = null;
    private AlertDialog mDialog = null;
    private ProgressDialog progressDlg = null;

    /**
     * 通用Dialog
     */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件
    public interface OnOkClickListener {
        abstract void onOkClick();
    }

    /**
     * Listener
     */
    public interface OnCancelClickListener {
        abstract void onCancelClick();
    }

    public void showDialog(Context context, String title, String content, final OnOkClickListener listenerYes,
                           final OnCancelClickListener listenerNo) {
        showDialog(context, context.getString(android.R.string.ok), context.getString(android.R.string.cancel), title, content, listenerYes, listenerNo);
    }

    public void showDialog(Context context, String ok, String cancel, String title, String content, final OnOkClickListener listenerYes,
                           final OnCancelClickListener listenerNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        // 设置title
        builder.setTitle(title);
        // 设置确定按钮，固定用法声明一个按钮用这个setPositiveButton
        builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 如果确定被电击
                if (listenerYes != null) {
                    listenerYes.onOkClick();
                }
                mDialog = null;
            }
        });
        // 设置取消按钮，固定用法声明第二个按钮要用setNegativeButton
        if (listenerNo != null) {
            builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // 如果取消被点击
                    if (listenerNo != null) {
                        listenerNo.onCancelClick();
                    }
                    mDialog = null;
                }
            });
        }

        // 控制这个dialog可不可以按返回键，true为可以，false为不可以
        builder.setCancelable(false);
        // 显示dialog
        mDialog = builder.create();
        if (!mDialog.isShowing())
            mDialog.show();
    }

    public void showDialog(Context context, int ok, int cancel, int title, int content, final OnOkClickListener listenerYes,
                           final OnCancelClickListener listenerNo) {
        showDialog(context, context.getString(ok), context.getString(cancel), context.getString(title), context.getString(content), listenerYes, listenerNo);
    }


    /**
     * 启动进度条
     *
     * @param strMessage 进度条显示的信息
     * @param //         当前的activity
     */
    public void showProgressDlg(Context ctx, String strMessage) {

        if (null == progressDlg) {
            if (ctx == null) return;
            progressDlg = new ProgressDialog(ctx);
        }
        //设置进度条样式
        progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //提示的消息
        progressDlg.setMessage(strMessage);
        progressDlg.setIndeterminate(false);
        progressDlg.setCancelable(true);
        progressDlg.show();

    }

    public void showProgressDlg(Context ctx) {
        showProgressDlg(ctx, "");
    }

    /**
     * 结束进度条
     */
    public void stopProgressDlg() {
        if (null != progressDlg && progressDlg.isShowing()) {
            progressDlg.dismiss();
            progressDlg = null;
        }
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void showLoadingDialog(@NonNull Context ctx) {
        if (dialog == null || dialog.getContext() != ctx) {
            if (ctx == null) return;
            dialog = new LoadingDialog(ctx);
        }
        dialog.show();
    }

    public void showLoadingDialog(@NonNull Context ctx, String strMessage) {
        if (dialog == null || dialog.getContext() != ctx) {
            if (ctx == null) return;
            dialog = new LoadingDialog(ctx, strMessage);
        }
        dialog.show();
    }

    public void setLoadingDialogContent(String content) {
        if (dialog == null) {
            Log.e("request_error_code", content);
            return;
        }
        dialog.alterMsgIsShowing(content);
    }

    public void stopLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void hiddenDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (mDialog != null && dialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
