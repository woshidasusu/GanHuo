package com.dasu.ganhuo.ui.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.ui.base.BaseFragment;
import com.dasu.ganhuo.utils.LogUtils;

/**
 * Created by dasu on 2017/4/20.
 */

public class CategoryFragment extends BaseFragment{
    private static final String TAG = CategoryFragment.class.getSimpleName();

    private String mType;

    public CategoryFragment() {
        if (isStateSaved()) {
            Bundle bundle = getArguments();
            mType = bundle.getString("_type", "null");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_category);
//        tv.setText(mType);
        return view;
    }

    public CategoryFragment setArguments(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("_type", type);
        setArguments(bundle);
        mType = type;
        return this;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        LogUtils.d(TAG, "fragment visiable: " + isVisible + "   " + mType);
    }
}
