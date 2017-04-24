package com.dasu.ganhuo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.home.HomeController;
import com.dasu.ganhuo.mode.logic.home.SomedayGanHuoEntity;
import com.dasu.ganhuo.ui.base.DrawerActivity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.utils.ToastUtils;

/**
 * 今日推荐页面，只负责界面数据的展示，业务逻辑交由{@link HomeController} 负责
 * 双方互相持有引用，可直接交互
 */
public class HomeActivity extends DrawerActivity implements OnItemClickListener<GanHuoEntity> {
    private static final String TAG = HomeActivity.class.getSimpleName();

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
        mHomeController.loadBaseData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //今日的干货数据
    private SomedayGanHuoEntity mSomedayGanHuo;
    private HomeController mHomeController;

    private void initVariable() {
        mSomedayGanHuo = new SomedayGanHuoEntity();
        mHomeController = new HomeController(this);
    }

    private RecyclerView mGanhuoRv;
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
        mRecycleAdapter.setOnItemClickListener(this);
    }

    /**
     * 与HomeController交互的接口，更新今日的干货列表
     *
     * @param somedayGanHuo
     */
    public void updateGanhuoRv(SomedayGanHuoEntity somedayGanHuo) {
        mSomedayGanHuo = somedayGanHuo;
        mRecycleAdapter.setData(mSomedayGanHuo);
    }

    @Override
    public void onItemClick(View view, GanHuoEntity data, int position) {
        //todo 列表项点击事件
        ToastUtils.show(mContext, data.getType());
        startActivity(new Intent(mContext, DebugActivity.class));
    }
}
