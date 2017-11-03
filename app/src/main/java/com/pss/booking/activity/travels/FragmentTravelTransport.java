package com.pss.booking.activity.travels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.activity.LoginActivity;
import com.pss.booking.activity.MainActivity;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentTravelTransport extends Fragment {
    MainActivity mActivity;
    private LinearLayout mCarLayout, mBikeLayout, mSegwayLayout;
    private ImageView mImageCheckedCar, mImageCheckedBike, mImageCheckedSegway;
    private LinearLayout mHoursLayout, mHalfDayLayout, mFullDayLayout;
    private TextView mHourTextView, mHalfTextView, mFullTextView;
    private TextView mHourView, mHalfView, mFullView;
    private Button mBookButton;

    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_travel),false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_travel_details, container, false);
        // Inflate the layout for this fragment
        initializeUI();
        setListeners();
        selection(R.id.checkedLayoutCar);
        selectionCurrency(R.id.selectCurrencyHourLayout);
        return rootView;
    }

    private void initializeUI() {
        mCarLayout = (LinearLayout) rootView.findViewById(R.id.checkedLayoutCar);
        mBikeLayout = (LinearLayout) rootView.findViewById(R.id.checkedLayoutBikes);
        mSegwayLayout = (LinearLayout) rootView.findViewById(R.id.checkedLayoutSegway);
        mImageCheckedCar = (ImageView) rootView.findViewById(R.id.checkImageViewCar);
        mImageCheckedBike = (ImageView) rootView.findViewById(R.id.checkImageViewBike);
        mImageCheckedSegway = (ImageView) rootView.findViewById(R.id.checkImageViewSegway);
        mBookButton = (Button) rootView.findViewById(R.id.btnBooking);

        mHoursLayout = (LinearLayout) rootView.findViewById(R.id.selectCurrencyHourLayout);
        mHalfDayLayout = (LinearLayout) rootView.findViewById(R.id.selectCurrencyHalfLayout);
        mFullDayLayout = (LinearLayout) rootView.findViewById(R.id.selectCurrencyFullLayout);
        mHourTextView = (TextView) rootView.findViewById(R.id.textCurrencyHours);
        mHalfTextView = (TextView) rootView.findViewById(R.id.textCurrencyHalf);
        mFullTextView = (TextView) rootView.findViewById(R.id.textCurrencyFull);
        mHourView = (TextView) rootView.findViewById(R.id.textHours);
        mHalfView = (TextView) rootView.findViewById(R.id.textHalf);
        mFullView = (TextView) rootView.findViewById(R.id.textFull);
    }

    private void setListeners() {
        mCarLayout.setOnClickListener(onCLicklistner);
        mBikeLayout.setOnClickListener(onCLicklistner);
        mSegwayLayout.setOnClickListener(onCLicklistner);

        mHoursLayout.setOnClickListener(onCLicklistener);
        mHalfDayLayout.setOnClickListener(onCLicklistener);
        mFullDayLayout.setOnClickListener(onCLicklistener);
        mBookButton.setOnClickListener(onCLicklistener);
    }

    View.OnClickListener onCLicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectionCurrency(v.getId());
        }
    };

    View.OnClickListener onCLicklistner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selection(v.getId());
        }
    };

    private void selection(int index) {
        switch (index) {
            case R.id.checkedLayoutCar: {
                mImageCheckedCar.setBackgroundResource(R.drawable.orange_bg_circle);
                mImageCheckedBike.setBackgroundResource(R.drawable.dark_bg_circle);
                mImageCheckedSegway.setBackgroundResource(R.drawable.dark_bg_circle);
            }
            break;
            case R.id.checkedLayoutBikes: {
                mImageCheckedCar.setBackgroundResource(R.drawable.dark_bg_circle);
                mImageCheckedBike.setBackgroundResource(R.drawable.orange_bg_circle);
                mImageCheckedSegway.setBackgroundResource(R.drawable.dark_bg_circle);
            }
            break;
            case R.id.checkedLayoutSegway: {
                mImageCheckedCar.setBackgroundResource(R.drawable.dark_bg_circle);
                mImageCheckedBike.setBackgroundResource(R.drawable.dark_bg_circle);
                mImageCheckedSegway.setBackgroundResource(R.drawable.orange_bg_circle);
            }
            break;

        }
    }

    private void selectionCurrency(int index) {
        switch (index) {
            case R.id.selectCurrencyHourLayout: {
                mHourTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mHalfTextView.setTextColor(getResources().getColor(android.R.color.white));
                mFullTextView.setTextColor(getResources().getColor(android.R.color.white));
                mHourView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mHalfView.setTextColor(getResources().getColor(android.R.color.white));
                mFullView.setTextColor(getResources().getColor(android.R.color.white));
            }
            break;
            case R.id.selectCurrencyHalfLayout: {
                mHourTextView.setTextColor(getResources().getColor(android.R.color.white));
                mHalfTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mFullTextView.setTextColor(getResources().getColor(android.R.color.white));
                mHourView.setTextColor(getResources().getColor(android.R.color.white));
                mHalfView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mFullView.setTextColor(getResources().getColor(android.R.color.white));
            }
            break;
            case R.id.selectCurrencyFullLayout: {
                mHourTextView.setTextColor(getResources().getColor(android.R.color.white));
                mHalfTextView.setTextColor(getResources().getColor(android.R.color.white));
                mFullTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                mHourView.setTextColor(getResources().getColor(android.R.color.white));
                mHalfView.setTextColor(getResources().getColor(android.R.color.white));
                mFullView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            break;
            case R.id.btnBooking: {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
            break;

        }
    }
}



