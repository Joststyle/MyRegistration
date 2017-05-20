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
public class WelcomeFragment extends ZBaseFragment {




    @Override
    public int setRootView() {
        return R.layout.fragment_welcome;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }
}
