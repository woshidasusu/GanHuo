package com.dasu.ganhuo.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;

import java.util.List;

/**
 * Created by suxq on 2017/4/17.
 */

public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.ViewHolder> {
    private static final String TAG = HomeRecycleAdapter.class.getSimpleName();

    private List<GanHuoEntity> mDataList;


    public HomeRecycleAdapter(List<GanHuoEntity> data) {
        mDataList = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTitleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick();
    }

}
