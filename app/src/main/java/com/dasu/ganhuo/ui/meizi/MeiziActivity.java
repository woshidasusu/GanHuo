package com.dasu.ganhuo.ui.meizi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.meizi.MeiziController;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.base.SubpageWithToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasu on 2017/4/14.
 *
 * 瀑布流图片界面，二级界面
 */

public class MeiziActivity extends SubpageWithToolbarActivity implements OnItemClickListener<GanHuoEntity> {

    @Override
    public String getToolbarTitle() {
        return "妹子";
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
        mMeiziList = data;
        mRecycleAdapter.setData(data);
    }


    @Override
    public void onItemClick(View view, GanHuoEntity data, int position) {
        ArrayList<String> images = new ArrayList<>();
        for (GanHuoEntity entity : mMeiziList) {
            images.add(entity.getUrl());
        }
        ImageActivity.startActivity(mContext, position, images);
    }
}
