package com.pss.booking.activity.profile;

import android.os.Bundle;
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
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.Utils.ValidationUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.DialCodeModel;
import com.pss.booking.model.UpdateProfileModel;
import com.pss.booking.model.UserProfileModel;
import com.squareup.picasso.Picasso;


/**
 * Created by Manu on 10/1/2016.
 */
public class FragmentProfile extends Fragment implements View.OnClickListener, DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    MainActivity mActivity;
    private EditText mFiratNameTextbox;
    private EditText mLastNameTextbox;
    private EditText mEmailTextbox;
    private EditText mPasswordTextbox;
    private EditText mConfirmPasswordTextbox;
    private EditText mMobileTextbox;
    private Button mSubmitButton;

    private ImageView mProfileImageView;
    private TextView mNameTextbox;
    private TextView mEmailIDTextbox;
    UserProfileModel userProfileModel;
    private View rootView;
    private TextView mDropDownDialCode;
    DialogUtils mDialogUtils;
    NetworkManager mNetworkManager;
    UserPefer mUserPefer;
    private ImageView mEditButton, mLogoutButton;
    private boolean isEditable = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        mNetworkManager = new NetworkManager(mActivity, this);
        mUserPefer = new UserPefer(mActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.profile),false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        if (mUserPefer.getUserId() != null) {
            mNetworkManager.userProfile(mUserPefer.getUserId());
        }
        mDropDownDialCode = (TextView) rootView.findViewById(R.id.dropdown_dial_code);
        mDropDownDialCode.setOnClickListener(this);
        // Inflate the layout for this fragment
        initUI();
        return rootView;
    }


    private void initUI() {
        mEditButton = (ImageView) rootView.findViewById(R.id.EditButton);
        mLogoutButton = (ImageView) rootView.findViewById(R.id.LogoutButton);
        mSubmitButton = (Button) rootView.findViewById(R.id.SubmitButton);
        mFiratNameTextbox = (EditText) rootView.findViewById(R.id.firstnameTextbox);
        mLastNameTextbox = (EditText) rootView.findViewById(R.id.lastnameTextbox);
        mEmailTextbox = (EditText) rootView.findViewById(R.id.emailTextbox);
        mMobileTextbox = (EditText) rootView.findViewById(R.id.phoneTextbox);
        mPasswordTextbox = (EditText) rootView.findViewById(R.id.newpaswordTextbox);
        mConfirmPasswordTextbox = (EditText) rootView.findViewById(R.id.confirmpaswordTextbox);
        mPasswordTextbox.setVisibility(View.GONE);
        mConfirmPasswordTextbox.setVisibility(View.GONE);
        mProfileImageView = (ImageView) rootView.findViewById(R.id.ProfileImage);
        mNameTextbox = (TextView) rootView.findViewById(R.id.NameLabel);
        mEmailIDTextbox = (TextView) rootView.findViewById(R.id.emailidTextBox);

        mSubmitButton.setOnClickListener(this);
        mEditButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);

        mFiratNameTextbox.setEnabled(false);
        mLastNameTextbox.setEnabled(false);
        mEmailTextbox.setEnabled(false);
        mDropDownDialCode.setClickable(false);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dropdown_dial_code) {
            mDialogUtils.dialogDialCode(getActivity(), CommonUtils.getCallingCodes(), FragmentProfile.this, DialogConstants.DIALOG_DIAL_LIST);
        } else if (v.getId() == R.id.SubmitButton) {
            if (isValidated()) {
                if (isEditable) {
                    mNetworkManager.updateUser(mUserPefer.getUserId(), mFiratNameTextbox.getText().toString().trim(),
                            mLastNameTextbox.getText().toString().trim(), mEmailTextbox.getText().toString().trim(),
                            mMobileTextbox.getText().toString().trim());
                }
            }
        } else if (v.getId() == R.id.EditButton) {
            if (isEditable == true) {
                mFiratNameTextbox.setEnabled(true);
                mLastNameTextbox.setEnabled(true);
                mEmailTextbox.setEnabled(true);
                mMobileTextbox.setEnabled(true);
                mEditButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                isEditable = false;
                mDropDownDialCode.setClickable(true);
            } else {
                mFiratNameTextbox.setEnabled(false);
                mLastNameTextbox.setEnabled(false);
                mEmailTextbox.setEnabled(false);
                mMobileTextbox.setEnabled(false);
                isEditable = true;
                mEditButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mDropDownDialCode.setClickable(false);

            }
        } else if (v.getId() == R.id.LogoutButton) {

            mDialogUtils.doubleFunc(mActivity, "Do you want to logout?", "PSS", this, DialogConstants.DIALOG_BUTTON_POSITIVE);
        }
    }

    private boolean isValidated() {

        if (StringUtils.isBlank(mFiratNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter FirstName", "PSS", FragmentProfile.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mLastNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter LastName", "PSS", FragmentProfile.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter EmailID", "PSS", FragmentProfile.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (!ValidationUtils.validateEmail(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter valid EmailID", "PSS", FragmentProfile.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mMobileTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Mobile number", "PSS", FragmentProfile.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }

        return true;
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int mDialogIndex) {
        if (mDialogIndex == DialogConstants.DIALOG_DIAL_LIST) {

            DialCodeModel mDialCodeModel = (DialCodeModel) mObj;
            mDropDownDialCode.setText("(" + mDialCodeModel.getDialCode() + ")" + mDialCodeModel.getCode());
        }
        if (mDialogIndex == DialogConstants.DIALOG_BUTTON_POSITIVE) {
            Log.e("Logout ----->", "wow");
            mUserPefer.logoutUser();

            mFiratNameTextbox.setEnabled(false);
            mLastNameTextbox.setEnabled(false);
            mEmailTextbox.setEnabled(false);
            mMobileTextbox.setEnabled(false);
            mFiratNameTextbox.setText("");
            mLastNameTextbox.setText("");
            mEmailTextbox.setText("");
            mMobileTextbox.setText("");
            mNameTextbox.setText("");
            mEmailIDTextbox.setText("xxxxxx@xxxx.com");
            mProfileImageView.setImageResource(R.drawable.ic_profile);
            mActivity.finish();
        }
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {

        if (responseType.equals(RequestType.USER_PROFILE)) {
            userProfileModel = (UserProfileModel) responseObject;
            mFiratNameTextbox.setText(userProfileModel.getFirst_name());
            mLastNameTextbox.setText(userProfileModel.getLast_name());
            mEmailTextbox.setText(userProfileModel.getEmail());
            mMobileTextbox.setText(userProfileModel.getMobile());
            mNameTextbox.setText(userProfileModel.getFirst_name() + " " + userProfileModel.getLast_name());
            mEmailIDTextbox.setText(userProfileModel.getEmail());

            mFiratNameTextbox.setEnabled(false);
            mLastNameTextbox.setEnabled(false);
            mEmailTextbox.setEnabled(false);
            mMobileTextbox.setEnabled(false);
            Picasso.with(mActivity)
                    .load(userProfileModel.getLogo_url())
                    .placeholder(R.drawable.profile) // optional
                    .error(R.drawable.profile)         // optional
                    .into(mProfileImageView);

        } else if (responseType.equals(RequestType.EDIT_PROFILE)) {
            UpdateProfileModel mUserProfileModel = (UpdateProfileModel) responseObject;
            mDialogUtils.okFunc(mActivity, mUserProfileModel.getMsg(), "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
        }

    }


}
