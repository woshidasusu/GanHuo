package com.dasu.ganhuo.ui.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.home.SomedayGanHuoEntity;
import com.dasu.ganhuo.mode.logic.update.UpdateController;
import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.base.NetworkListenerActivity;
import com.dasu.ganhuo.ui.update.UpdateDialog;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.List;

public class HomeActivity extends NetworkListenerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addNoNetworkTipView((ViewGroup) findViewById(R.id.activity_home));

        UpdateController.checkUpdate(this, new UpdateDialog(this));
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
