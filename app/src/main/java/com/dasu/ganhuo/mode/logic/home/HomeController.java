package com.dasu.ganhuo.mode.logic.home;

import android.content.Context;

import com.dasu.ganhuo.mode.logic.update.UpdateController;
import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.home.HomeActivity;
import com.dasu.ganhuo.ui.update.UpdateDialog;
import com.dasu.ganhuo.utils.LogUtils;
import com.dasu.ganhuo.utils.TimeUtils;

/**
 * Created by dasu on 2017/4/18.
 * <p>
 * 负责HomeActivity的业务逻辑即数据加载
 */

public class HomeController {
    private static final String TAG = HomeController.class.getSimpleName();

    private Context mContext;
    private HomeActivity mHomeActivity;

    public HomeController(Context context) {
        if (!(context instanceof HomeActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mHomeActivity = (HomeActivity) context;
    }

    public void loadBaseData() {
        //发起版本更新检查
        UpdateController.checkUpdate(mContext, new UpdateDialog(mContext));
        String today = TimeUtils.getCurTimeString(TimeUtils.YMD_SDF);
        //获取当天的干货数据
        GankController.getSomedayGanHuo(today, new RetrofitListener<SomedayGanHuoEntity>() {
            @Override
            public void onSuccess(SomedayGanHuoEntity data) {
                mHomeActivity.updateGanhuoRv(data);
                LogUtils.d(TAG, data.toString());
            }

            @Override
            public void onError(String description) {
                LogUtils.e(TAG, description);
            }
        });
        //获取当天网页数据，用于解析标题
        GankController.getSomedayHtmlData(today, new RetrofitListener<HtmlDataEntity>() {
            @Override
            public void onSuccess(HtmlDataEntity data) {
                if (data != null) {
                    String text = data.getTitle().contains("今日力推")
                            ? data.getTitle().substring(5)
                            : data.getTitle();
                    text = text.replaceAll("/", "\r\n").replaceAll(" ", "");
                    mHomeActivity.updateSubTitle(text);
                }
            }

            @Override
            public void onError(String description) {
                LogUtils.e(TAG, description);
            }
        });

    }
}
