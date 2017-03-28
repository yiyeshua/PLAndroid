/**
 * created by lhw, 16/04/09
 */


package com.yiyeshu.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

public class AppUtils {

    private static Context mContext;
    private static Thread mUiThread;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void init(Context context) { //在Application中初始化
        mContext = context;
        mUiThread = Thread.currentThread();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AssetManager getAssets() {
        return mContext.getAssets();
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static boolean isUIThread() {
        return Thread.currentThread() == mUiThread;
    }

    public static void runOnUI(Runnable r) {
        sHandler.post(r);
    }

    public static void runOnUIDelayed(Runnable r, long delayMills) {
        sHandler.postDelayed(r, delayMills);
    }

    public static void removeRunnable(Runnable r) {
        if (r == null) {
            sHandler.removeCallbacksAndMessages(null);
        } else {
            sHandler.removeCallbacks(r);
        }
    }

    public static String getPackageName() {
        if (mContext != null) {
            return mContext.getPackageName();
        } else {
            return "com.yiyeshu.plandroid";
        }
    }

    public static int getVersionCode() {
        if (mContext != null) {
            try {
                return mContext.getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    /**
     * 获取当前创建的进程名称，区分主进程和消息推送进程
     */
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> listInfo = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : listInfo) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 记录程序启动时间，在application创建的时候统一设置
     */
    private static long sAppStartTime = 0;
    public static void setAppStartTime() {
        sAppStartTime = System.currentTimeMillis();
    }

    /**
     * 获取程序当前运行时间点
     */
    public static long getAppRunTime() {
        return System.currentTimeMillis() - sAppStartTime;
    }
}
