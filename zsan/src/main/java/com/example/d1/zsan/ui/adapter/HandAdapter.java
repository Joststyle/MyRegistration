package com.example.d1.zsan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d1.zsan.R;
import com.example.d1.zsan.entity.HandHolder;

/**
 * Created by A on 2017/5/11.
 */

public class HandAdapter extends RecyclerView.Adapter<HandHolder>{
    Context context;
    private int[] headimgs;
    public HandAdapter(Context context , int[] headimgs){
        this.context=context;
        this.headimgs=headimgs;
    }
    @Override
    public HandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.item_hand_ing,parent,false);
        HandHolder myHolder = new HandHolder(itemview);
        return myHolder;

    }

    @Override
    public void onBindViewHolder(final HandHolder holder, int position) {
        holder.mImageView.setImageResource(headimgs[position]);
        //判断是否设置了监听器
        if(mOnItemClickListener!=null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return headimgs.length;
    }
    //设置了两个每部接口   用来实现点击
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }
    //点击监听的属性   和set方法
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
