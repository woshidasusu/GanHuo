package com.dasu.ganhuo.ui.home;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.home.HomeController;
import com.dasu.ganhuo.mode.logic.home.SomedayGanHuoEntity;
import com.dasu.ganhuo.ui.base.DrawerActivity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.view.recyclerview.BaseRecyclerView;

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

    private BaseRecyclerView mGanhuoRv;
    private HomeRecycleAdapter mRecycleAdapter;
    private TextView mSubTitleTv;
    private AppBarLayout mAppBarLayout;

    private void initView() {
        //添加 toolbar
        addToolbar((Toolbar)findViewById(R.id.toolbar));
        //init view
        mAppBarLayout = (AppBarLayout) findViewById(R.id.layout_appbar);
        mSubTitleTv = (TextView) findViewById(R.id.tv_home_subtitle);
        mGanhuoRv = (BaseRecyclerView) findViewById(R.id.rv_home_content);
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

    public void updateSubTitle(String subTitle) {
        if (mSubTitleTv != null && mAppBarLayout != null) {
            mAppBarLayout.setExpanded(true, true);
            mSubTitleTv.setText(subTitle);
        }
    }

    @Override
    public void onItemClick(View view, GanHuoEntity data, int position) {
        WebViewActivity.startActivity(mContext, data.getUrl(), data.getDesc());
    }
}
