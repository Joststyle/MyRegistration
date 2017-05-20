package com.example.d1.zsan.base;

import com.example.d1.library.Base.BaseApp;
import com.example.d1.zsan.R;

/**
 * Created by A on 2017/4/10.
 */

public class ZBaseApp extends BaseApp {
    @Override
    public boolean isDebug() {
        return true;
    }

    @Override
    public int initTitleBar() {
        return R.layout.title_bar;
    }

    @Override
    public void initOthers() {

    }
}
