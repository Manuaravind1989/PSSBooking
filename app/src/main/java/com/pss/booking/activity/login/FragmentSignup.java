package com.pss.booking.activity.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.CommonUtils;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.StringUtils;
import com.pss.booking.Utils.ValidationUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.DialCodeModel;
import com.pss.booking.model.ProfileModel;
import com.pss.booking.model.RegisterModel;

import java.io.InputStream;

/**
 * Created by mdev3 on 11/14/16.
 */
public class FragmentSignup extends Fragment implements DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    DialogUtils mDialogUtils;
    private EditText mFirstNameTextbox;
    private EditText mLastNameTextbox;
    private EditText mEmailTextbox;
    private EditText mPasswordTextbox;
    private EditText mPhoneNumberTextbox;
    private EditText mLocationTextbox;
    private TextView mDialCode;
    private Button mSubmitButton;
    NetworkManager mNetworkManager;
    private String mPhoneNuber = "+91";
    MainActivity mActivity;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;
    ProfileModel mProfileModel;

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
        rootView = inflater.inflate(R.layout.activity_signup_layout, container, false);
        initialUI();
        setValue();
        return rootView;
    }

    private void initialUI() {
        mFirstNameTextbox = (EditText) rootView.findViewById(R.id.firstnameTextbox);
        mLastNameTextbox = (EditText) rootView.findViewById(R.id.lastnameTextbox);
        mEmailTextbox = (EditText) rootView.findViewById(R.id.emailTextbox);
        mPasswordTextbox = (EditText) rootView.findViewById(R.id.paswordTextbox);
        mPhoneNumberTextbox = (EditText) rootView.findViewById(R.id.phoneTextbox);
        mLocationTextbox = (EditText) rootView.findViewById(R.id.LocationTextbox);
        mSubmitButton = (Button) rootView.findViewById(R.id.SubmitButton);
        mDialCode = (TextView) rootView.findViewById(R.id.dialoCode);

        mDialCode.setOnClickListener(onClick);
        mSubmitButton.setOnClickListener(onClick);

    }
private void setValue(){
    if(mProfileModel !=null){
        mFirstNameTextbox.setText(mProfileModel.getFirstName());
        mLastNameTextbox.setText(mProfileModel.getLastName());
        mEmailTextbox.setText(mProfileModel.getEmail());

    }
}
    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.register),false);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.SubmitButton) {
                if (isValidated()) {
                    mNetworkManager.setRegistration(mFirstNameTextbox.getText().toString().trim(), mLastNameTextbox.getText().toString().trim(),
                            mEmailTextbox.getText().toString().trim(), mPasswordTextbox.getText().toString().trim(), mPhoneNuber + mPhoneNumberTextbox.getText().toString().trim(),
                            mLocationTextbox.getText().toString().trim());

                }
            } else if (v.getId() == R.id.dialoCode) {
                mDialogUtils.dialogDialCode(mActivity, CommonUtils.getCallingCodes(), FragmentSignup.this, DialogConstants.DIALOG_DIAL_LIST);
            }
        }
    };

    private boolean isValidated() {

        if (StringUtils.isBlank(mFirstNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter FirstName", "PSS", FragmentSignup.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mLastNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter LastName", "PSS", FragmentSignup.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter EmailID", "PSS", FragmentSignup.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (!ValidationUtils.validateEmail(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter valid EmailID", "PSS", FragmentSignup.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mPasswordTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Password", "PSS", FragmentSignup.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mPhoneNumberTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Phone number", "PSS", FragmentSignup.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mLocationTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Location", "PSS", FragmentSignup.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        return true;
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int mDialogIndex) {
        if (selectedIndex == DialogConstants.DIALOG_DIAL_LIST) {
            DialCodeModel mDialCodeModel = (DialCodeModel) mObj;
            mDialCode.setText("(" + mDialCodeModel.getDialCode() + ")" + mDialCodeModel.getCode());
            mPhoneNuber = mDialCodeModel.getDialCode();
        }

    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.REGISTER_SERVICE)) {
            RegisterModel registerModel = (RegisterModel) responseObject;
            if (registerModel.getSuccess() == 1) {
                mDialogUtils.okFunc(mActivity, registerModel.getMsg(), "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            }else{
                mDialogUtils.okFunc(mActivity, registerModel.getMsg(), "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);

            }
            mActivity.getSupportFragmentManager().popBackStack();
            mActivity.getSupportFragmentManager().popBackStack();
        }
    }


    /**
     * Background Async task to load user profile picture from url
     */
    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... uri) {
            String url = uri[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

            if (result != null) {


                Bitmap resized = Bitmap.createScaledBitmap(result, 200, 200, true);
                //  bmImage.setImageBitmap(ImageHelper.getRoundedCornerBitmap(getContext(),resized,250,200,200, false, false, false, false));

            }
        }
    }

    public void getProfileData(Object obj) {
        mProfileModel = (ProfileModel) obj;
    }

}
