package com.pss.booking.activity.indutries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.adapter.InduestriesAdapter;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.IndustriesModel;

import java.util.Locale;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentIndustriesEquipments extends Fragment implements DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    private ListView mListView;
    InduestriesAdapter adapter;
    MainActivity mActivity;
    private EditText searchView;
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
        mActivity.updateTitle(getString(R.string.nav_industries),true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_industires, container, false);
        mListView = (ListView) rootView.findViewById(R.id.Listview_industries);

        mNetworkManager.getIndustries("4");
        // Inflate the layout for this fragment
        searchView = (EditText) rootView.findViewById(R.id.SearchView);
        searchImplementation();
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

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int mDialogIndex) {

    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.INDUSTRIES_TYPE)) {
            IndustriesModel mSubCategoryModel = (IndustriesModel) responseObject;
            if (mSubCategoryModel.getSuccess() == 1) {

                adapter = new InduestriesAdapter(getActivity(), mSubCategoryModel.getSub_categories());
                mListView.setAdapter(adapter);
            }
        }
    }
}
