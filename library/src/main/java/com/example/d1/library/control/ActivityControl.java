package com.example.d1.library.control;


import com.example.d1.library.Base.BaseActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 * 统一管理Activity 
 */
public class ActivityControl {
    private static List<BaseActivity> mActivities=new ArrayList<>();
    
    public static void add(BaseActivity baseActivity){
        mActivities.add(baseActivity);
        
    }
    
    public static void remove(BaseActivity baseActivity){
        mActivities.remove(baseActivity);
        
    }
    
    public static BaseActivity getActivity(Class activity){
        for (BaseActivity baseActivity : mActivities) {
            if (baseActivity.getClass()==activity) {
                return baseActivity;
            }
        }
        return null;
    }
    
    public static void killAll(){
        Iterator<BaseActivity> iterator=mActivities.iterator();
        while (iterator.hasNext()){
            iterator.next().finish();
        }
    
    }
}
