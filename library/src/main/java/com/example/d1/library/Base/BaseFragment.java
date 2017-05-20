package com.example.d1.library.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d1.library.R;


public abstract class BaseFragment extends Fragment {
    public String mTag;
    public FragmentManager mFragmentManager;
    public BaseActivity mActivitySelf;
    public BaseFragment mFragmentSelf;
    public LayoutInflater mLayoutInflater;
    public TextView mTitleCenter,mTitleRight,mTitleLeft;
    public View mContentView =null;
    public BaseFragment(){
        mTag = this.getClass().getName();  //this是实现它的子类对象
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentManager = this.getFragmentManager();
        mActivitySelf = (BaseActivity) this.getActivity();
        mFragmentSelf = this;
        mLayoutInflater = inflater;
        if(isNeedTitle()){
            mContentView = addTitle();
        }else{
            mContentView = mLayoutInflater.inflate(setRootView(),container,false);
        }
        initViews();
        initDatas();

        return mContentView;
    }
    public View findViewById(int resID){
        return mContentView.findViewById(resID);
    }

    private View addTitle() {

        LinearLayout linearLayout = new LinearLayout(mActivitySelf);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        View titleView = mLayoutInflater.inflate(BaseApp.mTitleLayoutID, linearLayout, false);
        View contentView = mLayoutInflater.inflate(setRootView(), linearLayout, false);


        mTitleCenter = (TextView) titleView.findViewById(R.id.title_center);
        mTitleRight = (TextView) titleView.findViewById(R.id.title_right);
        mTitleLeft = (TextView) titleView.findViewById(R.id.title_left);

        mTitleCenter.setVisibility(View.INVISIBLE);
        mTitleRight.setVisibility(View.INVISIBLE);
        mTitleLeft.setVisibility(View.INVISIBLE);

        linearLayout.addView(titleView);
        linearLayout.addView(contentView);

        return linearLayout;
    }

    public boolean isNeedTitle(){
        return true;
    }
    public abstract int setRootView();
    public abstract void initViews();
    public abstract void initDatas();
    public void setTitleCenter(String text) {

        setTitleCenter(text, null);

    }

    public void setTitleCenter(String text, View.OnClickListener onClickListener) {

        if (mTitleCenter != null) {
            mTitleCenter.setVisibility(View.VISIBLE);
            if (text != null) {
                mTitleCenter.setText(text);
            }
            if (onClickListener != null) {
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
    //快捷跳转到Activity
    public void goToActivity(Class des) {
        Intent intent = new Intent(mActivitySelf, des);
        startActivity(intent);

    }

    public void goToActivity(Class des, String key, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, des);
        intent.putExtra(key, bundle);
        startActivity(intent);

    }

    //快捷跳转到Service
    public void goToService(Class des) {
        Intent intent = new Intent(mActivitySelf, des);
        mActivitySelf.startService(intent);

    }

    public void goToService(Class des, String key, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, des);
        intent.putExtra(key, bundle);
        mActivitySelf.startService(intent);

    }

    //快捷跳转到Broad
    public void goToBroad(Class des) {
        Intent intent = new Intent(mActivitySelf, des);
        mActivitySelf.sendBroadcast(intent);

    }

    public void goToBroad(Class des, String key, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, des);
        intent.putExtra(key, bundle);
        mActivitySelf.sendBroadcast(intent);
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

    public void hideFrag(BaseFragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();

    }

    public void showFrag(BaseFragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();

    }
    //快捷操作fragment
}
