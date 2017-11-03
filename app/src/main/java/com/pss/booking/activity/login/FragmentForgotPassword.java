package com.pss.booking.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.ValidationUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.ForgotModel;

/**
 * Created by mdev3 on 11/14/16.
 */
public class FragmentForgotPassword extends Fragment implements View.OnClickListener, DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    DialogUtils mDialogUtils;
    private EditText mEmailTextbox;
    private Button mSubmitButton;


    MainActivity mActivity;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;

    NetworkManager mNetworkManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        mNetworkManager = new NetworkManager(mActivity, this);
        mOnFragmentChangeListener = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_forgot_layout, container, false);
        mEmailTextbox = (EditText) rootView.findViewById(R.id.editTextEmail);
        mSubmitButton = (Button) rootView.findViewById(R.id.buttonSubmitButton);
        mSubmitButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.forgotpassword),false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSubmitButton) {
            if (ValidationUtils.validateEmail(mEmailTextbox.getText().toString().trim())) {
                mNetworkManager.forgotPassword(mEmailTextbox.getText().toString().trim());
            } else {
                mDialogUtils.okFunc(mActivity, "Please enter valid email", "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            }
        }
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int mDialogIndex) {

    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.FORGOT_PASSWORD)) {
            ForgotModel mForgotModel = (ForgotModel)responseObject;

                    mDialogUtils.okFunc(mActivity,mForgotModel.getMsg(), "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);

        }

    }
}
