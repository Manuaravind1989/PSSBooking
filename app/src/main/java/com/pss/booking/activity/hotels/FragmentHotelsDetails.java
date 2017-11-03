package com.pss.booking.activity.hotels;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.model.HotelModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdev3 on 10/13/16.
 */
public class FragmentHotelsDetails extends Fragment {
    View rootView;
    private Button mBookingButon, mChatbutton;
    OnFragmentChangeListener mOnFragmentChangeListener;
    MainActivity mActivity;
    UserPefer mUserPefer;
    private RecyclerView horizontal_recycler_view;
    private ArrayList<Integer> horizontalList;
    private HorizontalAdapter horizontalAdapter;

    private TextView mDescriptionLabel;
    private TextView mAmentiesLabel;
    private TextView mFeaturesLabel;
    private TextView mTitleName;
    private TextView mTimeingLabel;
    private TextView mDiscountLabel;
    private TextView mPriceLabel;
    private TextView mNewPriceLabel;
    private TextView mLocationLabel;
    HotelModel.PostsEntity mHotelDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mOnFragmentChangeListener = (MainActivity) getActivity();
        mUserPefer = new UserPefer(mActivity);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_hotels_details, container, false);
        initUI();
        setListners();
        setHorizontal();
        setValues();
        return rootView;
    }

    private void initUI() {
        mBookingButon = (Button) rootView.findViewById(R.id.btnBooking);
        mChatbutton = (Button) rootView.findViewById(R.id.buttonchat);
        mDescriptionLabel = (TextView) rootView.findViewById(R.id.HotelDescriptionLabel);
        mAmentiesLabel = (TextView) rootView.findViewById(R.id.HotelAmenitiesLabel);
        mFeaturesLabel = (TextView) rootView.findViewById(R.id.HotelFeatureLabel);
        mTitleName = (TextView) rootView.findViewById(R.id.HotelTitle);
        mTimeingLabel = (TextView) rootView.findViewById(R.id.HotelTiming);
        mDiscountLabel = (TextView) rootView.findViewById(R.id.HotelDiscount);
        mPriceLabel = (TextView) rootView.findViewById(R.id.HotelPrice);
        mLocationLabel = (TextView) rootView.findViewById(R.id.HotelLocation);
        mNewPriceLabel = (TextView) rootView.findViewById(R.id.HotelNewPrice);

    }

    private void setValues() {
        mDescriptionLabel.setText(mHotelDetails.getDescription());
        mAmentiesLabel.setText(mHotelDetails.getAmenities());
        mFeaturesLabel.setText(mHotelDetails.getFetures());
        mTitleName.setText(mHotelDetails.getTitle() + "-" + mHotelDetails.getSub_title());
        mDiscountLabel.setText(mHotelDetails.getDiscount() + "%");
        Log.e("Amount ====>", mHotelDetails.getDiscount());
        double discount = 0;
        if (!mHotelDetails.getDiscount().equals("")) {
            discount = Integer.valueOf(mHotelDetails.getDiscount());
        }

        if (discount > 0) {
            mDiscountLabel.setVisibility(View.VISIBLE);
            mPriceLabel.setPaintFlags(mPriceLabel.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            mDiscountLabel.setVisibility(View.GONE);
            mPriceLabel.setVisibility(View.GONE);
        }
        mPriceLabel.setText(mHotelDetails.getAmount());
        mTimeingLabel.setText(mHotelDetails.getTime_start() + "-" + mHotelDetails.getTime_end());
        mLocationLabel.setText(mHotelDetails.getLocation());
        double mAmount = 0.0D;
        double mDiscount = 0.0D;
        if(!mHotelDetails.getAmount().equals("")){
            mAmount = Double.valueOf(mHotelDetails.getAmount());
        }
        if(!mHotelDetails.getDiscount().equals("")){
            mDiscount = Double.valueOf(mHotelDetails.getDiscount());
        }
        mNewPriceLabel.setText("" + getPercentageValue(mAmount,mDiscount));
    }

    private double getPercentageValue(double oldAmount, double percent) {
        double newAmount = oldAmount - (oldAmount * percent / 100);
        return newAmount;
    }

    private void setListners() {
        mBookingButon.setOnClickListener(OnClick);
        mChatbutton.setOnClickListener(OnClick);
    }


    public void getHotelDetails(Object obj) {
        mHotelDetails = (HotelModel.PostsEntity) obj;
    }

    private void setHorizontal() {
        horizontal_recycler_view = (RecyclerView) rootView.findViewById(R.id.horizontal_recycler_view);

        horizontalList = new ArrayList<>();
        horizontalList.add(R.drawable.h1);
        horizontalList.add(R.drawable.h2);
        horizontalList.add(R.drawable.h3);
        horizontalList.add(R.drawable.h4);
        horizontalList.add(R.drawable.h5);
        horizontalList.add(R.drawable.h6);
        horizontalList.add(R.drawable.h7);
        horizontalList.add(R.drawable.h8);


        horizontalAdapter = new HorizontalAdapter(horizontalList);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

    }

    View.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnBooking) {
                if (mUserPefer.isLoggedIn()) {
//                    Intent mIn = new Intent(getActivity(), ProceedBookingActivity.class);
//                    startActivity(mIn);

                    mOnFragmentChangeListener.onChangeListener(ActivityConstants.PROCES_CONFIRM, mHotelDetails);

                } else {
                    mOnFragmentChangeListener.onChangeListener(ActivityConstants.LOGIN_FRAGMENT, mHotelDetails);
                }
            } else if (v.getId() == R.id.buttonchat) {

                if (mUserPefer.isLoggedIn()) {

                    mOnFragmentChangeListener.onChangeListener(ActivityConstants.CHAT_FRAGMENT, mHotelDetails.getUser_id());
                }else {
                    mOnFragmentChangeListener.onChangeListener(ActivityConstants.LOGIN_FRAGMENT, mHotelDetails);
                }
            }
        }
    };


    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {
        private List<Integer> horizontalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView mImageView;

            public MyViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.horizontalImage);
            }
        }

        public HorizontalAdapter(List<Integer> horizontalList) {
            this.horizontalList = horizontalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizontal_imageview, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.mImageView.setImageResource(horizontalList.get(position));
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   Toast.makeText(MainActivity.this, holder.txtView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }
}
