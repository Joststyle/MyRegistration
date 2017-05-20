package com.example.d1.zsan.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;

public class DetailedActivity extends ZBaseActivity {
    private TextView mTvDataile;
    private TextView mTvInformationDeta;

    @Override
    public int setRootView() {
        return R.layout.activity_detailed;
    }

    @Override
    public void initViews() {
        setTitleCenter("详细信息");
        mTvDataile = (TextView) findViewById(R.id.tv_content_dataile);
        mTvInformationDeta = (TextView) findViewById(R.id.tv_information_deta);

    }

    @Override
    public void initDatas() {
        Intent intent = this.getIntent();
        String content =intent.getStringExtra("content");
        String information= intent.getStringExtra("information");
        mTvInformationDeta.setText(information);
        mTvDataile.setText(content);
    }
}
