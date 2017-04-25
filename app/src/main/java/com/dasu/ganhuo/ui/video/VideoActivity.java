package com.dasu.ganhuo.ui.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.video.VideoController;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.base.SubpageWithToolbarActivity;
import com.dasu.ganhuo.ui.home.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasu on 2017/4/14.
 *
 * 休闲视频界面，二级界面，只展示界面数据，数据的获取加载交由{@link VideoController}负责
 * 双方相互持有引用，可直接交互
 */

public class VideoActivity extends SubpageWithToolbarActivity implements OnItemClickListener<GanHuoEntity> {

    @Override
    public String getToolbarTitle() {
        return "休闲视频";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initVariable();
        initView();
        mVideoController.loadBaseData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private List<GanHuoEntity> mVideoList;
    private VideoController mVideoController;

    private void initVariable() {
        mVideoList = new ArrayList<>();
        mVideoController = new VideoController(this);
    }

    private RecyclerView mVideoRv;
    private VideoRecycleAdapter mRecycleAdapter;

    private void initView() {
        mVideoRv = (RecyclerView) findViewById(R.id.rv_video_content);
        mVideoRv.setLayoutManager(new LinearLayoutManager(this));
        mRecycleAdapter = new VideoRecycleAdapter(mVideoList);
        mRecycleAdapter.setOnItemClickListener(this);
        mVideoRv.setAdapter(mRecycleAdapter);
    }

    /**
     * 与VideoController交互的接口，设置列表数据
     *
     * @param data
     */
    public void updateVideo(List<GanHuoEntity> data) {
        if (data != null && data.size() > 0) {
            mRecycleAdapter.setData(data);
        } else {
            //todo 展示无数据界面
        }
    }

    @Override
    public void onItemClick(View view, GanHuoEntity data, int position) {
        WebViewActivity.startActivity(mContext, data.getUrl(), data.getDesc());
    }
}
