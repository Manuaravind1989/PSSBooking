package com.pss.booking.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Manu on 9/9/2016.
 */
public class PreferenceUtils {
    private static final String APPINFO = "Bookibg";
    private static final String FIRST_TIME = "first_time";
    Context mContext;
    private SharedPreferences mSharedPreference;
    SharedPreferences.Editor editor;

    public PreferenceUtils(Context mContext) {
        this.mContext = mContext;
        mSharedPreference = mContext.getSharedPreferences(APPINFO, Context.MODE_PRIVATE);
        editor = mSharedPreference.edit();

    }

    public void setSkipFirst(boolean isFistimr){
        editor.putBoolean(FIRST_TIME, isFistimr);
        editor.commit();
    }

    public boolean IsSecondTime(){
        return mSharedPreference.getBoolean(FIRST_TIME, false);
    }
}
