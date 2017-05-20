package com.example.d1.zsan.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseFragment;
import com.example.d1.zsan.ui.activity.MainActivity;
import com.example.d1.zsan.ui.activity.ManageDocActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GLoginFragment extends ZBaseFragment {

    private ImageView mImgvBannerGlogin;
    private TextView mTvIdGlogin;
    private Button mBtManageGlogin;
    private Button mBtCenGlogin;
    private MainActivity mMainActivity;


    public GLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_glogin;
    }

    @Override
    public void initViews() {
        mImgvBannerGlogin = (ImageView) findViewById(R.id.imgv_banner_glogin);
        mTvIdGlogin = (TextView) findViewById(R.id.tv_id_glogin);
        mBtManageGlogin = (Button) findViewById(R.id.bt_manage_glogin);
        mBtCenGlogin = (Button) findViewById(R.id.bt_cen_glogin);
        mBtManageGlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(ManageDocActivity.class);
            }
        });
        mBtCenGlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isshow=0;
                mMainActivity.changeHS(mMainActivity.mSelfFragment);
            }
        });
    }

    @Override
    public void initDatas() {
        mMainActivity =(MainActivity) mActivitySelf;
        mTvIdGlogin.setText("账号"+OneselfFragment.name);
    }

}
