package com.dasu.ganhuo.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.home.SomedayGanHuoEntity;
import com.dasu.ganhuo.mode.logic.update.UpdateController;
import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.base.DrawerActivity;
import com.dasu.ganhuo.ui.update.UpdateDialog;
import com.dasu.ganhuo.utils.LogUtils;

public class HomeActivity extends DrawerActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private RecyclerView mGanhuoRv;

    @Override
    protected int bindMenuId() {
        return MENU_HOME;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initVariable();
        initView();
        loadData();
    }

    private void initVariable() {
        mSomedayGanHuo = new SomedayGanHuoEntity();
    }

    private SomedayGanHuoEntity mSomedayGanHuo;
    private HomeRecycleAdapter mRecycleAdapter;

    private void initView() {
        //添加 toolbar
        addToolbar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("今日推荐");
        //init view
        mGanhuoRv = (RecyclerView) findViewById(R.id.rv_home_content);
        mGanhuoRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleAdapter = new HomeRecycleAdapter(mSomedayGanHuo);
        mGanhuoRv.setAdapter(mRecycleAdapter);

    }

    private void loadData() {
        //发起版本更新检查
        UpdateController.checkUpdate(this, new UpdateDialog(this));
        GankController.getSomedayGanHuo("2017-04-10", new RetrofitListener<SomedayGanHuoEntity>() {
            @Override
            public void onSuccess(SomedayGanHuoEntity data) {
                mRecycleAdapter.setData(data);
                LogUtils.d(TAG, data.toString());
            }

            @Override
            public void onError(String description) {

            }
        });
    }





}
