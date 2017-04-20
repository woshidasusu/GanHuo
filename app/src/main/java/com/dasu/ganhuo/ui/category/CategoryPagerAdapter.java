package com.dasu.ganhuo.ui.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasu on 2017/4/20.
 */

public class CategoryPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mFragmentList;
    private List<String> mTabNameList;
    private Fragment mCurFragment;

    public CategoryPagerAdapter(FragmentManager fm, List<String> categoryList) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mTabNameList = categoryList;
        for (String s : categoryList) {
            Fragment f = new CategoryFragment().setArguments(s);
            mFragmentList.add(f);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabNameList.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurFragment = (Fragment) object;
    }

    public Fragment getCurrentFragment() {
        return mCurFragment;
    }
}
