package com.pss.booking.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pss.booking.R;
import com.pss.booking.Utils.CommonUtils;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.StringUtils;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.HotelModel;
import com.pss.booking.model.LoginModel;
import com.pss.booking.model.ProfileModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by mdev3 on 11/14/16.
 */
public class FragmentLogin extends Fragment implements DialogUtils.OnDialogSelectedListener, ServiceResponseListener, GoogleApiClient.OnConnectionFailedListener {
    View rootView;
    DialogUtils mDialogUtils;
    private Button mLoginButton, mForgotButton;
    private LinearLayout mSignupButton;
    private EditText mUsernameTextbox;
    private EditText mPasswordTextbox;
    // private EditText mPhoneTextbox;
    NetworkManager mNetworkManager;
    private String mobileCode = "+91";
    // private TextView mDialCodeView;
    MainActivity mActivity;
    UserPefer mUserPefer;
    OnFragmentChangeListener mOnFragmentChangeListener;
    private Button mFacebookButton, mGooglePlusButton;
    private int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    HotelModel.PostsEntity mHotelDetails;
    private CallbackManager callbackManager;

    int Login_status = 999;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mDialogUtils = new DialogUtils();
        mNetworkManager = new NetworkManager(mActivity, this);
        mOnFragmentChangeListener = (MainActivity) getActivity();
        mUserPefer = new UserPefer(mActivity);
        initGooglePlus();
        initFacebook();
        printHashKey();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_login_layout, container, false);
        initializeUI();
        setListners();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly.
//            //Log.d(TAG, "Got cached sign-in");
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void initializeUI() {
        mUsernameTextbox = (EditText) rootView.findViewById(R.id.usernameTextbox);
        mPasswordTextbox = (EditText) rootView.findViewById(R.id.paswordTextbox);
        //  mPhoneTextbox = (EditText)rootView. findViewById(R.id.phoneTextbox);
        mLoginButton = (Button) rootView.findViewById(R.id.LoginButton);
        mForgotButton = (Button) rootView.findViewById(R.id.ForgotButton);
        mSignupButton = (LinearLayout) rootView.findViewById(R.id.SignupButton);
        mFacebookButton = (Button) rootView.findViewById(R.id.facebookButton);
        mGooglePlusButton = (Button) rootView.findViewById(R.id.GooglePlusButton);
    }

    private void setListners() {
        mLoginButton.setOnClickListener(onClickListners);
        mSignupButton.setOnClickListener(onClickListners);
        mForgotButton.setOnClickListener(onClickListners);
        mFacebookButton.setOnClickListener(onClickListners);
        mGooglePlusButton.setOnClickListener(onClickListners);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle(getString(R.string.login), false);
    }

    View.OnClickListener onClickListners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.LoginButton: {
                    if (isValidated()) {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                        mNetworkManager.Login(mUsernameTextbox.getText().toString().trim(), mPasswordTextbox.getText().toString().trim(),refreshedToken);

                    } else {
                        mDialogUtils.okFunc(mActivity, "Invalid usename or password", "PSS", FragmentLogin.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
                    }
                }


                break;
                case R.id.SignupButton: {
                    mOnFragmentChangeListener.onChangeListener(ActivityConstants.USER_TYPE, null);
                }
                break;
                case R.id.ForgotButton: {

                    mOnFragmentChangeListener.onChangeListener(ActivityConstants.FORGOT_PASSWORD, null);
                }
                break;
                case R.id.dropdownDialCode: {

                    mDialogUtils.dialogDialCode(mActivity, CommonUtils.getCallingCodes(), FragmentLogin.this, DialogConstants.DIALOG_DIAL_LIST);
                }
                break;
                case R.id.facebookButton: {
                    LoginManager.getInstance().logInWithReadPermissions(FragmentLogin.this, Arrays.asList("public_profile"));

                }
                break;
                case R.id.GooglePlusButton: {
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
                break;
            }
        }
    };

    public void printHashKey() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.pss.booking",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
        mGoogleApiClient = null;
        Log.e("DESTROY==== >", "DESTRUO");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("STOP==== >", "STOP");
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(getActivity());
            mGoogleApiClient.disconnect();
            //  mGoogleApiClient =null;
        }
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        registerCallBack();
    }

    private void initGooglePlus() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //  mGoogleApiClient =null;
        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void registerCallBack() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        showProgressDialog();
                        String token = loginResult.getAccessToken().getToken();
                        Log.e("Sucess", "" + token);
                        // accessToken.setText("Access Token: " + token);
                        // this is your access token. Pass this to server for further procedure.
                        Log.i("accessToken", token);

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e("LoginActivity", response.toString());
                                // Get facebook data from login
                                hideProgressDialog();
                                Bundle bFacebookData = getFacebookData(object);
                                ProfileModel mProfileModel = new ProfileModel();
                                mProfileModel.setFirstName(bFacebookData.get("first_name").toString());
                                mProfileModel.setLastName(bFacebookData.get("last_name").toString());
                                mProfileModel.setEmail(bFacebookData.get("email").toString());
                                mProfileModel.setImage(bFacebookData.get("profile_pic").toString());

                                Log.e("LoginActivity", "object   ===>");
                                mOnFragmentChangeListener.onChangeListener(ActivityConstants.SIGN_UP_FRAGMENT, mProfileModel);

                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Par �metros que pedimos a facebook
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        //  Log.d(TAG, "cancelled");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        //  Log.d(TAG, exception.toString());
                    }
                });
    }


    private boolean isValidated() {
        int index = 2;
        if (StringUtils.isBlank(mUsernameTextbox.getText().toString().trim())) {
            index--;
        } else if (StringUtils.isBlank(mPasswordTextbox.getText().toString().trim())) {
            index--;
        }
        return index == 2;
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int mDialogIndex) {
//        if (selectedIndex == DialogConstants.DIALOG_DIAL_LIST) {
//
//            DialCodeModel mDialCodeModel = (DialCodeModel) mObj;
//            mDialCodeView.setText("(" + mDialCodeModel.getDialCode() + ")" + mDialCodeModel.getCode());
//            mobileCode = mDialCodeModel.getDialCode();
//        }

    }


    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.LOGIN_SERVICE)) {
            LoginModel mLoginModel = (LoginModel) responseObject;
            if (mLoginModel.getSuccess() == 1) {
                mUserPefer.createLoginSession(mLoginModel.getUser_id(), mLoginModel.getEmail(), mLoginModel.getUser_type());
                mActivity.getSupportFragmentManager().popBackStack();
//
//                Intent i = new Intent(getActivity(), ProceedBookingActivity.class);
//                startActivity(i);
                if (Login_status == 999) {
                    mActivity.getSupportFragmentManager().popBackStack();
                    mOnFragmentChangeListener.onChangeListener(ActivityConstants.PROCES_CONFIRM, mHotelDetails);
                } else if (Login_status == 1000) {
                    if (!mLoginModel.getUser_type().equals("private")) {
                        mActivity.getSupportFragmentManager().popBackStack();
                        mOnFragmentChangeListener.onChangeListener(ActivityConstants.POST_CATEGORY, null);
                    } else {
                        mDialogUtils.okFunc(mActivity, "Private user cannot add the post", "PSS", FragmentLogin.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
                    }

                } else {
                    if (!mLoginModel.getUser_type().equals("private")) {
                        mActivity.getSupportFragmentManager().popBackStack();
                        mOnFragmentChangeListener.onChangeListener(ActivityConstants.POST_CATEGORY, null);
                    } else {
                        mDialogUtils.okFunc(mActivity, "Private user cannot add the post", "PSS", FragmentLogin.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);

                    }

                }

            } else if (mLoginModel.getSuccess() == 2) {
//                Intent i = new Intent(mActivity, ProceedBookingActivity.class);
//                startActivity(i);
            } else {
                mDialogUtils.okFunc(mActivity, "Login Failed", "PSS", FragmentLogin.this, DialogConstants.DIALOG_BUTTON_NEUTRAL);
            }
            //
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("PSS", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //Similarly you can get the email and photourl using acct.getEmail() and  acct.getPhotoUrl()
            //  mFirstNameTextbox.setText(acct.getDisplayName());
            // mEmailTextbox.setText(acct.getEmail());
            Log.e("Display Name ", acct.getDisplayName());
            Log.e("Email ", acct.getEmail());

//            mNetworkManager.setRegistration(acct.getDisplayName().replaceAll(" ", "."), "",
//                    acct.getEmail(), "", "",
//                    "");

            ProfileModel mProfileModel = new ProfileModel();
            mProfileModel.setFirstName(acct.getDisplayName());
            mProfileModel.setLastName(acct.getDisplayName());
            mProfileModel.setEmail(acct.getEmail());
            // mProfileModel.setImage(acct.getPhotoUrl());
            //  mProfileModel.setFirstName(bFacebookData.get("first_name").toString());

            Log.e("LoginActivity", "object   ===>");
            mOnFragmentChangeListener.onChangeListener(ActivityConstants.SIGN_UP_FRAGMENT, mProfileModel);

            if (acct.getPhotoUrl() != null)
                //    new LoadProfileImage(imgProfilePic).execute(acct.getPhotoUrl().toString());
                Log.e("Button Click event", "done");
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            Log.e("Email ", "else 3333");
            updateUI(false);
        }
    }


    private void updateUI(boolean signedIn) {
        if (signedIn) {
            mGooglePlusButton.setVisibility(View.VISIBLE);

        } else {
            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.profile);
            // imgProfilePic.setImageBitmap(ImageHelper.getRoundedCornerBitmap(getContext(),icon, 200, 200, 200, false, false, false, false));
            mGooglePlusButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d("PSS", "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading....");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }

    }

    private Bundle getFacebookData(JSONObject object) {
        Log.e("Response", "" + object.toString());
        Bundle bundle = null;
        try {
            bundle = new Bundle();
            String id = object.getString("id");
            Log.e("id =====>", id);

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            Log.e("first name ====>", object.getString("first_name"));
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (JSONException e) {
            Log.d("", "Error parsing JSON");
        }

        return bundle;
    }

    public void getData(Object obj) {
        if (obj != null) {
            if (obj instanceof HotelModel.PostsEntity) {
                mHotelDetails = (HotelModel.PostsEntity) obj;
                Login_status = 999;
            } else {
                int value = (Integer) obj;
                Log.e("GET VALUE +++>  ", "" + value);
                Login_status = value;
            }

        }
    }
}
