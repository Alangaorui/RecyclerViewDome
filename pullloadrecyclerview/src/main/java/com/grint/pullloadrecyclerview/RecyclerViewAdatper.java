package com.grint.pullloadrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grant on 2018/2/27 0027.
 */

public class RecyclerViewAdatper extends  RecyclerView.Adapter<RecyclerViewAdatper.ViewHolder> implements View.OnClickListener,View.OnLongClickListener{

    private Context mContext;
    private List<String> dataList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void addAllData(List<String> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdatper(Context context) {
        mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public void removeData(int position) {
        dataList.remove(position);//长按删除
        notifyItemRemoved(position);
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.OnLongClick(v,(Integer) v.getTag());
        }
        return true;
    }

    public interface OnItemClickListener {
        void OnItmeClick(View view,int position);
        void OnLongClick(View view,int position);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.OnItmeClick(v,(int)v.getTag());

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        v.setOnLongClickListener(this);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.title.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}