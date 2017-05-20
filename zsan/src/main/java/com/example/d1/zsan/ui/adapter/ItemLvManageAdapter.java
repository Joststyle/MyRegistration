package com.example.d1.zsan.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.d1.zsan.R;
import com.example.d1.zsan.entity.Subscribe;

import java.util.List;

/**
 * Created by A on 2017/5/15.
 */

public class ItemLvManageAdapter extends BaseAdapter {
    List<Subscribe.ListSub> mListSubs ;
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLvManageAdapter(Context context,List<Subscribe.ListSub> listSubs){
        this.context =context;
        mListSubs =listSubs;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListSubs.size();
    }

    @Override
    public Subscribe.ListSub getItem(int i) {
        return mListSubs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_lv_manage, viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        initializeViews((Subscribe.ListSub)getItem(i), (ViewHolder) view.getTag(),i);
        return view;
    }

    private void initializeViews(final Subscribe.ListSub listSub, ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(listSub.getUsername());
        viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                boolean check =checkBox.isChecked();
                listSub.setIscheck(check);
            }
        });
    }

    protected class ViewHolder{
        private TextView mTextView;
        private CheckBox mCheckBox;
        public ViewHolder(View view){
            mTextView = (TextView) view.findViewById(R.id.tv_lv_manage);
            mCheckBox = (CheckBox) view.findViewById(R.id.cb_iv_manage);
        }
    }
}
