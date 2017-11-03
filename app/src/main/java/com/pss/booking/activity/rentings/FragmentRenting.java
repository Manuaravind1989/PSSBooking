package com.pss.booking.activity.rentings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.adapter.RentingAdapter;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.ServiceResponseListener;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentRenting extends Fragment implements View.OnClickListener, DialogUtils.OnDialogSelectedListener,ServiceResponseListener {
    String[] itemList = {"Train","Truck","Jeep","Flowers","Spray","Train","Truck","Jeep","Flowers","Spray","Train","Truck","Jeep","Flowers","Spray","Train","Truck","Jeep","Flowers","Spray"
            ,"Train","Truck","Jeep","Flowers","Spray","Train","Truck","Jeep","Flowers","Spray","Train","Truck","Jeep","Flowers","Spray"};
    private ListView mListView ;
    RentingAdapter adapter;
    MainActivity mActivity;
    private TextView dropdownButton;
    DialogUtils mDialogUtils;
    NetworkManager mNetworkManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        mNetworkManager = new NetworkManager(mActivity, this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_renting),false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_renting, container, false);
        mListView = (ListView)rootView.findViewById(R.id.Listview_renting);
        mNetworkManager.getRenting("3");
        adapter = new RentingAdapter(getActivity(), itemList);
        mListView.setAdapter(adapter);
        dropdownButton = (TextView)rootView.findViewById(R.id.dropdownButton);
        dropdownButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dropdownButton){
            String[] arr = {"item 1","Item 2", "Item 3","Item 4","Item 5","Item 6","Item 7","Item 8","Item 9","Item 10"};
            mDialogUtils.listFunc(mActivity,"Select a category",arr,FragmentRenting.this, DialogConstants.DIALOG_BUTTON_LIST);
        }
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj,int mDialogIndex) {

    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {

    }
}

