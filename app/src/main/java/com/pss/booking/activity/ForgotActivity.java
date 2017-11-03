package com.pss.booking.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.ValidationUtils;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.ForgotModel;


/**
 * Created by Manu on 9/11/2016.
 */
public class ForgotActivity extends AppCompatActivity implements View.OnClickListener, DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    DialogUtils mDialogUtils;
    private EditText mEmailTextbox;
    private Button mSubmitButton;
    NetworkManager mNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_layout);
        mNetworkManager = new NetworkManager(this, this);
        mEmailTextbox = (EditText) findViewById(R.id.editTextEmail);
        mSubmitButton = (Button) findViewById(R.id.buttonSubmitButton);
        mSubmitButton.setOnClickListener(this);
        mDialogUtils = new DialogUtils();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSubmitButton) {
            if (ValidationUtils.validateEmail(mEmailTextbox.getText().toString().trim())) {
                mNetworkManager.forgotPassword(mEmailTextbox.getText().toString().trim());
            } else {
                mDialogUtils.okFunc(ForgotActivity.this, "Please enter valid email", "Booking App", ForgotActivity.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            }
        }
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int dialogIndex) {

    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if(responseType.equals(RequestType.FORGOT_PASSWORD)){
            ForgotModel mForgotModel = (ForgotModel)responseObject;
            mDialogUtils.okFunc(ForgotActivity.this, mForgotModel.getMsg(), "Forgot Password", ForgotActivity.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
        }

    }
}
