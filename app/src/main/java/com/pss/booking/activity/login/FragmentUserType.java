package com.pss.booking.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;

/**
 * Created by mdev3 on 11/14/16.
 */
public class FragmentUserType extends Fragment implements View.OnClickListener {
    private Button mPrivateButton, mBussinessButton, mOrganisationButton;
    MainActivity mActivity;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;
    DialogUtils mDialogUtils;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity)getActivity();
        mOnFragmentChangeListener = (MainActivity)getActivity();
        mDialogUtils = new DialogUtils();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mPrivateButton = (Button)rootView. findViewById(R.id.PrivateButton);
        mBussinessButton = (Button)rootView. findViewById(R.id.BussinessButton);
        mOrganisationButton = (Button)rootView. findViewById(R.id.OrganizationButton);
        mPrivateButton.setOnClickListener(this);
        mBussinessButton.setOnClickListener(this);
        mOrganisationButton.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        String userType ="";
        switch (v.getId()){
            case R.id.PrivateButton:{
                userType = "private";
                mOnFragmentChangeListener.onChangeListener(ActivityConstants.SIGN_UP_FRAGMENT,null);
            }
            break;
            case R.id.BussinessButton:{
                userType = "bussiness";
                mOnFragmentChangeListener.onChangeListener(ActivityConstants.BUSSINESS_REGISTER,userType);
            }
            break;
            case R.id.OrganizationButton:{
                userType = "organization";
                mOnFragmentChangeListener.onChangeListener(ActivityConstants.BUSSINESS_REGISTER,userType);
            }
            break;
        }

    }
}
