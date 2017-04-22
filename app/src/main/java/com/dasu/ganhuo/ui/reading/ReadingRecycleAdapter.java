package com.dasu.ganhuo.ui.reading;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.reading.BlogEntity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by dasu on 2017/4/22.
 */

class ReadingRecycleAdapter extends RecyclerView.Adapter<ReadingRecycleAdapter.ViewHolder> {
    private static final String TAG = ReadingRecycleAdapter.class.getSimpleName();

    private Context mContext;
    private List<BlogEntity> mDataList;
    private OnItemClickListener<BlogEntity> mClickListener;

    ReadingRecycleAdapter(List<BlogEntity> blogList) {
        mDataList = blogList;
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_reading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BlogEntity data = mDataList.get(position);
        final int posi = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(v, data, posi);
                }
            }
        });
        holder.titleTv.setText(data.getTitle());
        holder.dateTv.setText(data.getDate());
        holder.sourceNameTv.setText(data.getSource());
        Glide.with(mContext)
                .load(data.getSourceIcon())
                .transform(new GlideCircleTransform(mContext))
                .into(holder.sourceIconIv);
    }

    public void setOnItemClickListener(OnItemClickListener<BlogEntity> listener) {
        mClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView sourceIconIv;
        TextView sourceNameTv;
        TextView dateTv;
        TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            sourceIconIv = (ImageView) itemView.findViewById(R.id.iv_reading_source);
            sourceNameTv = (TextView) itemView.findViewById(R.id.tv_reading_source);
            dateTv = (TextView) itemView.findViewById(R.id.tv_reading_date);
            titleTv = (TextView) itemView.findViewById(R.id.tv_reading_title);
        }
    }
}
