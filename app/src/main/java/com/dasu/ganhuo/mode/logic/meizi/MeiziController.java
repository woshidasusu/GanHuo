package com.dasu.ganhuo.mode.logic.meizi;

import android.content.Context;

import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.meizi.MeiziActivity;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.List;

/**
 * Created by dasu on 2017/4/18.
 */

public class MeiziController {
    private static final String TAG = MeiziController.class.getSimpleName();

    private Context mContext;
    private MeiziActivity mMeiziActivity;

    public MeiziController(Context context) {
        if (!(context instanceof MeiziActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mMeiziActivity = (MeiziActivity) context;
    }

    private final String MEIZI_TYPE = GankController.TYPE_MEIZI;

    public void loadBaseData() {
        GankController.getSpecifyGanHuo(MEIZI_TYPE, 1, new RetrofitListener<List<GanHuoEntity>>() {
            @Override
            public void onSuccess(List<GanHuoEntity> data) {
                mMeiziActivity.updateMeizi(data);
            }

            @Override
            public void onError(String description) {

            }
        });
    }

}
