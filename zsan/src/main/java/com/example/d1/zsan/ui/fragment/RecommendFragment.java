package com.example.d1.zsan.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseFragment;
import com.example.d1.zsan.entity.Doctor;
import com.example.d1.zsan.ui.adapter.ItemLvHomeFragAdapter;
import com.example.d1.zsan.ui.adapter.ItemLvRecomFragAdapter;
import com.example.d1.zsan.utils.Constant;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends ZBaseFragment{
    private ListView mLvFragRecom;
    private NetJsonUtil mNetJsonUtil;
    private ItemLvRecomFragAdapter mItemLvRecomFragAdapter;


    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initViews() {
        setTitleCenter("推荐");
        mLvFragRecom = (ListView) findViewById(R.id.lv_frag_recom);

        mTitleCenter.setFocusable(true);
        mTitleCenter.setFocusableInTouchMode(true);
        mTitleCenter.requestFocus();
    }

    @Override
    public void initDatas() {
        mNetJsonUtil = new NetJsonUtil(Constant.RECOM_URL,new RecomNetCallback());
        mNetJsonUtil.execute();
    }
    public class RecomNetCallback extends NetCallback<Doctor>{

        @Override
        public void onError(Throwable e) {
            Log.e("qqq", "onError: ");
            Toast.makeText(mActivitySelf, "home-url-error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOK(Doctor entity) {
            Log.e("qqq", "oncg: ");
            System.out.println("成功1");
            showDoc(entity.getList());
        }

        @Override
        public void onFinish() {
            Log.e("qqq", "onfs: ");
        }
    }
    private void showDoc(List<Doctor.ListBean> list) {
        mItemLvRecomFragAdapter = new ItemLvRecomFragAdapter(mActivitySelf,list);
        mLvFragRecom.setAdapter(mItemLvRecomFragAdapter);
        System.out.println(list.size());
    }
}
