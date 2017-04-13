package com.dasu.ganhuo;

import android.app.Application;
import android.content.IntentFilter;

import com.dasu.ganhuo.mode.network.NetBroadcastReceiver;

/**
 * Created by dasu on 2017/4/13.
 */
public class GanhuoApplication extends Application{
    private static final String TAG = GanhuoApplication.class.getSimpleName();

    private NetBroadcastReceiver mNetBroadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        registerNetStateListener();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterNetStateListener();
    }

    private void registerNetStateListener() {
        IntentFilter filter = new IntentFilter(NetBroadcastReceiver.NET_CHANGED_ACTION);
        mNetBroadcastReceiver = new NetBroadcastReceiver();
        registerReceiver(mNetBroadcastReceiver, filter);
    }

    private void unregisterNetStateListener() {
        if (mNetBroadcastReceiver != null) {
            unregisterReceiver(mNetBroadcastReceiver);
            mNetBroadcastReceiver = null;
        }
    }
}
