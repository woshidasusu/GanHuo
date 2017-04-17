package com.dasu.ganhuo.ui.reading;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.ui.base.DrawerActivity;

/**
 * Created by suxq on 2017/4/14.
 */

public class ReadingActivity extends DrawerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
    }

    @Override
    protected int bindMenuId() {
        return MENU_READING;
    }
}
