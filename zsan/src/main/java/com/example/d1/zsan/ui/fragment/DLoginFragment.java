package com.example.d1.zsan.ui.fragment;


import android.content.Intent;
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
import com.example.d1.zsan.ui.activity.ManageActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DLoginFragment extends ZBaseFragment {

    private ImageView mImgvBannerDlogin;
    private TextView mTvIdDlogin;
    private Button mBtManageDlogin;
    private Button mBtCenDlogin;
    private MainActivity mMainActivity;


    public DLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_dlogin;
    }

    @Override
    public void initViews() {
        setTitleCenter("个人中心");
        mImgvBannerDlogin = (ImageView) findViewById(R.id.imgv_banner_dlogin);
        mTvIdDlogin = (TextView) findViewById(R.id.tv_id_dlogin);
        mBtManageDlogin = (Button) findViewById(R.id.bt_manage_dlogin);
        mBtCenDlogin = (Button) findViewById(R.id.bt_cen_dlogin);
        mBtManageDlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivitySelf, ManageActivity.class);
                intent.putExtra("name",OneselfFragment.name);
                mActivitySelf.startActivity(intent);
            }
        });
        mBtCenDlogin.setOnClickListener(new View.OnClickListener() {
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
        mTvIdDlogin.setText("账号"+OneselfFragment.name);
    }

}
