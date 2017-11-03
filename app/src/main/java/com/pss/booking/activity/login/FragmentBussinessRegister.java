package com.pss.booking.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import com.pss.booking.model.RegisterModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by mdev3 on 11/14/16.
 */
public class FragmentBussinessRegister extends Fragment implements DialogUtils.OnDialogSelectedListener, ServiceResponseListener {
    DialogUtils mDialogUtils;
    NetworkManager mNetworkManager;
    MainActivity mActivity;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;

    private EditText mFirstNameTextbox;
    private EditText mLastNameTextbox;
    private EditText mCompanyTextbox;
    private EditText mEmailTextbox;
    private EditText mPasswordTextbox;
    private EditText mPhoneNumberTextbox;
    private EditText mLandNumberTextbox;
    private EditText mDescriptionTextbox;

    private TextView mDialCode, mDialLandCode;
    private Button mSubmitButton;
    private Button mUpdateButton;
    private EditText mCityTextbox;
    private EditText mCountryTextbox;

    private String mPhoneNuber = "+91";

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
        rootView = inflater.inflate(R.layout.fragment_bussiness_register, container, false);
        initialUI();

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.register),false);
    }
    private void initialUI() {
        mFirstNameTextbox = (EditText)rootView. findViewById(R.id.firstnameTextbox);
        mLastNameTextbox = (EditText)rootView. findViewById(R.id.lastnameTextbox);
        mEmailTextbox = (EditText)rootView. findViewById(R.id.emailTextbox);
        mPasswordTextbox = (EditText)rootView. findViewById(R.id.paswordTextbox);
        mPhoneNumberTextbox = (EditText)rootView. findViewById(R.id.phoneTextbox);
        mLandNumberTextbox = (EditText)rootView. findViewById(R.id.LandphoneTextbox);
        mCompanyTextbox = (EditText)rootView. findViewById(R.id.companynameTextbox);
        mDescriptionTextbox = (EditText)rootView.findViewById(R.id.DescriptionTextbox);
        mSubmitButton = (Button) rootView.findViewById(R.id.SubmitButton);
        mDialCode = (TextView)rootView. findViewById(R.id.dialoCode);
        mCityTextbox = (EditText)rootView.findViewById(R.id.cityTextbox);
        mCountryTextbox = (EditText)rootView.findViewById(R.id.countryTextbox);
        mUpdateButton = (Button)rootView.findViewById(R.id.UploadButton);
       mDialCode.setOnClickListener(onClick);
       mSubmitButton.setOnClickListener(onClick);
        mUpdateButton.setOnClickListener(onClick);
    }
    public void setUserType(String usertype){
        Log.e("USER TYPE  ",""+usertype);
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
        if (responseType.equals(RequestType.BUSINESS_REGISTER_SERVICE)) {
            RegisterModel registerModel = (RegisterModel) responseObject;
//            if(registerModel.getSuccess()==1) {
//                mDialogUtils.okFunc(mActivity, registerModel.getMsg(), "PSS", this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
//            }
            if (registerModel.getSuccess() == 1) {
                mDialogUtils.okFunc(mActivity, registerModel.getMsg(), "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            }else{
                mDialogUtils.okFunc(mActivity, registerModel.getMsg(), "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);

            }
            mActivity.getSupportFragmentManager().popBackStack();
            mActivity.getSupportFragmentManager().popBackStack();
        }

    }
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.SubmitButton) {
                if (isValidated()) {
                    mNetworkManager.setBussinessRegistration("business",mFirstNameTextbox.getText().toString().trim()
                            ,mLastNameTextbox.getText().toString().trim(),
                            mEmailTextbox.getText().toString().trim(),
                            mPasswordTextbox.getText().toString().trim(),
                            mPhoneNumberTextbox.getText().toString().trim(),
                            mLandNumberTextbox.getText().toString().trim(),
                            mCityTextbox.getText().toString().trim()+mCountryTextbox.getText().toString().trim(),
                            mCompanyTextbox.getText().toString().trim(),
                            mDescriptionTextbox.getText().toString().trim()
                            );
                }
            } else if (v.getId() == R.id.dialoCode) {
                mDialogUtils.dialogDialCode(mActivity, CommonUtils.getCallingCodes(), FragmentBussinessRegister.this, DialogConstants.DIALOG_DIAL_LIST);
            }else if(v.getId()==R.id.UploadButton){
                showFileChooser();
            }
        }
    };
    private boolean isValidated() {

        if (StringUtils.isBlank(mFirstNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter FirstName", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mLastNameTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter LastName", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }else if (StringUtils.isBlank(mCompanyTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Company Name ", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }  else if (StringUtils.isBlank(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter EmailID", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (!ValidationUtils.validateEmail(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter valid EmailID", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mPasswordTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Password", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mPhoneNumberTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Mobile number", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mLandNumberTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Landline number", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mCityTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter City", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mCountryTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Country", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mDescriptionTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Description", "PSS", FragmentBussinessRegister.this,DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }

        return true;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                uploadImage();
                Log.e("Image------- > ", "hhjhjh");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String UPLOAD_URL = "http://pssbooking.com/apps/users/user_image.php";

    private void uploadImage() {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(mActivity, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(mActivity, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(mActivity, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name = "" + System.currentTimeMillis();

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}
