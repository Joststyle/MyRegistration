package com.example.d1.zsan.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseActivity;
import com.example.d1.zsan.base.ZBaseFragment;
import com.example.d1.zsan.ui.fragment.AboutFragment;
import com.example.d1.zsan.ui.fragment.DLoginFragment;
import com.example.d1.zsan.ui.fragment.GLoginFragment;
import com.example.d1.zsan.ui.fragment.HomeFragment;
import com.example.d1.zsan.ui.fragment.OneselfFragment;
import com.example.d1.zsan.ui.fragment.RecommendFragment;
import com.example.d1.zsan.ui.fragment.SearchFragment;
import com.example.d1.zsan.ui.fragment.ULoginFragment;
import com.example.d1.zsan.ui.fragment.WelcomeFragment;

public class MainActivity extends ZBaseActivity implements RadioGroup.OnCheckedChangeListener {
    private WelcomeFragment mWelcomeFragment=new WelcomeFragment();
    private HomeFragment mHomeFragment = new HomeFragment();
    private RecommendFragment mRecommendFragment =  new RecommendFragment();
    private SearchFragment mSearchFragment = new SearchFragment();
    public OneselfFragment mSelfFragment = new OneselfFragment();
    private AboutFragment mAboutFragment = new AboutFragment();
    public DLoginFragment mDLoginFragment = new DLoginFragment();
    public ULoginFragment mULoginFragment = new ULoginFragment();
    public GLoginFragment mGLoginFragment = new GLoginFragment();
    private ZBaseFragment[] mZBaseFragmentsChange= {mHomeFragment,mRecommendFragment,mSearchFragment,mSelfFragment,mAboutFragment,mDLoginFragment,mULoginFragment,mGLoginFragment};

    private FrameLayout mLayoutFragWelcome;
    private FrameLayout mLayoutChangeFragMainActivity;
    private RadioGroup mRgNaviMainActivity;
    private RadioButton mRbHomeMainActivity;
    private RadioButton mRbRecommedMainActivity;
    private RadioButton mRbSearchMainActivity;
    private RadioButton mRbSelfMainActivity;
    private RadioButton mRbAboutMainActivity;

    public static int isshow =0;



    private static final int REMOVE_WOLCOME =110;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REMOVE_WOLCOME:
                    removeFrag(mWelcomeFragment);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);   //撤销全屏显示
                    mLayoutFragWelcome.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public int setRootView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mLayoutChangeFragMainActivity = (FrameLayout) findViewById(R.id.layout_change_frag_main_activity);
        mRgNaviMainActivity = (RadioGroup) findViewById(R.id.rg_navi_main_activity);
        mRbHomeMainActivity = (RadioButton) findViewById(R.id.rb_home_main_activity);
        mRbRecommedMainActivity = (RadioButton) findViewById(R.id.rb_recommed_main_activity);
        mRbSearchMainActivity = (RadioButton) findViewById(R.id.rb_search_main_activity);
        mRbSelfMainActivity = (RadioButton) findViewById(R.id.rb_self_main_activity);
        mRbAboutMainActivity = (RadioButton) findViewById(R.id.rb_about_main_activity);
        mLayoutFragWelcome = (FrameLayout) findViewById(R.id.layout_frag_welcome);

        mRgNaviMainActivity.setOnCheckedChangeListener(this);
        mRbHomeMainActivity.setChecked(true);

        addFrag(R.id.layout_frag_welcome,mWelcomeFragment);
        mHandler.sendEmptyMessageDelayed(REMOVE_WOLCOME,3000);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        ZBaseFragment zBaseFragmentNow = null;
        switch (checkedId) {
            case R.id.rb_home_main_activity:
                zBaseFragmentNow = mHomeFragment;
                break;
            case R.id.rb_recommed_main_activity:
                zBaseFragmentNow = mRecommendFragment;
                break;
            case R.id.rb_search_main_activity:
                zBaseFragmentNow= mSearchFragment;
                break;
            case R.id.rb_self_main_activity:
                System.out.println(ULoginFragment.isSubscribe);
                switch (isshow){
                    case 0:
                        zBaseFragmentNow=mSelfFragment;
                        break;
                    case 1:
                        zBaseFragmentNow=mULoginFragment;
                        break;
                    case 2:
                        zBaseFragmentNow=mDLoginFragment;
                        break;
                    case 3:
                        zBaseFragmentNow=mGLoginFragment;
                        break;
                }

                break;
            case R.id.rb_about_main_activity:
                zBaseFragmentNow=mAboutFragment;
                break;
        }
        changeHS(zBaseFragmentNow);
    }

    public void changeHS(ZBaseFragment zBaseFragmentNow) {
        if(!zBaseFragmentNow.isAdded()){
            addFrag(R.id.layout_change_frag_main_activity,zBaseFragmentNow);
        }
        showFrag(zBaseFragmentNow);
        for (ZBaseFragment zBaseFragment : mZBaseFragmentsChange){
            if(zBaseFragment!=zBaseFragmentNow){
                hideFrag(zBaseFragment);
            }
        }
    }
}
