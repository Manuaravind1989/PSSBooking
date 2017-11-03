package com.pss.booking.activity.services;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pss.booking.R;
import com.pss.booking.activity.MainActivity;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentServices extends Fragment {


    MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_services), false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_services, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }
}
