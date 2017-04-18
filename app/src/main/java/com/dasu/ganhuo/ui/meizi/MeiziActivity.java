package com.dasu.ganhuo.ui.meizi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.meizi.MeiziController;
import com.dasu.ganhuo.ui.base.DrawerActivity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suxq on 2017/4/14.
 */

public class MeiziActivity extends DrawerActivity implements OnItemClickListener<GanHuoEntity> {

    @Override
    protected int bindMenuId() {
        return MENU_MEIZI;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi);
        initVariable();
        initView();
        mMeiziController.loadBaseData();
    }

    private List<GanHuoEntity> mMeiziList;
    private MeiziController mMeiziController;

    private void initVariable() {
        mMeiziList = new ArrayList<>();
        mMeiziController = new MeiziController(this);
    }

    private RecyclerView mMeiziRv;
    private MeiziRecycleAdapter mRecycleAdapter;

    private void initView() {
        //添加 toolbar
        addToolbar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("妹子");

        mMeiziRv = (RecyclerView) findViewById(R.id.rv_meizi_content);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mMeiziRv.setLayoutManager(layoutManager);
        mRecycleAdapter = new MeiziRecycleAdapter(mMeiziList);
        mRecycleAdapter.setOnItemClickListener(this);
        mMeiziRv.setAdapter(mRecycleAdapter);
        mMeiziRv.addOnScrollListener(addOnScrollListener());
    }

    private RecyclerView.OnScrollListener addOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        };
    }

    public void updateMeizi(List<GanHuoEntity> data) {
        mRecycleAdapter.setData(data);
    }


    @Override
    public void onItemClick(View view, GanHuoEntity data, int position) {
        ToastUtils.show(mContext, data.getDesc());
    }
}
