package com.example.d1.zsan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.d1.zsan.R;
import com.example.d1.zsan.entity.HomeEntity;
import com.example.d1.zsan.ui.activity.DetailedActivity;

import java.util.List;

/**
 * Created by A on 2017/5/9.
 */

public class ItemLvHomeFragAdapter extends BaseAdapter{
    List<HomeEntity.ListBean> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLvHomeFragAdapter(Context context, List<HomeEntity.ListBean> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public HomeEntity.ListBean getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lv_home, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((HomeEntity.ListBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(final HomeEntity.ListBean entity, ViewHolder holder, int position) {
        //TODO implement
        holder.mTextView.setText(entity.getInformation());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("content",entity.getContent());
                intent.putExtra("information",entity.getInformation());
                context.startActivity(intent);
            }
        });
    }

    protected class ViewHolder {

        private TextView mTextView;

        public ViewHolder(View view) {

            mTextView= (TextView) view.findViewById(R.id.tv_title_lv_home_frag);
        }
    }
}
