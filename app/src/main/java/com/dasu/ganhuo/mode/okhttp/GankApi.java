package com.dasu.ganhuo.mode.okhttp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dasu on 2016/8/25.
 *
 * gank.io 的 api
 */
interface GankApi {
    /**
     * 获取发布过干货的日期
     */
    @GET("day/history")
    Call getGankDay();

    /**
     * 获取指定type类型的data数据
     */
    @GET("data/{type}/{count}/{page}")
    Call getData(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    /**
     * 获取指定日期的每日干货
     */
    @GET("day/{y}/{M}/{d}")
    Call getDayGankData(@Path("y") String year, @Path("M") String month, @Path("d") String day);
}
