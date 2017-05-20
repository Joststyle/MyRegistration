package com.example.d1.zsan.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;

public class LoginActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public int setRootView() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {

    }
}
