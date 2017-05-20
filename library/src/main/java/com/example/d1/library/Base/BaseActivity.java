package com.example.d1.library.Base;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d1.library.R;
import com.example.d1.library.control.ActivityControl;

public abstract class BaseActivity extends FragmentActivity {
    public FragmentManager mFragmentManager;
    public BaseActivity mActivitySelf;
    public LayoutInflater mLayoutInflater;
    public TextView mTitleCenter,mTitleRight,mTitleLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityControl.add(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mFragmentManager = this.getSupportFragmentManager();
        mLayoutInflater = this.getLayoutInflater();
        mActivitySelf = this;
        if (isNeedTitle()) {
            addTitle();
        }else {
            setContentView(setRootView());
        }
        initViews();
        initDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControl.remove(this);
    }

    private void addTitle(){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        View titleView = mLayoutInflater.inflate(BaseApp.mTitleLayoutID, linearLayout, false);
        View contentView = mLayoutInflater.inflate(setRootView(), linearLayout, false);


        mTitleCenter = (TextView) titleView.findViewById(R.id.title_center);
        mTitleRight = (TextView) titleView.findViewById(R.id.title_right);
        mTitleLeft = (TextView) titleView.findViewById(R.id.title_left);
        //s设置成看不见的  在set的时候 再改成看的见
        mTitleCenter.setVisibility(View.INVISIBLE);
        mTitleRight.setVisibility(View.INVISIBLE);
        mTitleLeft.setVisibility(View.INVISIBLE);

        linearLayout.addView(titleView);
        linearLayout.addView(contentView);

        setContentView(linearLayout);
    }

    public boolean isNeedTitle() {
        return true;
    }
    public void setTitleCenter(String text){
        setTitleCenter(text,null);
    }

    public void setTitleCenter(String text,View.OnClickListener onClickListener)  {
        if(mTitleCenter!=null){
            mTitleCenter.setVisibility(View.VISIBLE);
            if(text!=null){
                mTitleCenter.setText(text);
            }
            if(onClickListener!=null){
                mTitleCenter.setOnClickListener(onClickListener);
            }
        }
    }
    public void setTitleRight(String text) {

        setTitleRight(text, null);

    }

    public void setTitleRight(String text, View.OnClickListener onClickListener) {

        if (mTitleRight != null) {
            mTitleRight.setVisibility(View.VISIBLE);
            if (text != null) {
                mTitleRight.setText(text);
            }
            if (onClickListener != null) {
                mTitleRight.setOnClickListener(onClickListener);
            }
        }

    }
    public void setTitleLeft(String text) {

        setTitleLeft(text, null);

    }

    public void setTitleLeft(String text, View.OnClickListener onClickListener) {

        if (mTitleLeft != null) {
            mTitleLeft.setVisibility(View.VISIBLE);
            if (text != null) {
                mTitleLeft.setText(text);
            }
            if (onClickListener != null) {
                mTitleLeft.setOnClickListener(onClickListener);
            }
        }

    }
    //------------标题栏操作
    public abstract int setRootView();

    public abstract void initViews();

    public abstract void initDatas();
    //快捷跳转到Activity
    public void goToActivity(Class des) {
        Intent intent = new Intent(this, des);
        startActivity(intent);

    }

    public void goToActivity(Class des, String key, Bundle bundle) {
        Intent intent = new Intent(this, des);
        intent.putExtra(key, bundle);
        startActivity(intent);

    }

    //快捷跳转到Service
    public void goToService(Class des) {
        Intent intent = new Intent(this, des);
        startService(intent);

    }

    public void goToService(Class des, String key, Bundle bundle) {
        Intent intent = new Intent(this, des);
        intent.putExtra(key, bundle);
        startService(intent);

    }

    //快捷跳转到Broad
    public void goToBroad(Class des) {
        Intent intent = new Intent(this, des);
        sendBroadcast(intent);

    }

    public void goToBroad(Class des, String key, Bundle bundle) {
        Intent intent = new Intent(this, des);
        intent.putExtra(key, bundle);
        sendBroadcast(intent);
    }

    //快捷操作fragment
    public void addFrag(int resID, BaseFragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(resID, fragment, fragment.mTag);
        transaction.commit();

    }

    public void removeFrag(BaseFragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();

    }

    public void replaceFrag(int resID, BaseFragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(resID, fragment);
        transaction.commit();

    }
    //隐藏
    public void hideFrag(BaseFragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();

    }
    //显示
    public void showFrag(BaseFragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();

    }
    //快捷操作fragment
}
