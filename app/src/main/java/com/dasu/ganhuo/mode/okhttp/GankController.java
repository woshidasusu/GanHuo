package com.dasu.ganhuo.mode.okhttp;

import com.dasu.ganhuo.BuildConfig;

import retrofit2.Callback;


/**
 * Created by dasu on 2017/4/8.
 */
public class GankController {
    private static final String TAG = GankController.class.getSimpleName();

    private static GankApi getGankApi() {
        return GankApiSingleton.mInstance;
    }

    public static void getData(String type, int count, int page, Callback callback) {
        getGankApi().getData(type, count, page).enqueue(callback);
    }

    public static void getGankDay(Callback callback) {
        getGankApi().getGankDay().enqueue(callback);
    }

    public static void getDayGankData(String year, String month, String day, Callback callback) {
        getGankApi().getDayGankData(year, month, day).enqueue(callback);
    }

    private static class GankApiSingleton{
        private static GankApi mInstance = RetrofitHelper.newRetrofit(BuildConfig.GAND_SERVICE).create(GankApi.class);
    }

}
