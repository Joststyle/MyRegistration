package com.example.d1.zsan.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;
import com.example.d1.zsan.entity.Subscribe;
import com.example.d1.zsan.ui.adapter.ItemLvManageAdapter;
import com.example.d1.zsan.utils.Constant;

import java.util.Iterator;
import java.util.List;

public class ManageActivity extends ZBaseActivity {
    private ListView mLvManage;
    private ItemLvManageAdapter mItemLvManageAdapter;
    private NetJsonUtil mNetJsonUtil;
    private Button mBtAllowManage;
    private Subscribe mSubscribe;
    private List<Subscribe.ListSub> mListSubList;
    private String mString ="";
    @Override
    public int setRootView() {
        return R.layout.activity_manage;
    }

    @Override
    public void initViews() {
        setTitleCenter("申请管理");
        mLvManage = (ListView) findViewById(R.id.lv_manage);
    }

    @Override
    public void initDatas() {
        Intent intent = this.getIntent();
        String name =intent.getStringExtra("name");
        mNetJsonUtil = new NetJsonUtil(Constant.DLOGIN,new DloginNetCallback());
        mNetJsonUtil.mRequestParams.addBodyParameter("name",name);
        mNetJsonUtil.execute();
        mBtAllowManage = (Button) findViewById(R.id.bt_allow_manage);
        mBtAllowManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListSubList =mSubscribe.getList();
                for(Subscribe.ListSub listSub:mListSubList){
                    if(listSub.isIscheck()){
                        int id = listSub.getId();
                        String sid = String.valueOf(id);
                        Log.e("ggg",sid);
                        System.out.println(sid);
                        //mString = mString+sid+":";
                        mNetJsonUtil = new NetJsonUtil(Constant.UPDATE,new UpdateNetCallback());
                        mNetJsonUtil.mRequestParams.addParameter("id",sid);
                        mNetJsonUtil.execute();
                    }
                }
                finish();
                //mNetJsonUtil.mRequestParams.addBodyParameter();
            }
        });
        //mItemLvManageAdapter = new ItemLvManageAdapter(mActivitySelf,)
    }

    private class DloginNetCallback extends NetCallback<Subscribe> {
        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onOK(Subscribe entity) {
            mSubscribe= entity;
            mItemLvManageAdapter = new ItemLvManageAdapter(mActivitySelf,entity.getList());
            mLvManage.setAdapter(mItemLvManageAdapter);
        }

        @Override
        public void onFinish() {

        }
    }

    private class UpdateNetCallback extends NetCallback<String> {
        @Override
        public void onError(Throwable e) {
            Log.e("qqq", "onError: ");
        }

        @Override
        public void onOK(String entity) {
            Log.e("qqq", "oncg: ");
            System.out.println("成功update");
        }

        @Override
        public void onFinish() {

        }
    }
}
