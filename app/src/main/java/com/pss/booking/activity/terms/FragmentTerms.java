package com.pss.booking.activity.terms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pss.booking.R;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.NotificationModel;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentTerms extends Fragment implements ServiceResponseListener {
    MainActivity mActivity;
    NetworkManager mNetworkManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

        mNetworkManager = new NetworkManager(mActivity, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_terms),false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_terms, container, false);
        mNetworkManager.ListNotification();

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {

        if(responseType.equals(RequestType.LIST_NOTIFICATION)){
            NotificationModel notificationModel = (NotificationModel)responseObject;
            for(int i= 0;i<  notificationModel.getMessages().size();i++){
                Log.e("TITLE == > "," "+notificationModel.getMessages().get(i).getMessage());
            }
        }

    }
}
