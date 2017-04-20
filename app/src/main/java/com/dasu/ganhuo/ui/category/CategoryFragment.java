package com.dasu.ganhuo.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.category.ICategoryType;
import com.dasu.ganhuo.ui.base.BaseFragment;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.utils.LogUtils;
import com.dasu.ganhuo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasu on 2017/4/20.
 */

public class CategoryFragment extends BaseFragment implements ICategoryType{
    private static final String TAG = CategoryFragment.class.getSimpleName();

    @Override
    public String getCategoryType() {
        return mCategoryType;
    }

    private String mCategoryType;

    public CategoryFragment() {
    }

    private CategoryActivity mCategoryActivity;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof CategoryActivity)) {
            LogUtils.e(TAG, TAG + "绑定错误的Activity");
            throw new UnsupportedOperationException(TAG + "绑定错误的Activity");
        }
        mContext = context;
        mCategoryActivity = (CategoryActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        mCategoryActivity.getCategoryController().loadBaseData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        return view;
    }

    private List<GanHuoEntity> mGanHuoList;

    private void initVariable() {
        mGanHuoList = new ArrayList<>();
        Bundle bundle = getArguments();
        mCategoryType = bundle.getString("_type", null);
    }

    private RecyclerView mCategoryRv;
    private CategoryRecycleAdapter mRecycleAdapter;


    private void initView(View view) {
        mCategoryRv = (RecyclerView) view.findViewById(R.id.rv_category_content);
        mCategoryRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleAdapter = new CategoryRecycleAdapter(mGanHuoList);
        mRecycleAdapter.setOnItemClickListener(onItemClick());
        mCategoryRv.setAdapter(mRecycleAdapter);
    }

    private OnItemClickListener<GanHuoEntity> onItemClick() {
        return new OnItemClickListener<GanHuoEntity>() {
            @Override
            public void onItemClick(View view, GanHuoEntity data, int position) {
                ToastUtils.show(mContext, data.getDesc());
            }
        };
    }


    public CategoryFragment setArguments(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("_type", type);
        setArguments(bundle);
        return this;
    }

    @Override
    public void updateGanHuo(List<GanHuoEntity> data) {
        if (mRecycleAdapter != null) {
            mRecycleAdapter.setData(data);
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        LogUtils.d(TAG, "fragment visiable: " + isVisible + "   " + mCategoryType);
    }

}
