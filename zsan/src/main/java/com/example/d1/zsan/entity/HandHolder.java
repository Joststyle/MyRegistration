package com.example.d1.zsan.entity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.d1.zsan.R;

/**
 * Created by A on 2017/5/11.
 */

public class HandHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public HandHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.iv_hand_ing);
    }
}
