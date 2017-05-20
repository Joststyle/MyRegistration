package com.example.d1.zsan.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.d1.library.net.NetCallback;
import com.example.d1.library.net.NetJsonUtil;
import com.example.d1.library.util.UtilEdt;
import com.example.d1.zsan.R;
import com.example.d1.zsan.base.ZBaseFragment;
import com.example.d1.zsan.entity.Banner;
import com.example.d1.zsan.entity.HomeEntity;
import com.example.d1.zsan.ui.adapter.ItemLvHomeFragAdapter;
import com.example.d1.zsan.ui.adapter.ItemVPBannerAdapter;
import com.example.d1.zsan.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends ZBaseFragment {
    private EditText mEdtSearchFragHome;
    private Button mBtSearchFragHome;
    private ViewPager mVpBannerFragHome;
    private RadioGroup mRgHannerFragHome;
    private RecyclerView mRbFragHome;
    private NetJsonUtil mNetJsonUtil;
    private ListView mLvFragHome;
    private ItemVPBannerAdapter mItemVPBannerAdapter;
    private int[] banner =new int[]{R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};
    private List<Banner> mBannerList = new ArrayList<>();
    private ItemLvHomeFragAdapter mItemLvHomeFragAdapter;
    private Timer mTimer= new Timer() ;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mVpBannerFragHome.setCurrentItem(mVpBannerFragHome.getCurrentItem()+1);
                    break;
                default:
                    break;
            }
        }
    };
    private boolean mHasTaskCanceled;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        setTitleCenter("帮您首页");
        mEdtSearchFragHome = (EditText) findViewById(R.id.edt_search_frag_home);
        mBtSearchFragHome = (Button) findViewById(R.id.bt_search_frag_home);
        mVpBannerFragHome = (ViewPager) findViewById(R.id.vp_banner_frag_home);
        mRgHannerFragHome = (RadioGroup) findViewById(R.id.rg_hanner_frag_home);
        mLvFragHome = (ListView) findViewById(R.id.lv_frag_home);

        mBtSearchFragHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = UtilEdt.getEdtText(mEdtSearchFragHome);
                Toast.makeText(mActivitySelf, "mActivitySelf:" + mActivitySelf, Toast.LENGTH_SHORT).show();
            }
        });

        mTitleCenter.setFocusable(true);
        mTitleCenter.setFocusableInTouchMode(true);
        mTitleCenter.requestFocus();
        showvp();

    }

    private void showvp() {
        createbanner(banner);
        mItemVPBannerAdapter = new ItemVPBannerAdapter(mBannerList,mActivitySelf,mVpBannerFragHome);
        mVpBannerFragHome.setAdapter(mItemVPBannerAdapter);
        mItemVPBannerAdapter.setMiddle();
        RadioButton radioButton = (RadioButton) mRgHannerFragHome.getChildAt(0);
        radioButton.setChecked(true);
        mVpBannerFragHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RadioButton radioButton = (RadioButton) mRgHannerFragHome.getChildAt(position % mBannerList.size());
                radioButton.setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //设置点击时滑动
                if (ViewPager.SCROLL_STATE_IDLE==state) {

                    if (mHasTaskCanceled){
                        mTimer = new Timer();
                        mTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mHandler.sendEmptyMessage(1);
                            }
                        }, 2000, 2000);
                        mHasTaskCanceled=false ;

                    }

                }else if (ViewPager.SCROLL_STATE_DRAGGING==state){
                    mTimer.cancel();
                    mHasTaskCanceled=true;
                }
            }
        });
        mHasTaskCanceled=false;
        //轮播时间2
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        },2000,2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mTimer.cancel();
        mHasTaskCanceled=true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mHasTaskCanceled) {
            mTimer = new Timer();
            mHasTaskCanceled=false;
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(1);
                }
            }, 2000, 2000);
        }
    }

    @Override
    public void initDatas() {
        mNetJsonUtil = new NetJsonUtil(Constant.HOME_URL,new HomeNetCallback());
        mNetJsonUtil.execute();

    }
    public class HomeNetCallback extends NetCallback<HomeEntity>{

        @Override
        public void onError(Throwable e) {
            Log.e("qqq", "onError: ");
            Toast.makeText(mActivitySelf, "home-url-error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOK(HomeEntity entity) {

            Log.e("qqq", "oncg: ");
            System.out.println("成功");
            showLV(entity.getList());
        }

        @Override
        public void onFinish() {
            Log.e("qqq", "onfs: ");
        }
    }

    private void showLV(List<HomeEntity.ListBean> list) {
        mItemLvHomeFragAdapter = new ItemLvHomeFragAdapter(mActivitySelf,list);
        mLvFragHome.setAdapter(mItemLvHomeFragAdapter);
        System.out.println(list.size());
    }
    private List<Banner> createbanner(int[] ints){
        Banner banner1 = new Banner();
        banner1.setBan(ints[0]);
        Banner banner2 = new Banner();
        banner2.setBan(ints[1]);
        Banner banner3 = new Banner();
        banner3.setBan(ints[2]);
        Banner banner4 = new Banner();
        banner4.setBan(ints[3]);
        mBannerList.add(banner1);
        mBannerList.add(banner2);
        mBannerList.add(banner3);
        mBannerList.add(banner4);
        return mBannerList;
    }

}
