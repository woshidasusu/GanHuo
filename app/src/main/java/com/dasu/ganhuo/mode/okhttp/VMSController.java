package com.dasu.ganhuo.mode.okhttp;

import android.text.TextUtils;

import com.dasu.ganhuo.BuildConfig;
import com.dasu.ganhuo.mode.okhttp.entity.VersionResEntity;
import com.dasu.ganhuo.utils.LogUtils;

import retrofit2.Callback;

/**
 * Created by dasu on 2017/4/8.
 */
public class VMSController {
    private static final String TAG = VMSController.class.getSimpleName();

    private static VMSApi getVMSApi() {
        return VMSApiSingleton.sInstance;
    }


    public static void queryVersion(Callback<VersionResEntity> callback) {
        String id = BuildConfig.APPID;
        String apiToken = BuildConfig.APITOKEN;
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(apiToken)) {
            LogUtils.e(TAG, "没有 fir.im 的appId 和 API TOKEN");
            callback.onFailure(null, new Throwable("没有 fir.im 的appId 和 API TOKEN"));
            return;
        }
        getVMSApi().queryVersion(id, apiToken).enqueue(callback);
    }

    private static class VMSApiSingleton {
        private static VMSApi sInstance = RetrofitHelper.newRetrofit(BuildConfig.VMS).create(VMSApi.class);
    }
}
