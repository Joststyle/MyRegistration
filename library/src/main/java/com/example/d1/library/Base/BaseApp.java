package com.example.d1.library.Base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.xutils.x;

/**
 * Created by A on 2017/4/9.
 */

public abstract class BaseApp extends Application {
    public static Context mContextGlobal;   //全局的上下文对象
    public static int mTitleLayoutID;

    @Override
    public void onCreate() {
        super.onCreate();
        mContextGlobal = this;
        initXutils();
        initLogger();
        mTitleLayoutID = initTitleBar();
        initOthers();
    }


    private void initLogger(){
        Logger.init(this.getPackageName())                 // default PRETTYLOGGER or use just init()
                .methodCount(1)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(isDebug() ? LogLevel.FULL : LogLevel.NONE);  //根据isDebug判断打印
    }
    private void initXutils(){
        x.Ext.init(this);
        x.Ext.setDebug(isDebug()); // 是否输出debug日志, 开启debug会影响性能.
    }

    public abstract boolean isDebug();
    public abstract int initTitleBar();
    public abstract void initOthers();
}
