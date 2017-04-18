package com.dasu.ganhuo.mode.logic.video;

import android.content.Context;

import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.video.VideoActivity;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.List;

/**
 * Created by suxq on 2017/4/18.
 */

public class VideoController {
    private static final String TAG = VideoController.class.getSimpleName();

    private Context mContext;
    private VideoActivity mVideoActivity;

    public VideoController(Context context) {
        if (!(context instanceof VideoActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mVideoActivity = (VideoActivity) context;
    }

    private final String VIDEO_TYPE = GankController.TYPE_VIDEO;

    public void loadBaseData() {
        GankController.getSpecifyGanHuo(VIDEO_TYPE, 1, new RetrofitListener<List<GanHuoEntity>>() {
            @Override
            public void onSuccess(List<GanHuoEntity> data) {
                mVideoActivity.updateVideo(data);
            }

            @Override
            public void onError(String description) {

            }
        });
    }


}
