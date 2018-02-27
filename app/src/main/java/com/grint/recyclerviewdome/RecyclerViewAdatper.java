package com.grint.recyclerviewdome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by grant on 2018/2/27 0027.
 */

public class RecyclerViewAdatper extends RecyclerView.Adapter<RecyclerViewAdatper.MyViewHolder> implements View.OnClickListener,View.OnLongClickListener {

    private List<String> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdatper(Context context,List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_itme_adatper,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);//初始化监听
        view.setOnLongClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdatper.MyViewHolder holder, int position) {
        holder.itemView.setTag(position);//设置点击的事件传输的position
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void removeData(int position) {
        mList.remove(position);//长按删除
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItmeClick(View view,int position);
        void OnLongClick(View view,int position);
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.OnItmeClick(v, (Integer) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.OnLongClick(v,(Integer) v.getTag());
        }
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
