package com.pss.booking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pss.booking.R;
import com.pss.booking.Utils.PreferenceUtils;


/**
 * Created by Manu on 9/11/2016.
 */
public class SplashActivity extends AppCompatActivity {
    PreferenceUtils mPreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        mPreferenceUtils = new PreferenceUtils(this);
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                  //  if (mPreferenceUtils.IsSecondTime()) {
                        Intent intent = new Intent(SplashActivity.this, CategoryActivity.class);
                        startActivity(intent);
                    //} else {
                      //  Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                      //  startActivity(intent);
                   // }
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}

