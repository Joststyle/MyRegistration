package com.example.d1.zsan.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.d1.library.adapter.SimplePagerAdapter;
import com.example.d1.zsan.R;
import com.example.d1.zsan.entity.Banner;

import java.util.List;

/**
 * Created by A on 2017/5/9.
 */

public class ItemVPBannerAdapter extends SimplePagerAdapter<Banner>{

    public ItemVPBannerAdapter(List<Banner> entities, Context context, ViewPager viewPager) {
        super(entities, context, viewPager);
    }

    @Override
    public int setLayouResID() {
        return R.layout.item_vp_home;
    }

    @Override
    public void initView(View itemView, ViewGroup container, int position, Banner entity) {
        ImageView imageView= (ImageView) itemView.findViewById(R.id.imgv_vp_banner);
        imageView.setImageResource(entity.getBan());
    }
}
