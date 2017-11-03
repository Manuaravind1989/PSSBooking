package com.pss.booking.activity.post;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.Utils.ValidationUtils;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.AddPostModel;
import com.pss.booking.model.DialCodeModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Manu on 12/10/2016.
 */
public class FragmentPost extends Fragment implements DialogUtils.OnDialogSelectedListener, ServiceResponseListener, View.OnClickListener {
    MainActivity mActivity;
    DialogUtils mDialogUtils;
    NetworkManager mNetworkManager;
    OnFragmentChangeListener mOnFragmentChangeListener;
    View rootView;
    private String mPhoneNuber = "+91";
    private LinearLayout mCategoryLayout, mSubCategoryLayout;
    private TextView mCategoryLabel;
    private TextView mSubCategoryLabel;
    private EditText mEmailTextbox;
    private TextView mDialCode;
    private EditText mMobileTextbox;
    private TextView mCurrencyCode;
    private EditText mAmountTextbox;
    private EditText mCountTextbox;
    private EditText mDiscountTextbox;
    private EditText mTitleTextbox;
    private EditText mSubtitlextbox;
    private EditText mFeatureTextbox;
    private EditText mAmenitiesTextbox;
    private EditText mDescriptionTextbox;
    private EditText mLocationTextbox;
    private Button mUploadButton;
    private Button mDoneButton;
    private HashMap<String, String> mHashMapList = new HashMap<String, String>();
    UserPefer mUserPefer;
    FragmentPostSubCategory.ItemCategoryModel mItemCategoryModel;
    String[] arrOfCategories = {"HOTELS", "TRAVEL & TRANSPORT", "PRIVATE RENTING", "INDUSTRIES & EQUIPMENTS", "HIRE"};
    private int mYear, mMonth, mDay, mHour, mMinute;
    private LinearLayout mStartTimeLayoutBtton, mEndTimeLayoutButton, mCreatedDateLayoutButton, mExpiryDateLAyoutButton;

    private TextView mStartTimeLabel, mEndTimeLabel, mCreatedDateLabel, mExpiryDateLabel;
    private Button mLocationButton;
    private String Lat="", Lng="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        mNetworkManager = new NetworkManager(mActivity, this);
        mOnFragmentChangeListener = (MainActivity) getActivity();
        mUserPefer = new UserPefer(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_post_layout, container, false);
        if (mUserPefer.getUserId() != null) {
            mNetworkManager.userProfile(mUserPefer.getUserId());
        }
        initUI();
        setListners();
        setValues();
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.nav_postAdd),false);
    }

    public void getCategoryDetails(FragmentPostSubCategory.ItemCategoryModel model) {
        this.mItemCategoryModel = model;
    }

    private void initUI() {

        mCategoryLayout = (LinearLayout) rootView.findViewById(R.id.Category);
        mSubCategoryLayout = (LinearLayout) rootView.findViewById(R.id.SubCategory);
        mCategoryLabel = (TextView) rootView.findViewById(R.id.CategoryLabel);
        mSubCategoryLabel = (TextView) rootView.findViewById(R.id.SubCategoryLabel);
        mEmailTextbox = (EditText) rootView.findViewById(R.id.emailTextbox);
        mDialCode = (TextView) rootView.findViewById(R.id.dialoCode);
        mMobileTextbox = (EditText) rootView.findViewById(R.id.phoneTextbox);
        mCurrencyCode = (TextView) rootView.findViewById(R.id.CurrencyCode);
        mAmountTextbox = (EditText) rootView.findViewById(R.id.AmountTextbox);
        mCountTextbox = (EditText) rootView.findViewById(R.id.CountTextbox);
        mTitleTextbox = (EditText) rootView.findViewById(R.id.TitleTextbox);
        mSubtitlextbox = (EditText) rootView.findViewById(R.id.SubTitleTextbox);
        mDiscountTextbox = (EditText) rootView.findViewById(R.id.DiscountTextbox);

        mLocationTextbox = (EditText) rootView.findViewById(R.id.LocationTextbox);
        mFeatureTextbox = (EditText) rootView.findViewById(R.id.FeatureTextbox);
        mAmenitiesTextbox = (EditText) rootView.findViewById(R.id.AmenitiesTextbox);
        mDescriptionTextbox = (EditText) rootView.findViewById(R.id.DescriptionTextbox);
        mUploadButton = (Button) rootView.findViewById(R.id.UploadButton);
        mDoneButton = (Button) rootView.findViewById(R.id.SubmitButton);
        mLocationButton = (Button) rootView.findViewById(R.id.LocationButton);

        mStartTimeLayoutBtton = (LinearLayout) rootView.findViewById(R.id.StartTimeLayout);
        mEndTimeLayoutButton = (LinearLayout) rootView.findViewById(R.id.EndTimeLayout);
        mCreatedDateLayoutButton = (LinearLayout) rootView.findViewById(R.id.CreatedDateLayout);
        mExpiryDateLAyoutButton = (LinearLayout) rootView.findViewById(R.id.ExpireDateLayout);

        mStartTimeLabel = (TextView) rootView.findViewById(R.id.StartTime);
        mEndTimeLabel = (TextView) rootView.findViewById(R.id.EndTime);
        mCreatedDateLabel = (TextView) rootView.findViewById(R.id.CreatedTime);
        mExpiryDateLabel = (TextView) rootView.findViewById(R.id.ExpireTime);
        getCurrentDate(mCreatedDateLabel);

    }
    public void getCurrentDate(TextView mTextView) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        String strDate =  mdformat.format(calendar.getTime());
        mTextView.setText(strDate);

    }



    private void setListners() {
        mCategoryLayout.setOnClickListener(this);
        mSubCategoryLayout.setOnClickListener(this);
        mUploadButton.setOnClickListener(this);
        mDoneButton.setOnClickListener(this);
        mDialCode.setOnClickListener(this);
        mCurrencyCode.setOnClickListener(this);
        mStartTimeLayoutBtton.setOnClickListener(this);
        mEndTimeLayoutButton.setOnClickListener(this);
      //  mCreatedDateLayoutButton.setOnClickListener(this);
     //   mExpiryDateLAyoutButton.setOnClickListener(this);
        mExpiryDateLAyoutButton.setVisibility(View.GONE);
        mLocationButton.setOnClickListener(this);
    }

    private void setValues() {
        int index = Integer.valueOf(mItemCategoryModel.getCategoryid()) - 1;
        mCategoryLabel.setText(arrOfCategories[index]);
        mSubCategoryLabel.setText(mItemCategoryModel.getSubCategoryName());
    }

    private boolean isValidated() {

        if (StringUtils.isBlank(mCategoryLabel.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "select category", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mSubCategoryLabel.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter LastName", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter EmailID", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (!ValidationUtils.validateEmail(mEmailTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter valid EmailID", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mMobileTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Mobile Number", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mAmountTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Amount", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mCountTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Count", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }

        else if (StringUtils.isBlank(mTitleTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Tile", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mSubtitlextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Subtitle", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mLocationTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Location", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mFeatureTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Features", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        } else if (StringUtils.isBlank(mAmenitiesTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Amenities", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mDescriptionTextbox.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Description", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mStartTimeLabel.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Start Time", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mEndTimeLabel.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter End Time", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(mCreatedDateLabel.getText().toString().trim())) {
            mDialogUtils.okFunc(mActivity, "Enter Created Date", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
//        else if (StringUtils.isBlank(mCreatedDateLabel.getText().toString().trim())) {
//            mDialogUtils.okFunc(mActivity, "Enter Expiry Date", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
//            return false;
//        }
        else if (StringUtils.isBlank(Lat)) {
            mDialogUtils.okFunc(mActivity, "Please Select Location From Map", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        else if (StringUtils.isBlank(Lng)) {
            mDialogUtils.okFunc(mActivity, "Please Select Location From Map", "PSS", FragmentPost.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            return false;
        }
        return true;
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int mDialogIndex) {
        if (mDialogIndex == DialogConstants.DIALOG_CATEGORY) {
            mCategoryLabel.setText((String) mObj);
        } else if (mDialogIndex == DialogConstants.DIALOG_SUBCATEGORY) {
            mSubCategoryLabel.setText((String) mObj);
        } else if (mDialogIndex == DialogConstants.DIALOG_DIAL_LIST) {
            DialCodeModel mDialCodeModel = (DialCodeModel) mObj;
            mDialCode.setText("(" + mDialCodeModel.getDialCode() + ")" + mDialCodeModel.getCode());
            mPhoneNuber = mDialCodeModel.getDialCode();
        } else if (mDialogIndex == DialogConstants.DIALOG_CURRENCY) {
            mCurrencyCode.setText((String) mObj);
        }
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.ADD_POST)) {
            AddPostModel mAddPostModel = (AddPostModel) responseObject;
            mDialogUtils.okFunc(mActivity, mAddPostModel.getMsg(), "PSS", this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            mActivity.finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Category) {
            String[] arr = {"category 1", "category 2", "category 3", "category 4", "category 5", "category 6", "category 7", "category 8", "category 9", "category 10"};
            // mDialogUtils.listFunc(mActivity, "Select a category", arr, FragmentPost.this, DialogConstants.DIALOG_CATEGORY);


        } else if (view.getId() == R.id.SubCategory) {
            String[] arr = {" sub category 1", " sub category 2", " sub category 3", " sub category 4", " sub category 5", " sub category 6", " sub category 7", " sub category 8", " sub category 9", " sub category 10"};
            //mDialogUtils.listFunc(mActivity, "Select a sub category", arr, FragmentPost.this, DialogConstants.DIALOG_SUBCATEGORY);


        } else if (view.getId() == R.id.dialoCode) {
            mDialogUtils.dialogDialCode(mActivity, CommonUtils.getCallingCodes(), FragmentPost.this, DialogConstants.DIALOG_DIAL_LIST);


        } else if (view.getId() == R.id.CurrencyCode) {
          //  String[] arr = {"USD", "INR", "AED"};
            mDialogUtils.listFunc(mActivity, "Select a currency", cuurecncycode, FragmentPost.this, DialogConstants.DIALOG_CURRENCY);

        } else if (view.getId() == R.id.StartTimeLayout) {
            timePicker(1);
        } else if (view.getId() == R.id.EndTimeLayout) {
            timePicker(2);
        } else if (view.getId() == R.id.CreatedDateLayout) {
            datePicker(1);

        } else if (view.getId() == R.id.ExpireDateLayout) {
            datePicker(2);
        } else if (view.getId() == R.id.UploadButton) {
            showFileChooser();
        } else if (view.getId() == R.id.SubmitButton) {
            if (isValidated()) {
                if (mUserPefer.getUserId() == null) {
                    Toast.makeText(getActivity(), "Please Login first", Toast.LENGTH_SHORT).show();
                    return;
                }
                String discount = "0";
                if(mDiscountTextbox.getText().length()!=0){
                    discount =mDiscountTextbox.getText().toString();
                }
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user_id", mUserPefer.getUserId());
                params.put("cat_id", mItemCategoryModel.getCategoryid());
                params.put("subcat_id", mItemCategoryModel.getSubcategoryid());
                params.put("time_start", mStartTimeLabel.getText().toString());
                params.put("time_end", mEndTimeLabel.getText().toString());
                params.put("amenities", mAmenitiesTextbox.getText().toString().trim());
                params.put("fetures", mFeatureTextbox.getText().toString().trim());
                params.put("location", mLocationTextbox.getText().toString().trim());
                params.put("amount", mAmountTextbox.getText().toString().trim());
                params.put("currency", mCurrencyCode.getText().toString().trim());
                params.put("email", mEmailTextbox.getText().toString().trim());
                params.put("mobile", mMobileTextbox.getText().toString().trim());
                params.put("date_create", mCreatedDateLabel.getText().toString());
                params.put("date_exp",mExpiryDateLabel.getText().toString());
                params.put("title",mTitleTextbox.getText().toString());
                params.put("sub_title",mSubtitlextbox.getText().toString());
                params.put("description",mDescriptionTextbox.getText().toString());
                params.put("count",mCountTextbox.getText().toString());
                params.put("lat",Lat);
                params.put("long",Lng);
                params.put("discount",discount);
                params.put("imgname", "" + System.currentTimeMillis());
                params.put("image", getStringImage(bitmap));


                mNetworkManager.addPost(params);
            }
        }
        else if(view.getId()==R.id.LocationButton){
            Intent i  = new Intent(mActivity, MapLocationActivity.class);
            startActivityForResult(i,200);

        }

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
                //  uploadImage();
                Log.e("Image------- > ", "hhjhjh");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            if(resultCode==2) {
                Lat = data.getStringExtra("LAT");
                Lng = data.getStringExtra("LNG");
            }else{
            Toast.makeText(mActivity, "Location not featured", Toast.LENGTH_SHORT).show();}
        }
    }

    public String getStringImage(Bitmap bmp) {
        if (bmp == null) {
            return "";
        }
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

    private void timePicker(final int index) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(mActivity,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        if (index == 1) {
                            mStartTimeLabel.setText(hourOfDay + ":" + minute);
                        } else if (index == 2) {
                            mEndTimeLabel.setText(hourOfDay + ":" + minute);
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void datePicker(final int index) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (index == 1) {

                            mCreatedDateLabel.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        } else if (index == 2) {

                            mExpiryDateLabel.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private String[]  cuurecncycode={"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM",
            "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BOV", "BRL", "BSD", "BTN", "BWP", "BYR",
            "BZD", "CAD", "CDF", "CHE", "CHF", "CHW", "CLF", "CLP", "CNY", "COP", "COU", "CRC", "CUC", "CUP",
            "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL",
            "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "INR",
            "IQD", "IRR", "ISK", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD",
            "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK",
            "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MXV", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK",
            "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB",
            "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "SSP", "STD", "SYP",
            "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "USN",
            "USS", "UYI", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XBA", "XBB",
            "XBC", "XBD", "XCD", "XDR", "XFU", "XOF", "XPD", "XPF", "XPT", "XTS", "XXX", "YER", "ZAR", "ZMW"};
}
