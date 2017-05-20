package com.example.d1.library.net;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by A on 2017/4/10.
 */

public class NetJsonUtil implements Callback.CommonCallback<String> {
    private String mUrl;
    private Type mType;
    private boolean mIsPost;
    private NetCallback mNetCallback;
    public RequestParams mRequestParams;
    public NetJsonUtil(String url, boolean isPost, NetCallback netCallback) {
        mUrl = url;
        mIsPost = isPost;
        mNetCallback = netCallback;

        //根据泛型获取Type类型，从而省略传入解析类型参数
        if (netCallback!=null){
            ParameterizedType parameterizedType = (ParameterizedType) netCallback
                    .getClass().getGenericSuperclass();
            mType = parameterizedType.getActualTypeArguments()[0];
        }

        mRequestParams=new RequestParams(mUrl);
    }

    public NetJsonUtil(String url, NetCallback netCallback) {

        this(url,false,netCallback);
    }


    public void execute(){

        if (mIsPost){
            x.http().post(mRequestParams,this);
        }else {
            x.http().get(mRequestParams,this);
        }
    }

    @Override
    public void onSuccess(String result) {

        Gson gson=new Gson();
        mNetCallback.onOK(gson.fromJson(result,mType));
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        mNetCallback.onError(ex);
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {
        mNetCallback.onFinish();
    }
}
