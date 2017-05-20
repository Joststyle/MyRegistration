package com.example.d1.zsan.entity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.d1.zsan.R;

/**
 * Created by A on 2017/5/16.
 */

public class SearchHolder extends RecyclerView.ViewHolder {
    public TextView mTextViewname;
    public TextView mTextViewfunc;
    public SearchHolder(View itemView) {
        super(itemView);
        mTextViewname = (TextView) itemView.findViewById(R.id.tv_name_search_frag);
        mTextViewfunc = (TextView) itemView.findViewById(R.id.tv_function_search_frag);
    }
}
