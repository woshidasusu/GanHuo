package com.dasu.ganhuo.ui.home;

import android.os.Bundle;
import android.view.ViewGroup;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.update.UpdateController;
import com.dasu.ganhuo.ui.base.NetworkListenerActivity;
import com.dasu.ganhuo.ui.update.UpdateDialog;

public class HomeActivity extends NetworkListenerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addNoNetworkTipView((ViewGroup) findViewById(R.id.activity_home));

        UpdateController.checkUpdate(this, new UpdateDialog(this));
    }
}
