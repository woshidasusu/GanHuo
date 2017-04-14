package com.dasu.ganhuo.utils;

import android.util.Log;

import com.dasu.ganhuo.BuildConfig;

/**
 * Created by dasu on 2017/4/10.
 */
public class LogUtils {
    private static final String TAG = LogUtils.class.getSimpleName();

    private static final boolean debug = BuildConfig.DEBUG;

    private static final int V = 0x01;
    private static final int D = 0x02;
    private static final int I = 0x04;
    private static final int W = 0x08;
    private static final int E = 0x10;
    private static final int A = 0x20;

    public static void v(String tag, String msg) {
        log(V, tag, msg);
    }

    public static void d(String tag, String msg) {
        log(D, tag, msg);
    }

    public static void i(String tag, String msg) {
        log(I, tag, msg);
    }

    public static void w(String tag, String msg) {
        log(W, tag, msg);
    }

    public static void e(String tag, String msg) {
        log(E, tag, msg);
    }

    public static void a(String tag, String msg) {
        log(A, tag, msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        Log.e(tag, msg, e);
    }

    private static void log(int type, String tag, String msg) {
        if (!debug) {
            return;
        }
        switch (type) {
            case V:
                Log.v(tag, msg);
                break;
            case D:
                Log.d(tag, msg);
                break;
            case I:
                Log.i(tag, msg);
                break;
            case W:
                Log.w(tag, msg);
                break;
            case E:
                Log.e(tag, msg);
                break;
            case A:
                Log.wtf(tag, msg);
                break;
        }
    }
}
