package com.example.d1.zsan.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;
import com.example.d1.zsan.ui.fragment.OneselfFragment;
import com.example.d1.zsan.ui.fragment.ULoginFragment;
import com.example.d1.zsan.utils.Constant;

public class ApplyActivity extends ZBaseActivity {
    private RelativeLayout mActivityApply;
    private LinearLayout mLlApply;
    private ImageView mIvApply;
    private TextView mTvNameApply;
    private TextView mTvHospitalApply;
    private TextView mTvDepApply;
    private TextView mTvJiehsouApply;
    private Button mBtSubscribeApply;
    private Button mBtCenApply;
    private TextView mTbContentApply;
    Handler mHandler = new Handler();

    private String name,doc;
    private NetJsonUtil mNetJsonUtil;

    @Override
    public int setRootView() {
        return R.layout.activity_apply;
    }

    @Override
    public void initViews() {
        mActivityApply = (RelativeLayout) findViewById(R.id.activity_apply);
        mLlApply = (LinearLayout) findViewById(R.id.ll_apply);
        mIvApply = (ImageView) findViewById(R.id.iv_apply);
        mTvNameApply = (TextView) findViewById(R.id.tv_name_apply);
        mTvHospitalApply = (TextView) findViewById(R.id.tv_hospital_apply);
        mTvDepApply = (TextView) findViewById(R.id.tv_dep_apply);
        mTvJiehsouApply = (TextView) findViewById(R.id.tv_jiehsou_apply);
        mTbContentApply = (TextView) findViewById(R.id.tb_content_apply);
        mBtSubscribeApply = (Button) findViewById(R.id.bt_subscribe_apply);
        mBtCenApply = (Button) findViewById(R.id.bt_cen_apply);

    }

    @Override
    public void initDatas() {
        Intent intent = this.getIntent();
        name = intent.getStringExtra("name");
        String hospital = intent.getStringExtra("hospital");
        String department = intent.getStringExtra("department");
        String describe = intent.getStringExtra("describe");
        doc = intent.getStringExtra("doc");
        mTvNameApply.setText(name);
        mTvHospitalApply.setText(hospital);
        mTvDepApply.setText(department);
        mTbContentApply.setText(describe);
        mBtSubscribeApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.isshow!=1){
                    Toast.makeText(mActivitySelf, "没有用户登录", Toast.LENGTH_SHORT).show();
                }else{

                    ULoginFragment.isSubscribe=1;
                    ULoginFragment.doctor=name;
                    Message msg = Message.obtain();
                   // msg.obj=name;
                    msg.what=1;
                    mHandler.sendMessage(msg);
                    mBtSubscribeApply.setText("预约已提交");

                    url();
                }
            }
        });
        mBtCenApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void url() {
        mNetJsonUtil = new NetJsonUtil(Constant.SUB_URL,new subNetCallback());
        mNetJsonUtil.mRequestParams.addParameter("docname",doc);
        mNetJsonUtil.mRequestParams.addParameter("username", OneselfFragment.name);
        mNetJsonUtil.mRequestParams.addParameter("issubscribe",ULoginFragment.isSubscribe);
        mNetJsonUtil.execute();
    }

    private class subNetCallback extends NetCallback<String> {
        @Override
        public void onError(Throwable e) {
            Log.e("qqq", "onError: ");
            Toast.makeText(mActivitySelf, "home-url-error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOK(String entity) {
            Log.e("qqq", "oncg: ");
            System.out.println("成功");
            if(entity.equals("已拥有")){
                Toast.makeText(mActivitySelf, entity, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFinish() {
            Log.e("qqq", "onfs: ");
        }
    }
}
