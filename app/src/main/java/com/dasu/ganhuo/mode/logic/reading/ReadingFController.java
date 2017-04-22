package com.dasu.ganhuo.mode.logic.reading;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.dasu.ganhuo.mode.okhttp.ReadingController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.reading.ReadingFragment;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.List;

/**
 * Created by dasu on 2017/4/22.
 */

public class ReadingFController {

    private static final String TAG = ReadingFController.class.getSimpleName();

    private Context mContext;
    private ReadingFragment mReadingFragment;
    private String mReadingType;

    public ReadingFController(Fragment fragment) {
        if (!(fragment instanceof ReadingFragment)) {
            LogUtils.e(TAG, TAG + "绑定错误的Fragment");
            throw new UnsupportedOperationException(TAG + "绑定错误的Fragment");
        }
        mContext = fragment.getContext();
        mReadingFragment = (ReadingFragment) fragment;
        mReadingType = mReadingFragment.getReadingType();
        if (TextUtils.isEmpty(mReadingType)) {
            LogUtils.e(TAG, "ReadingFragment 必须实现IReadingType接口，指定返回某一type类型");
            throw new UnsupportedOperationException("ReadingFragment 必须实现IReadingType接口，指定返回某一type类型");
        }
    }

    public void loadBaseData() {
        ReadingController.getSpecifyType(mReadingType, 1, new RetrofitListener<List<BlogEntity>>() {
            @Override
            public void onSuccess(List<BlogEntity> data) {
                mReadingFragment.updateBlogs(data);
            }

            @Override
            public void onError(String description) {
                mReadingFragment.onLoadFailed();
            }
        });
    }


}
