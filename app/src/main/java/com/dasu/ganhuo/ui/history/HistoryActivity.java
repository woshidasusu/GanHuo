package com.dasu.ganhuo.ui.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.history.HistoryController;
import com.dasu.ganhuo.mode.logic.home.HtmlDataEntity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.base.SubpageWithToolbarActivity;
import com.dasu.ganhuo.ui.view.recyclerview.LoadMoreRecyclerView;
import com.dasu.ganhuo.ui.view.recyclerview.OnPullUpRefreshListener;
import com.dasu.ganhuo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasu on 2017/4/18.
 *
 * 往期推荐界面，二级界面
 */

public class HistoryActivity extends SubpageWithToolbarActivity implements OnItemClickListener<HtmlDataEntity> {

    @Override
    public String getToolbarTitle() {
        return "往期推荐";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initVariable();
        initView();
        mHistoryController.loadBaseData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private List<HtmlDataEntity> mHistoryList;
    private HistoryController mHistoryController;

    private void initVariable() {
        mHistoryList = new ArrayList<>();
        mHistoryController = new HistoryController(this);
    }

    private LoadMoreRecyclerView mHistoryRv;
    private HistoryRecycleAdapter mRecycleAdapter;

    private void initView() {
        mHistoryRv = (LoadMoreRecyclerView) findViewById(R.id.rv_history_content);
        mHistoryRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleAdapter = new HistoryRecycleAdapter(mHistoryList);
        mRecycleAdapter.setOnItemClickListener(this);
        mHistoryRv.setAdapter(mRecycleAdapter);
        mHistoryRv.setOnPullUpRefreshListener(onPullUpRefresh());
    }

    private OnPullUpRefreshListener onPullUpRefresh() {
        return new OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                ToastUtils.show(mContext, "上拉刷新");
            }
        };
    }

    public void updateHistory(List<HtmlDataEntity> data) {
        mRecycleAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, HtmlDataEntity data, int position) {
        ToastUtils.show(mContext, data.getTitle());
    }
}
