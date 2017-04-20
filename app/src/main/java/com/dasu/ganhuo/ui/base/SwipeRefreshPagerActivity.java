package com.dasu.ganhuo.ui.base;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;

import com.dasu.ganhuo.utils.LogUtils;

/**
 * Created by dasu on 2017/4/20.
 *
 * 解决下拉刷新SwipeRefreshLayout和ViewPager的滑动事件冲突问题
 */

public abstract class SwipeRefreshPagerActivity extends BaseActivity {
    private static final String TAG = SwipeRefreshPagerActivity.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ViewPager mViewPager;

    public abstract SwipeRefreshLayout findSwipeRefreshLayout();
    public abstract ViewPager findViewPager();

    @Override
    protected void onStart() {
        super.onStart();
        mSwipeRefreshLayout = findSwipeRefreshLayout();
        mViewPager = findViewPager();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d(TAG, event.getAction() + "");
        if (mSwipeRefreshLayout != null && mViewPager != null) {

        }
        return super.onTouchEvent(event);
    }
}
