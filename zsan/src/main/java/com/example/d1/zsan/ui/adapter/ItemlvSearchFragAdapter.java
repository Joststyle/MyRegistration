package com.example.d1.zsan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d1.zsan.R;
import com.example.d1.zsan.entity.Department;
import com.example.d1.zsan.entity.SearchHolder;

import java.util.List;

/**
 * Created by A on 2017/5/16.
 */

public class ItemlvSearchFragAdapter extends RecyclerView.Adapter<SearchHolder> {
    Context context;
    List<Department.ListDepart> mListDeparts;

    public ItemlvSearchFragAdapter(Context context , List<Department.ListDepart> listDeparts){
        this.context = context;
        this.mListDeparts = listDeparts;
    }
    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.item_lv_search_frag,parent,false);
        SearchHolder searchHolder = new SearchHolder(itemview);
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(final SearchHolder holder, int position) {
        holder.mTextViewname.setText(mListDeparts.get(position).getName());
        holder.mTextViewfunc.setText(mListDeparts.get(position).getFunction());
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
        return mListDeparts.size();
    }
    //设置了两个每部接口   用来实现点击
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }
    //点击监听的属性   和set方法
    private HandAdapter.OnItemClickListener mOnItemClickListener;
    private HandAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(HandAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(HandAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
