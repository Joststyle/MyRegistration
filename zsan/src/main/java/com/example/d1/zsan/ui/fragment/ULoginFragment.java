package com.example.d1.zsan.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseFragment;
import com.example.d1.zsan.entity.Subscribe;
import com.example.d1.zsan.ui.activity.MainActivity;
import com.example.d1.zsan.utils.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class ULoginFragment extends ZBaseFragment {
    private ImageView mImgvBannerUlogin;
    private TextView mTvIdUlogin;
    public TextView mTvSubmitUlogin;
    private Button mBtCenUlogin;
    private MainActivity mMainActivity;
    private NetJsonUtil mNetJsonUtil;
    private String strsubscribe="";
    public static int isSubscribe=0;
    public static String doctor="";

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //String doctorname="";
                    //doctorname=doctorname+ (String) msg.obj+"  ";
                    mTvSubmitUlogin.setText("提交申请: 已提交申请"+doctor+"医生");
                    break;
            }
        }
    };

    public ULoginFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_ulogin;
    }

    @Override
    public void initViews() {
        mImgvBannerUlogin = (ImageView) findViewById(R.id.imgv_banner_ulogin);
        mTvIdUlogin = (TextView) findViewById(R.id.tv_id_ulogin);
        mTvSubmitUlogin = (TextView) findViewById(R.id.tv_submit_ulogin);
        mBtCenUlogin = (Button) findViewById(R.id.bt_cen_ulogin);
        mBtCenUlogin.setOnClickListener(new View.OnClickListener() {
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
        mTvIdUlogin.setText("账号："+OneselfFragment.name);
        mNetJsonUtil = new NetJsonUtil(Constant.ULOGIN,new uloginNetCallback());
        mNetJsonUtil.mRequestParams.addParameter("name",OneselfFragment.name);
        mNetJsonUtil.execute();
//        switch (isSubscribe){
//            case 0:
//                mTvSubmitUlogin.setText("提交申请: 无预约申请");
//                break;
//            case 1:
//                mTvSubmitUlogin.setText("提交申请: 已提交申请"+doctor+"医生");
//                break;
//            case 2:
//                mTvSubmitUlogin.setText("提交申请: 您申请"+doctor+"医生已同意");
//        }
    }

    private class uloginNetCallback extends NetCallback<Subscribe> {

        @Override
        public void onError(Throwable e) {
            Log.e("qqq", "onError: ");
            Toast.makeText(mActivitySelf, "home-url-error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOK(Subscribe entity) {
            Log.e("qqq", "oncg: ");
            System.out.println("成功");

            if(entity.getList().size()==0){
                strsubscribe="无预约申请";
            }else{
                for (Subscribe.ListSub listSub:entity.getList()){
                    switch (listSub.getSubscribe()){
                        case "1":
                            strsubscribe=strsubscribe+"已提交申请"+ listSub.getDocname()+"医生";
                            break;
                        case "2":
                            strsubscribe=strsubscribe+"您申请"+ listSub.getDocname()+"医生已同意";
                            break;
                    }

                }
            }
            mTvSubmitUlogin.setText("提交申请: "+strsubscribe);
        }

        @Override
        public void onFinish() {
            Log.e("qqq", "onfs: ");
        }
    }
}
