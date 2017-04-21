package com.dasu.ganhuo.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.category.GanHuoHelper;
import com.dasu.ganhuo.mode.logic.home.SomedayGanHuoEntity;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dasu on 2017/4/17.
 *
 */

class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.ViewHolder> {
    private static final String TAG = HomeRecycleAdapter.class.getSimpleName();

    private List<GanHuoEntity> mDataList;
    private OnItemClickListener<GanHuoEntity> mClickListener;
    private Context mContext;


    public HomeRecycleAdapter(SomedayGanHuoEntity data) {
        setData(data);
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
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
        holder.mTitleTv.setText(data.getDesc());
        holder.mAuthorTv.setText(data.getWho());
        setSource(data.getUrl(), holder.mSourceTv);
        setType(data.getType(), holder.mTypeTv);
        setDate(data.getPublishedAt(), holder.mDateTv);
    }

    private void setType(String type, TextView tv) {
        tv.setBackgroundColor(mContext.getResources().getColor(GanHuoHelper.getTypeColor(type)));
        tv.setText(type);
    }

    private void setSource(String url, TextView tv) {
        tv.setBackgroundColor(mContext.getResources().getColor(GanHuoHelper.getSourceColor(url)));
        tv.setText(GanHuoHelper.getUrlSource(url));
    }

    private void setDate(Date date, TextView tv) {
        String time = TimeUtils.howLongAgo(TimeUtils.adjustDate(date));
        tv.setText(time);
    }

    public void setData(SomedayGanHuoEntity data) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        if (data == null || data.getResults() == null) {
            return;
        }
        mDataList.clear();
        for (List<GanHuoEntity> l : data.getResults().getAllList()) {
            for (GanHuoEntity g : l) {
                mDataList.add(g);
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<GanHuoEntity> listener) {
        mClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTv;
        TextView mTypeTv;
        TextView mSourceTv;
        TextView mAuthorTv;
        TextView mDateTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.tv_home_item_title);
            mTypeTv = (TextView) itemView.findViewById(R.id.tv_home_item_type);
            mAuthorTv = (TextView) itemView.findViewById(R.id.tv_home_item_author);
            mDateTv = (TextView) itemView.findViewById(R.id.tv_home_item_date);
            mSourceTv = (TextView) itemView.findViewById(R.id.tv_home_item_source);
        }
    }
}
