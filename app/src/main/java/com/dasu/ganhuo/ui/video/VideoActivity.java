package com.dasu.ganhuo.ui.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.ui.base.DrawerActivity;

/**
 * Created by suxq on 2017/4/14.
 */

public class VideoActivity extends DrawerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initVariable();
        initView();
        loadData();
    }

    private void initVariable() {

    }

    private void initView() {
        //添加 toolbar
        addToolbar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("休闲视频");
    }

    private void loadData() {

    }

    @Override
    protected int bindMenuId() {
        return MENU_VIDEO;
    }
}
