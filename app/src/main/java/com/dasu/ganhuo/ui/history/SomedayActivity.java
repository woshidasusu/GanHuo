package com.dasu.ganhuo.ui.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.history.SomedayController;
import com.dasu.ganhuo.ui.base.SubpageWithToolbarActivity;
import com.dasu.ganhuo.ui.view.recyclerview.BaseRecyclerView;

/**
 * Created by dasu on 2017/4/27.
 */

public class SomedayActivity extends SubpageWithToolbarActivity{
    private static final String TAG = SomedayActivity.class.getSimpleName();

    @Override
    public String getToolbarTitle() {
        return mTitle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someday);
        initVariable();
        initView();
    }

    private String mTitle;
    private String mContent;
    private SomedayController mSomedayController;

    private void initVariable() {
        Intent intent = getIntent();
        if (intent != null) {
            mTitle = intent.getStringExtra("_title");
            mContent = intent.getStringExtra("_content");
        }
        mSomedayController = new SomedayController(this);
    }

    private BaseRecyclerView mRecyclerView;

    private void initView() {
        mRecyclerView = (BaseRecyclerView) findViewById(R.id.rv_someday_content);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }



    public static void startActivity(Context context, String title, String content) {
        Intent intent = new Intent(context, SomedayActivity.class);
        intent.putExtra("_title", title);
        intent.putExtra("_content", content);
        context.startActivity(intent);
    }
}
