package com.pss.booking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pss.booking.R;
import com.pss.booking.Utils.PreferenceUtils;
import com.pss.booking.adapter.IntroPagerAdapter;


/**
 * Created by Manu on 9/11/2016.
 */
public class IntroActivity extends AppCompatActivity {
    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] content;
    ImageView mIndicatorOne, mIndicatorTwo, mIndicatorThree, mIndicatorFour, mIndicatorFive;
    PreferenceUtils mPreferenceUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_layout);
        content = new String[]{"1", "2", "3", "4", "5"};
        mPreferenceUtils = new PreferenceUtils(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicatorOne = (ImageView) findViewById(R.id.indicatorone);
        mIndicatorTwo = (ImageView) findViewById(R.id.indicatortwo);
        mIndicatorThree = (ImageView) findViewById(R.id.indicatorthree);
        mIndicatorFour = (ImageView) findViewById(R.id.indicatorfour);
        mIndicatorFive = (ImageView) findViewById(R.id.indicatorfive);

        adapter = new IntroPagerAdapter(IntroActivity.this, content);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Button mSkipButton = (Button) findViewById(R.id.SkipButton);
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreferenceUtils.setSkipFirst(true);
                Intent mSkipIntent = new Intent(IntroActivity.this, CategoryActivity.class);
                startActivity(mSkipIntent);
                finish();

            }
        });
    }

    private void indicators(int position) {
        switch (position) {
            case 0: {
                mIndicatorOne.setImageResource(R.drawable.slide_icon_active);
                mIndicatorTwo.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorThree.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFour.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFive.setImageResource(R.drawable.slide_icon_inctive);
            }
            break;
            case 1: {
                mIndicatorOne.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorTwo.setImageResource(R.drawable.slide_icon_active);
                mIndicatorThree.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFour.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFive.setImageResource(R.drawable.slide_icon_inctive);
            }
            break;
            case 2: {
                mIndicatorOne.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorTwo.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorThree.setImageResource(R.drawable.slide_icon_active);
                mIndicatorFour.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFive.setImageResource(R.drawable.slide_icon_inctive);
            }
            break;
            case 3: {
                mIndicatorOne.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorTwo.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorThree.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFour.setImageResource(R.drawable.slide_icon_active);
                mIndicatorFive.setImageResource(R.drawable.slide_icon_inctive);
            }
            break;
            case 4: {
                mIndicatorOne.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorTwo.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorThree.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFour.setImageResource(R.drawable.slide_icon_inctive);
                mIndicatorFive.setImageResource(R.drawable.slide_icon_active);
            }
            break;

        }
    }
}
