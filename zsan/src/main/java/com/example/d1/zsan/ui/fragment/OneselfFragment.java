package com.example.d1.zsan.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseFragment;
import com.example.d1.zsan.ui.activity.MainActivity;
import com.example.d1.zsan.ui.activity.RegisterActivity;
import com.example.d1.zsan.utils.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneselfFragment extends ZBaseFragment implements View.OnClickListener {
    private EditText mEtAccountFragSelf;
    private EditText mEtPasswordFragSelf;
    private Button mBtLoginFragSelf;
    private Button mBtRegistered;
    private RadioGroup mRgStutasSelf;
    private RadioButton mRbUserRgSelf;
    private RadioButton mRbDoctorRgSelf;
    private RadioButton mRbAdminRgSelf;
    public static String name="";
    private String pwd,onestatus;
    private NetJsonUtil mNetJsonUtil;
    private int isshow=0;
    private MainActivity mMainActivity;
    public OneselfFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_oneself;
    }

    @Override
    public void initViews() {
        setTitleCenter("自己");
        mEtAccountFragSelf = (EditText) findViewById(R.id.et_account_frag_self);
        mEtPasswordFragSelf = (EditText) findViewById(R.id.et_password_frag_self);
        mRgStutasSelf = (RadioGroup) findViewById(R.id.rg_stutas_self);
        mRbUserRgSelf = (RadioButton) findViewById(R.id.rb_user_rg_self);
        mRbDoctorRgSelf = (RadioButton) findViewById(R.id.rb_doctor_rg_self);
        mRbAdminRgSelf = (RadioButton) findViewById(R.id.rb_admin_rg_self);

        mBtLoginFragSelf = (Button) findViewById(R.id.bt_login_frag_self);
        mBtRegistered = (Button) findViewById(R.id.bt_registered);

        mBtLoginFragSelf.setOnClickListener(this);
        mBtRegistered.setOnClickListener(this);

    }

    @Override
    public void initDatas() {
        mMainActivity =(MainActivity) mActivitySelf;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login_frag_self:
                Toast.makeText(mActivitySelf, "dd", Toast.LENGTH_SHORT).show();
                hava();
                onesubmit();
                break;
            case R.id.bt_registered:
                goToActivity(RegisterActivity.class);
                break;
        }
    }

    private void hava() {
        name = mEtAccountFragSelf.getText().toString();
        pwd = mEtPasswordFragSelf.getText().toString();
        switch (mRgStutasSelf.getCheckedRadioButtonId()){
            case R.id.rb_user_rg_self:
                onestatus = mRbUserRgSelf.getText().toString();
                break;
            case R.id.rb_doctor_rg_self:
                onestatus = mRbDoctorRgSelf.getText().toString();
                break;
            case R.id.rb_admin_rg_self:
                onestatus ="管理";
                break;
        }
    }

    private void onesubmit() {
        mNetJsonUtil = new NetJsonUtil(Constant.LOGIN_URL,new OneNetCallback());
        mNetJsonUtil.mRequestParams.addParameter("onename",name);
        mNetJsonUtil.mRequestParams.addParameter("onepwd",pwd);
        mNetJsonUtil.mRequestParams.addBodyParameter("onestutas",onestatus);
        mNetJsonUtil.execute();
    }

    private class OneNetCallback extends NetCallback<String> {
        @Override
        public void onError(Throwable e) {
            Log.e("qqq", "onError: ");
            Toast.makeText(mActivitySelf, "home-url-error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOK(String entity) {
            Log.e("qqq", "oncg: ");
            System.out.println("成功");
            switch (entity){
                case "用户登录成功":
                    //isshow=1;
                    //showFrag(mDLoginFragment);
                    MainActivity.isshow=1;
                    mMainActivity.changeHS(mMainActivity.mULoginFragment);
                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_SHORT).show();
                    break;
                case "医生登录成功":
                    MainActivity.isshow=2;
                    mMainActivity.changeHS(mMainActivity.mDLoginFragment);
                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_SHORT).show();
                    break;
                case "管理登录成功":
                    MainActivity.isshow=3;
                    mMainActivity.changeHS(mMainActivity.mGLoginFragment);
                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_SHORT).show();
                    break;
                case "无次账号":
                    Toast.makeText(mActivitySelf, entity, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onFinish() {
            Log.e("qqq", "onfs: ");
        }
    }
}
