package com.pss.booking.Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by mdev3 on 10/17/16.
 */
public class App extends Application {
    private static App mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance = this;

        this.setAppContext(getApplicationContext());

    }

    public static App getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }


}
