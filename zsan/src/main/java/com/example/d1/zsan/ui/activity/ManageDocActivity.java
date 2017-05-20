package com.example.d1.zsan.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;
import com.example.d1.zsan.entity.ManageDoc;
import com.example.d1.zsan.ui.adapter.ItemLvMagDocAdapter;
import com.example.d1.zsan.utils.Constant;

public class ManageDocActivity extends ZBaseActivity {
    private ListView mLvContentMadoc;
    private Button mBtCenMadoc;
    private NetJsonUtil mNetJsonUtil;
    @Override
    public int setRootView() {
        return R.layout.activity_manage_doc;
    }

    @Override
    public void initViews() {
        mLvContentMadoc = (ListView) findViewById(R.id.lv_content_madoc);
        mBtCenMadoc = (Button) findViewById(R.id.bt_cen_madoc);
        mBtCenMadoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initDatas() {
        mNetJsonUtil = new NetJsonUtil(Constant.GLOGIN,new magNetCallback());
        mNetJsonUtil.execute();
    }

    private class magNetCallback extends NetCallback<ManageDoc> {
        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onOK(ManageDoc entity) {
            ItemLvMagDocAdapter itemLvMagDocAdapter = new ItemLvMagDocAdapter(mActivitySelf,entity.getList());
            mLvContentMadoc.setAdapter(itemLvMagDocAdapter);
            System.out.println(entity.getList().size());
        }

        @Override
        public void onFinish() {

        }
    }
}
