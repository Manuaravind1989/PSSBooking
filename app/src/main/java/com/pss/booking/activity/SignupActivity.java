package com.pss.booking.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.CommonUtils;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.StringUtils;
import com.pss.booking.Utils.ValidationUtils;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.DialCodeModel;
import com.pss.booking.model.RegisterModel;


/**
 * Created by Manu on 9/11/2016.
 */
public class SignupActivity extends AppCompatActivity implements DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    DialogUtils mDialogUtils;
    private EditText mFirstNameTextbox;
    private EditText mLastNameTextbox;
    private EditText mEmailTextbox;
    private EditText mPasswordTextbox;
    private EditText mPhoneNumberTextbox;
    private TextView mDialCode;
    private Button mSubmitButton;
    NetworkManager mNetworkManager;
    private String mPhoneNuber = "+91";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_layout);
        mDialogUtils = new DialogUtils();

        mNetworkManager = new NetworkManager(SignupActivity.this, this);


        initialUI();
    }

    private void initialUI() {
        mFirstNameTextbox = (EditText) findViewById(R.id.firstnameTextbox);
        mLastNameTextbox = (EditText) findViewById(R.id.lastnameTextbox);
        mEmailTextbox = (EditText) findViewById(R.id.emailTextbox);
        mPasswordTextbox = (EditText) findViewById(R.id.paswordTextbox);
        mPhoneNumberTextbox = (EditText) findViewById(R.id.phoneTextbox);
        mSubmitButton = (Button) findViewById(R.id.SubmitButton);
        mDialCode = (TextView) findViewById(R.id.dialoCode);
        mDialCode.setOnClickListener(onClick);
        mSubmitButton.setOnClickListener(onClick);
    }
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.SubmitButton) {
                if (isValidated()) {
                    mNetworkManager.setRegistration(getIntent().getStringExtra("USERTYPE"), mFirstNameTextbox.getText().toString().trim(), mLastNameTextbox.getText().toString().trim(),
                            mEmailTextbox.getText().toString().trim(), mPasswordTextbox.getText().toString().trim(), mPhoneNuber+mPhoneNumberTextbox.getText().toString().trim());

                }
            } else if (v.getId() == R.id.dialoCode) {
                mDialogUtils.dialogDialCode(SignupActivity.this, CommonUtils.getCallingCodes(), SignupActivity.this,DialogConstants.DIALOG_DIAL_LIST);
            }
        }
    };
    private boolean isValidated() {

        if (StringUtils.isBlank(mFirstNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(SignupActivity.this, "Enter FirstName", "Booking App", SignupActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mLastNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(SignupActivity.this, "Enter LastName", "Booking App", SignupActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(SignupActivity.this, "Enter EmailID", "Booking App", SignupActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (!ValidationUtils.validateEmail(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(SignupActivity.this, "Enter valid EmailID", "Booking App", SignupActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mPasswordTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(SignupActivity.this, "Enter Password", "Booking App", SignupActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }else if (StringUtils.isBlank(mPhoneNumberTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(SignupActivity.this, "Enter Phone number", "Booking App", SignupActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }

        return true;
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj , int dialogIndex) {
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
            mDialogUtils.okFunc(SignupActivity.this, registerModel.getMsg(), "Booking App", this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
        }
    }
}
