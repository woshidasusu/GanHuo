package com.dasu.ganhuo.ui.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.view.ScaleImageView;
import com.dasu.ganhuo.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dasu on 2017/4/20.
 */

public class CategoryRecycleAdapter extends RecyclerView.Adapter<CategoryRecycleAdapter.ViewHolder> {

    private List<GanHuoEntity> mDataList;
    private Context mContext;
    private OnItemClickListener<GanHuoEntity> mClickListener;

    public CategoryRecycleAdapter(List<GanHuoEntity> data) {
        setData(data);
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
        setDemoImage(holder.mDemoIv, data.getImages());
        holder.mTitleTv.setText(data.getDesc());
        holder.mAuthorTv.setText(data.getWho());
        setDate(data.getPublishedAt(), holder.mDateTv);
    }

    private void setDemoImage(final ScaleImageView imageView, List<String> images) {
//        Glide.with(mContext)
//                .load(images != null ? images.get(0) : "http://www.baidu.com.jpg")
//                .asBitmap()
//                .placeholder(R.drawable.bg_placeholder_blank)
//                .error(R.drawable.bg_placeholder_blank)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        imageView.setOriginSize(resource.getWidth(), resource.getHeight());
//                        imageView.setImageBitmap(resource);
//                    }
//                });
        Glide.with(mContext)
                .load(images != null ? images.get(0) : "http://www.baidu.com.jpg")
                .crossFade()
                .placeholder(R.drawable.bg_placeholder_blank)
                .error(R.drawable.bg_placeholder_blank)
                .into(imageView);
    }

    private void setDate(Date date, TextView tv) {
        String time = TimeUtils.howLongAgo(TimeUtils.adjustDate(date));
        tv.setText(time);
    }

    public void setData(List<GanHuoEntity> data) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        if (data == null || data.size() == 0 ) {
            return;
        }
        mDataList = data;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnItemClickListener<GanHuoEntity> listener) {
        mClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ScaleImageView mDemoIv;
        TextView mTitleTv;
        TextView mAuthorTv;
        TextView mDateTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mDemoIv = (ScaleImageView) itemView.findViewById(R.id.iv_category_demo);
            mTitleTv = (TextView) itemView.findViewById(R.id.tv_category_title);
            mAuthorTv = (TextView) itemView.findViewById(R.id.tv_category_author);
            mDateTv = (TextView) itemView.findViewById(R.id.tv_category_date);
        }
    }

}
