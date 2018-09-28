package com.binzee.fencing.fencingrecord.base;

import android.app.Application;

import com.tencent.bugly.Bugly;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

public class FoxApplication extends LitePalApplication {

    public static final boolean IS_DEBUG = true;    //Debug状态

    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(this, "23a7635ace", IS_DEBUG);
        LitePal.initialize(this);
    }
}
