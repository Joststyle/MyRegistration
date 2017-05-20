package com.example.d1.zsan.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.d1.zsan.R;
import com.example.d1.zsan.entity.ManageDoc;

import java.util.List;

/**
 * Created by A on 2017/5/15.
 */

public class ItemLvMagDocAdapter extends BaseAdapter {
    List<ManageDoc.ListMagDoc> mListMagDocs;
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLvMagDocAdapter(Context context,List<ManageDoc.ListMagDoc> listMagDoc){
        this.context = context;
        this.mListMagDocs=listMagDoc;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mListMagDocs.size();
    }

    @Override
    public ManageDoc.ListMagDoc getItem(int i) {
        return mListMagDocs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_lv_magdoc, viewGroup,false);
            view.setTag(new ViewHolder(view));
        }
        initializeViews((ManageDoc.ListMagDoc)getItem(i), (ViewHolder) view.getTag(),i);
        return view;
    }

    private void initializeViews(ManageDoc.ListMagDoc listMagDoc, ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(listMagDoc.getName());
    }

    protected class ViewHolder{
        private TextView mTextView;
        public ViewHolder(View view){
            mTextView = (TextView) view.findViewById(R.id.tv_contet__item_magdoc);
        }
    }
}
