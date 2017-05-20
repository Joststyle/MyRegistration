package com.example.d1.zsan.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;
import com.example.d1.zsan.entity.Doctor;
import com.example.d1.zsan.ui.adapter.ItemLvRecomFragAdapter;
import com.example.d1.zsan.utils.Constant;

public class SearchActivity extends ZBaseActivity {
    private ListView mLvSearchActivity;
    private NetJsonUtil mNetJsonUtil;

    @Override
    public int setRootView() {
        return R.layout.activity_search;
    }

    @Override
    public void initViews() {
        setTitleCenter("科室医生");
        mLvSearchActivity = (ListView) findViewById(R.id.lv_search_activity);
    }

    @Override
    public void initDatas() {
        Intent intent = this.getIntent();
        String name = intent.getStringExtra("name");
        mNetJsonUtil = new NetJsonUtil(Constant.SEARCH,new SerarchNetCallback());
        mNetJsonUtil.mRequestParams.addParameter("name",name);
        mNetJsonUtil.execute();
    }


    private class SerarchNetCallback extends NetCallback<Doctor> {
        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onOK(Doctor entity) {
            ItemLvRecomFragAdapter itemLvRecomFragAdapter = new ItemLvRecomFragAdapter(mActivitySelf,entity.getList());
            mLvSearchActivity.setAdapter(itemLvRecomFragAdapter);
        }

        @Override
        public void onFinish() {

        }
    }
}
