package com.dasu.ganhuo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.home.SomedayGanHuoEntity;
import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.base.BaseActivity;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.List;

/**
 * Created by suxq on 2017/4/17.
 */

public class DebugActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }

    public void getSpecifyGanHuo(View view) {
        GankController.getSpecifyGanHuo(GankController.TYPE_ANDROID, 1, new RetrofitListener<List<GanHuoEntity>>() {

            @Override
            public void onSuccess(List<GanHuoEntity> data) {
                LogUtils.d("onsuccess", "da");
            }

            @Override
            public void onError(String description) {

            }
        });
    }


    public void getPublishDate(View view) {
        GankController.getPublishDate(new RetrofitListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                LogUtils.d("onsuccess", data.toString());
            }

            @Override
            public void onError(String description) {

            }
        });
    }

    public void getSomedayGanhuo(View view) {
        GankController.getSomedayGanHuo("2017-04-10", new RetrofitListener<SomedayGanHuoEntity>() {

            @Override
            public void onSuccess(SomedayGanHuoEntity data) {
                LogUtils.d("onsuccess", data.toString());
            }

            @Override
            public void onError(String description) {

            }
        });
    }

}
