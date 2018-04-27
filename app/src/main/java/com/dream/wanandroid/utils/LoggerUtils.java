package com.dream.wanandroid.utils;



import com.orhanobut.logger.Logger;

/**
 * 对日志库进行相应的封装
 */
public class LoggerUtils {

//    private static final boolean isLogDebug = BuildConfig.IS_LOG_DEBUG;
    private static final boolean isLogDebug = true;

//    static {
//        // default PRETTYLOGGER or use just init()
//        Logger.init(AppVersionUtils.getAppPackageName(MyApplication.getInstance()))
//                .methodCount(2)                 // default 2
//                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
//                .methodOffset(2);               // default 0
//                //.logTool(new AndroidLogTool()); // custom log tool, optional
//    }

    public static void d(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).d(message);
        }
    }

    public static void e(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).e(message);
        }
    }

    public static void w(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).w(message);
        }

    }

    public static void v(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).v(message);
        }

    }

    public static void i(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).i(message);
        }
    }

    public static void wtf(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).wtf(message);
        }
    }

    public static void json(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).json(message);
        }
    }

    public static void xml(String TAG, String message) {
        if (isLogDebug) {
            Logger.t(TAG).xml(message);
        }
    }

    public LoggerUtils(String TAG) {
        this.TAG = TAG;
    }

    // =================================

    private String TAG;

    public static LoggerUtils newInstance(String TAG) {
        return new LoggerUtils(TAG);
    }

    public void d(String message) {
        if (isLogDebug)
            d(TAG, message);
    }

    public void e(String message) {
        if (isLogDebug)
            e(TAG, message);
    }

    public void w(String message) {
        if (isLogDebug)
            w(TAG, message);
    }

    public void v(String message) {
        if (isLogDebug)
            v(TAG, message);
    }

    public void i(String message) {
        if (isLogDebug)
            i(TAG, message);
    }

    public void wtf( String message) {
        if (isLogDebug)
            wtf(TAG, message);
    }

    public void json(String message) {
        if (isLogDebug)
            json(TAG, message);
    }

    public void xml(String message) {
        if (isLogDebug)
            xml(TAG, message);
    }

}
