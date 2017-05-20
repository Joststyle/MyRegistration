package com.example.d1.zsan.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends ZBaseFragment {


    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_about;
    }

    @Override
    public void initViews() {
        setTitleCenter("开发人员");
    }

    @Override
    public void initDatas() {

    }

}
