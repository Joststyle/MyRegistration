package com.example.d1.library.net;

/**
 * Created by A on 2017/4/10.
 */

public abstract class NetCallback<T> {

    public abstract void onError(Throwable e);
    public  abstract void onOK(T entity);
    public  abstract void onFinish();
}

