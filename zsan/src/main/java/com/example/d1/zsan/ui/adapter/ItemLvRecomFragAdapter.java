package com.example.d1.zsan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.d1.zsan.R;
import com.example.d1.zsan.entity.Doctor;
import com.example.d1.zsan.ui.activity.ApplyActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2017/5/10.
 */

public class ItemLvRecomFragAdapter extends BaseAdapter {
    List<Doctor.ListBean> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;
    public ItemLvRecomFragAdapter(Context context, List<Doctor.ListBean> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }
    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public Doctor.ListBean getItem(int position) {
        return  mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_rv_recom, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Doctor.ListBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(final Doctor.ListBean entity, ViewHolder holder, int position) {
        holder.mTextViewhospital.setText(entity.getHospital());
        holder.mTextViewdepartment.setText(entity.getDepartment());
        holder.mTextViewname.setText(entity.getName());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ApplyActivity.class);
                intent.putExtra("name",entity.getName());
                intent.putExtra("hospital",entity.getHospital());
                intent.putExtra("department",entity.getDepartment());
                intent.putExtra("describe",entity.getDescribe());
                intent.putExtra("doc",entity.getDoc());
                context.startActivity(intent);
            }
        });
    }

    protected class ViewHolder {

        private TextView mTextViewname;
        private TextView mTextViewhospital;
        private TextView mTextViewdepartment;
        private Button mButton;

        public ViewHolder(View view) {
            mTextViewhospital = (TextView) view.findViewById(R.id.tv_title_item_lv_limit);
            mTextViewdepartment = (TextView) view.findViewById(R.id.tv_old_price_item_lv_limit);
            mTextViewname= (TextView) view.findViewById(R.id.tv_xxtj_item_lv_limit);
            mButton= (Button) view.findViewById(R.id.bt_buy_item_lv_limit);
        }
    }
}
