package com.pss.booking.activity.hire;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pss.booking.R;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.model.HotelModel;

import java.util.ArrayList;

/**
 * Created by mdev3 on 11/14/16.
 */
public class FragmentHire extends Fragment {
    MainActivity mActivity;
    OnFragmentChangeListener mOnFragmentChangeListener;
    private View parentView;
    private ArrayList<HotelModel> itemList;
    private ListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mOnFragmentChangeListener = (MainActivity)getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_hire),false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_hire, container, false);
        mListView = (ListView) parentView.findViewById(R.id.hire);
//
//      //  prepareArrayLits();
//        HireAdapter  mHotelAdapter = new HireAdapter(getActivity(), itemList);
//        mListView.setAdapter(mHotelAdapter);
        return parentView;
    }



}
