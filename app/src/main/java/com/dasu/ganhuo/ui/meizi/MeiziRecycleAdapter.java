package com.dasu.ganhuo.ui.meizi;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.view.ScaleImageView;

import java.util.List;

/**
 * Created by dasu on 2017/4/18.
 */

class MeiziRecycleAdapter extends RecyclerView.Adapter<MeiziRecycleAdapter.ViewHolder> {

    private static final String TAG = MeiziRecycleAdapter.class.getSimpleName();

    private Context mContext;
    private List<GanHuoEntity> mDataList;
    private OnItemClickListener<GanHuoEntity> mClickListener;

    public MeiziRecycleAdapter(List<GanHuoEntity> data) {
        setData(data);
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final GanHuoEntity data = mDataList.get(position);
        final int posi = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(v, data, posi);
                }
            }
        });
        holder.itemView.setVisibility(View.GONE);
        Glide.with(mContext)
                .load(data.getUrl())
                .asBitmap()
                .placeholder(R.drawable.bg_placeholder_blank)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.itemView.setVisibility(View.VISIBLE);
                        holder.mMeiziIv.setOriginSize(resource.getWidth(), resource.getHeight());
                        holder.mMeiziIv.setImageBitmap(resource);
                    }
                });
    }

    public void setData(List<GanHuoEntity> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        mDataList = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<GanHuoEntity> listener) {
        mClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ScaleImageView mMeiziIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mMeiziIv = (ScaleImageView) itemView.findViewById(R.id.iv_meizi);
        }
    }
}
