package com.dasu.ganhuo.mode.logic.history;

import android.content.Context;

import com.dasu.ganhuo.mode.logic.home.HtmlDataEntity;
import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.history.HistoryActivity;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.List;

/**
 * Created by suxq on 2017/4/18.
 */

public class HistoryController {
    private static final String TAG = HistoryController.class.getSimpleName();

    private Context mContext;
    private HistoryActivity mHistoryActivity;

    public HistoryController(Context context) {
        if (!(context instanceof HistoryActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mHistoryActivity = (HistoryActivity) context;
    }

    public void loadBaseData() {
        GankController.getHistoryHtmlData(1, new RetrofitListener<List<HtmlDataEntity>>() {
            @Override
            public void onSuccess(List<HtmlDataEntity> data) {
                mHistoryActivity.updateHistory(data);
            }

            @Override
            public void onError(String description) {

            }
        });
    }
}
