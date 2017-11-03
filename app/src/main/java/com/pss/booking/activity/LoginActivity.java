package com.pss.booking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.CommonUtils;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.StringUtils;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.DialCodeModel;
import com.pss.booking.model.LoginModel;


/**
 * Created by Manu on 9/11/2016.
 */
public class LoginActivity extends AppCompatActivity implements DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    DialogUtils mDialogUtils;
    private Button mLoginButton, mForgotButton;
    private LinearLayout mSignupButton;
    private EditText mUsernameTextbox;
    private EditText mPasswordTextbox;
    private EditText mPhoneTextbox;
    NetworkManager mNetworkManager;
    private String mobileCode = "+91";
    private TextView mDialCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        mDialogUtils = new DialogUtils();
        mNetworkManager = new NetworkManager(LoginActivity.this, this);
        initializeUI();
        setListners();

    }

    private void initializeUI() {
        mUsernameTextbox = (EditText) findViewById(R.id.usernameTextbox);
        mPasswordTextbox = (EditText) findViewById(R.id.paswordTextbox);
        mPhoneTextbox = (EditText) findViewById(R.id.phoneTextbox);
        mLoginButton = (Button) findViewById(R.id.LoginButton);
        mForgotButton = (Button) findViewById(R.id.ForgotButton);
        mSignupButton = (LinearLayout) findViewById(R.id.SignupButton);
        mDialCodeView = (TextView) findViewById(R.id.dropdownDialCode);
    }

    private void setListners() {
        mLoginButton.setOnClickListener(onClickListners);
        mSignupButton.setOnClickListener(onClickListners);
        mForgotButton.setOnClickListener(onClickListners);
        mDialCodeView.setOnClickListener(onClickListners);
    }

    View.OnClickListener onClickListners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.LoginButton: {
                    if (isValidated()) {
//                        mNetworkManager.Login(mUsernameTextbox.getText().toString().trim(), mPasswordTextbox.getText().toString().trim(),
//                                mobileCode + mPhoneTextbox.getText().toString().trim());

                    } else {
                        mDialogUtils.okFunc(LoginActivity.this, "Invalid usename or password", "BookingApp", LoginActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
                    }
                }


                break;
                case R.id.SignupButton: {
                    Intent inSignup = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(inSignup);
                }
                break;
                case R.id.ForgotButton: {

                    Intent inSignup = new Intent(LoginActivity.this, ForgotActivity.class);
                    startActivity(inSignup);
                }
                break;
                case R.id.dropdownDialCode: {

                    mDialogUtils.dialogDialCode(LoginActivity.this, CommonUtils.getCallingCodes(), LoginActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
                }
                break;
            }
        }
    };

    private boolean isValidated() {
        int index = 3;
        if (StringUtils.isBlank(mUsernameTextbox.getText().toString().trim())) {
            index--;
        } else if (StringUtils.isBlank(mPasswordTextbox.getText().toString().trim())) {
            index--;
        } else if (StringUtils.isBlank(mPhoneTextbox.getText().toString().trim())) {
            index--;
        }
        return index == 3;
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int dialogIndex) {
        if (selectedIndex == DialogConstants.DIALOG_DIAL_LIST) {

            DialCodeModel mDialCodeModel = (DialCodeModel) mObj;
            mDialCodeView.setText("(" + mDialCodeModel.getDialCode() + ")" + mDialCodeModel.getCode());
            mobileCode = mDialCodeModel.getDialCode();
        }

    }


    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.LOGIN_SERVICE)) {
            LoginModel mLoginModel = (LoginModel) responseObject;
            if (mLoginModel.getSuccess() == 1) {
               Intent i = new Intent(LoginActivity.this, ProceedBookingActivity.class);
                startActivity(i);
            }
            //
        }

    }
}
