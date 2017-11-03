package com.pss.booking.activity.hotels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.pss.booking.R;
import com.pss.booking.Utils.DateUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.adapter.HotelAdapter;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.customview.ArrowDirection;
import com.pss.booking.customview.BubbleLayout;
import com.pss.booking.customview.BubblePopupHelper;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.HotelModel;

import org.threeten.bp.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import solar.blaz.date.week.WeekDatePicker;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentHotels extends Fragment implements ServiceResponseListener {

    private View rootView;
    BubbleLayout bubbleLayout;
    HotelAdapter mHotelAdapter;
    MainActivity mActivity;
    NetworkManager mNetworkManager;
    OnFragmentChangeListener mOnFragmentChangeListener;
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HotelModel.PostsEntity mHotelDetails = (HotelModel.PostsEntity) parent.getAdapter().getItem(position);
            mOnFragmentChangeListener.onChangeListener(ActivityConstants.HOTELS_DETAILS_FRAGMENT, mHotelDetails);
        }
    };
    // Declare Variables
    private WeekDatePicker datePicker;
    private Button clickButton;
    private PopupWindow popupWindow;
    View.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ChangeButton) {
                int[] location = new int[2];
                v.getLocationInWindow(location);
                bubbleLayout.setArrowDirection(ArrowDirection.TOP);
                popupWindow.showAtLocation(v, Gravity.TOP | Gravity.LEFT, 46, v.getHeight() + location[1]);
            }
        }
    };
    private TextView mMonthName;
    // private Button mTodayButton, mTomorrowButton, mChangeButton;
    private ListView mListView;
    private ArrayList<HotelModel> itemList;
    private int category_ID = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mOnFragmentChangeListener = (MainActivity) getActivity();
        mNetworkManager = new NetworkManager(mActivity, this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (category_ID == 1) {
            mActivity.updateTitle(getString(R.string.nav_hotles),true);
            Log.e("kkk=====> ","hotel");
        } else if (category_ID == 2) {
            mActivity.updateTitle(getString(R.string.nav_travel),true);
            Log.e("kkk=====> ","travel");
        } else if (category_ID == 3) {
            mActivity.updateTitle(getString(R.string.nav_renting),true);
            Log.e("kkk=====> ","renting");
        } else if (category_ID == 4) {
            mActivity.updateTitle(getString(R.string.nav_industries),true);
            Log.e("kkk=====> ","industries");
        } else if (category_ID == 5) {
            mActivity.updateTitle(getString(R.string.nav_hire),true);
            Log.e("kkk=====> ","hire");
        }
        mActivity.updateFilter(true);
    }

    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */

//    // Add one item into the Array List
//    public void AddObjectToList(int image, String title) {
//        HotelModel bean = new HotelModel();
//
//        bean.setImage(image);
//        bean.setTitle(title);
//        itemList.add(bean);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView != null) {
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_hotels, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listView);

//        mTodayButton = (Button) rootView.findViewById(R.id.TodayButton);
//        mTomorrowButton = (Button) rootView.findViewById(R.id.TomorrowButton);
//        mChangeButton = (Button) rootView.findViewById(R.id.ChangeButton);
//        mChangeButton.setOnClickListener(OnClick);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String outputDate = simpleDateFormat.format(calendar.getTime());
        Log.e("First Day == > ", outputDate);
        mNetworkManager.getHotels("" + category_ID);

        bubbleLayout = (BubbleLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_sample_popup, null);
        popupWindow = BubblePopupHelper.create(getActivity(), bubbleLayout);

        datePicker = (WeekDatePicker) bubbleLayout.findViewById(R.id.date_picker);
        mMonthName = (TextView) bubbleLayout.findViewById(R.id.dateHeader);
        datePicker.setDateIndicator(LocalDate.now().plusDays(0), true);
        datePicker.setLimits(LocalDate.now().minusWeeks(0), null);
        datePicker.setOnDateSelectedListener(new WeekDatePicker.OnDateSelected() {
            @Override
            public void onDateSelected(LocalDate date) {
                Toast.makeText(getActivity(), "" + date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        datePicker.setOnWeekChangedListener(new WeekDatePicker.OnWeekChanged() {
            @Override
            public void onItemSelected(LocalDate firstDay) {


                Log.e("OnChanged ======>", firstDay.toString());
                Log.e("firstday ======>", DateUtils.onConvert(firstDay.toString()));
                mMonthName.setText(DateUtils.onConvert(firstDay.toString()));
                //DateUtils.onConvert(firstDay.toString());
            }
        });
        mListView.setOnItemClickListener(onItemClickListener);

        return rootView;
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.HOTEL_TYPE)) {
            HotelModel mSubCategoryModel = (HotelModel) responseObject;
            if (mSubCategoryModel.getSuccess() == 1) {
                for (int k = 0; k < mSubCategoryModel.getPosts().size(); k++) {
                    Log.e("Title", "" + mSubCategoryModel.getPosts().get(k).getSub_category());
                }

                // prepareArrayLits();
                mHotelAdapter = new HotelAdapter(getActivity(), mSubCategoryModel.getPosts());
                mListView.setAdapter(mHotelAdapter);
            }
        }
    }

    public void getCategoryID(int category_ID) {
        this.category_ID = category_ID;
    }
}
