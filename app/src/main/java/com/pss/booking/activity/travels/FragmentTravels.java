package com.pss.booking.activity.travels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.pss.booking.R;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.adapter.TravelAdapter;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.HotelModel;

import java.util.Locale;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentTravels extends Fragment implements ServiceResponseListener {
    private ListView mListView;
    TravelAdapter adapter;
    MainActivity mActivity;
    OnFragmentChangeListener mOnFragmentChangeListener;
    NetworkManager mNetworkManager;
    private EditText searchView;
    private int category_ID =0;
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
        mActivity.updateTitle(getString(R.string.nav_travel),false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_travel, container, false);
        mListView = (ListView) rootView.findViewById(R.id.Listview_travel);
        mNetworkManager.getHotels(""+category_ID);
        mListView.setOnItemClickListener(OnItemClick);
        searchView = (EditText)rootView.findViewById(R.id.SearchView);
        searchImplementation();
        // Inflate the layout for this fragment
        return rootView;
    }


    private void searchImplementation() {
        searchView.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = searchView.getText().toString().toLowerCase(Locale.getDefault());
                Log.e("searc substring", "" + text);
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }


    AdapterView.OnItemClickListener OnItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HotelModel.PostsEntity mHotelDetails = (HotelModel.PostsEntity) parent.getAdapter().getItem(position);

            mOnFragmentChangeListener.onChangeListener(ActivityConstants.HOTELS_DETAILS_FRAGMENT, mHotelDetails);
        }
    };

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        HotelModel mSubCategoryModel = (HotelModel) responseObject;
        if (mSubCategoryModel.getSuccess() == 1) {
            for (int k = 0; k < mSubCategoryModel.getPosts().size(); k++) {
                Log.e("Title", "" + mSubCategoryModel.getPosts().get(k).getTitle());
            }

            // prepareArrayLits();
            adapter = new TravelAdapter(getActivity(), mSubCategoryModel.getPosts());
            mListView.setAdapter(adapter);
        }
    }
    public void getCategoryID(int category_ID){
        this.category_ID = category_ID;
    }

}
