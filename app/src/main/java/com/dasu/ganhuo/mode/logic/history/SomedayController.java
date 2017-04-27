package com.dasu.ganhuo.mode.logic.history;

import android.content.Context;

import com.dasu.ganhuo.ui.history.SomedayActivity;
import com.dasu.ganhuo.utils.LogUtils;

/**
 * Created by dasu on 2017/4/27.
 */

public class SomedayController {
    private static final String TAG = SomedayController.class.getSimpleName();

    private Context mContext;
    private SomedayActivity mSomedayActivity;

    public SomedayController(Context context) {
        if (!(context instanceof SomedayActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mSomedayActivity = (SomedayActivity) context;
    }

}
