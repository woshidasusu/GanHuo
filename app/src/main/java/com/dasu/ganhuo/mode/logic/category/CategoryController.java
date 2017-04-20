package com.dasu.ganhuo.mode.logic.category;

import android.content.Context;

import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.category.CategoryActivity;
import com.dasu.ganhuo.ui.category.CategoryFragment;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasu on 2017/4/20.
 */
//todo 这逻辑是错的，应该一个controller绑定一个fragment，而不是一个controller绑定一个activity，控制多个Fragment
public class CategoryController implements ICategoryType{
    private static final String TAG = CategoryController.class.getSimpleName();

    @Override
    public String getCategoryType() {
        return ((CategoryFragment)(mCategoryActivity.getCurrentFragment())).getCategoryType();
    }

    private Context mContext;
    private CategoryActivity mCategoryActivity;
    private List<String> mCategoryList;

    public CategoryController(Context context) {
        if (!(context instanceof CategoryActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mCategoryActivity = (CategoryActivity) context;
        initVariable();
    }

    private void initVariable() {
        mCategoryList = new ArrayList<>();
        mCategoryList.add(GankController.TYPE_ANDROID);
        mCategoryList.add(GankController.TYPE_IOS);
        mCategoryList.add(GankController.TYPE_APP);
        mCategoryList.add(GankController.TYPE_WEB);
        mCategoryList.add(GankController.TYPE_RECOMMENT);
        mCategoryList.add(GankController.TYPE_OTHER);
    }

    public List<String> getCategoryList() {
        if (mCategoryList == null) {
            mCategoryList = new ArrayList<>();
        }
        return mCategoryList;
    }

    public void loadBaseData() {
        GankController.getSpecifyGanHuo(getCategoryType(), 1, new RetrofitListener<List<GanHuoEntity>>() {
            @Override
            public void onSuccess(List<GanHuoEntity> data) {
                updateGanHuo(data);
            }

            @Override
            public void onError(String description) {

            }
        });
    }

    @Override
    public void updateGanHuo(List<GanHuoEntity> data) {
        ((CategoryFragment)(mCategoryActivity.getCurrentFragment())).updateGanHuo(data);
    }

}
