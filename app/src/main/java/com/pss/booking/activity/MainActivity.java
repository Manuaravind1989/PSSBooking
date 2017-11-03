package com.pss.booking.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.pss.booking.R;
import com.pss.booking.Utils.ArrayUtils;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.activity.aboutus.FragmentAboutus;
import com.pss.booking.activity.hire.FragmentHire;
import com.pss.booking.activity.hotels.FragmentHotels;
import com.pss.booking.activity.hotels.FragmentHotelsDetails;
import com.pss.booking.activity.indutries.FragmentIndustriesEquipments;
import com.pss.booking.activity.login.FragmentBussinessRegister;
import com.pss.booking.activity.login.FragmentForgotPassword;
import com.pss.booking.activity.login.FragmentLogin;
import com.pss.booking.activity.login.FragmentProcessConfirm;
import com.pss.booking.activity.login.FragmentSignup;
import com.pss.booking.activity.login.FragmentUserType;
import com.pss.booking.activity.post.FragmentPost;
import com.pss.booking.activity.post.FragmentPostCategory;
import com.pss.booking.activity.post.FragmentPostSubCategory;
import com.pss.booking.activity.profile.FragmentProfile;
import com.pss.booking.activity.rentings.FragmentRenting;
import com.pss.booking.activity.services.FragmentServices;
import com.pss.booking.activity.terms.FragmentTerms;
import com.pss.booking.activity.travels.FragmentTravelTransport;
import com.pss.booking.activity.travels.FragmentTravels;
import com.pss.booking.adapter.ArrivalTimeAdapter;
import com.pss.booking.callback.OnFragmentChangeListener;
import com.pss.booking.chat.FragmentChat;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.constants.DialogConstants;
import com.pss.booking.customview.ArrowDirection;
import com.pss.booking.customview.BubbleLayout;
import com.pss.booking.customview.BubblePopupHelper;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, DialogUtils.OnDialogSelectedListener, OnFragmentChangeListener {
    BubbleLayout bubbleLayout;
    private Toolbar mToolbar;
    private TextView mTextViewTitle;
    private FragmentDrawer drawerFragment;
    DialogUtils mDialogUtils;
    UserPefer mUserPefer;
    boolean isLog = false;
    // private ImageView mSearchButton, mMapButton, mProfileButton;
    private PopupWindow popupWindow;
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.imageViewsearch: {
//
//                }
//                break;
//                case R.id.Mapbutton: {
//
//                }
//                break;
//                case R.id.Profilebutton: {
//                    displayView(ActivityConstants.PROFILE_FRAGMENT);
//                }
//                break;
                case R.id.filterButton: {
                    int[] location = new int[2];
                    v.getLocationInWindow(location);
                    bubbleLayout.setArrowDirection(ArrowDirection.TOP);
                    popupWindow.showAtLocation(v, Gravity.TOP | Gravity.LEFT, 46, v.getHeight() + location[1]);
                }
                break;
            }
        }
    };
    private ImageView mFilterButton;
    private RecyclerView mArrivalRecyclerview, mLenghtStayRecyclerView, mBudgetRecyclerView, mRatingRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserPefer = new UserPefer(this);
        mDialogUtils = new DialogUtils();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //AnimationUtils.animateToolbarDroppingDown(mToolbar);
        mTextViewTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        Log.e("MainActivity   ==== >  ", "" + getIntent().getIntExtra("CATEGORY_TYPE", 0));
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, getIntent().getIntExtra("CATEGORY_TYPE", 0));
        drawerFragment.setDrawerListener(this);
        //initialFragment(getIntent().getIntExtra("CATEGORY_TYPE", 0));
        if (getIntent().getIntExtra("CATEGORY_TYPE", 0) == 8) {
            displayView(ActivityConstants.LOGINADS_FRAGMENT, 1000);
        }else if(getIntent().getIntExtra("CATEGORY_TYPE",0)==9){
            displayView(ActivityConstants.POST_CATEGORY, 9);
        }

        else {
            displayView(ActivityConstants.SUBCATEGORY, (getIntent().getIntExtra("CATEGORY_TYPE", 0) + 1));
        }

        initUI();
        filterComponent();
    }
//
//    private void initialFragment(int index) {
//
//        switch (index) {
//
//            case 0: {
//                //displayView(ActivityConstants.HOTELS_FRAGMENT, null);
//                displayView(ActivityConstants.SUBCATEGORY, index);
//
//            }
//            break;
//            case 1: {
//                //displayView(ActivityConstants.TRAVEL_FRAGMENT, null);
//                displayView(ActivityConstants.SUBCATEGORY, index);
//            }
//            break;
//            case 2: {
//                //displayView(ActivityConstants.RENTING_FRAGMENT, null);
//                displayView(ActivityConstants.SUBCATEGORY, index);
//            }
//            break;
//            case 3: {
//                // displayView(ActivityConstants.INDUSTRIES_FRAGMENT, null);
//                displayView(ActivityConstants.SUBCATEGORY, index);
//            }
//            break;
//            case 8: {
//                //displayView(ActivityConstants.HIRE_FRAGMENT, null);
//                displayView(ActivityConstants.SUBCATEGORY, index);
//            }
//            break;
//            case ActivityConstants.POST_CATEGORY: {
//                displayView(ActivityConstants.POST_CATEGORY, null);
//            }
//            break;
//        }
//
//    }

    private void filterComponent() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mArrivalRecyclerview = (RecyclerView) bubbleLayout.findViewById(R.id.Arrivaltime_recycler_view);
        mArrivalRecyclerview.setHasFixedSize(true);
        mArrivalRecyclerview.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new ArrivalTimeAdapter(ArrayUtils.getArrivalTime());
        mArrivalRecyclerview.setAdapter(adapter);

        mLenghtStayRecyclerView = (RecyclerView) bubbleLayout.findViewById(R.id.LengthStay_recycler_view);
        mLenghtStayRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLenghtStayRecyclerView.setLayoutManager(layoutManager2);
        RecyclerView.Adapter mLengthAdapter = new ArrivalTimeAdapter(ArrayUtils.getLengthOfStay());
        mLenghtStayRecyclerView.setAdapter(mLengthAdapter);

        mBudgetRecyclerView = (RecyclerView) bubbleLayout.findViewById(R.id.Budget_recycler_view);
        mBudgetRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager3
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mBudgetRecyclerView.setLayoutManager(layoutManager3);
        RecyclerView.Adapter mBudgetAdapter = new ArrivalTimeAdapter(ArrayUtils.getLengthBudget());
        mBudgetRecyclerView.setAdapter(mBudgetAdapter);


        mRatingRecyclerView = (RecyclerView) bubbleLayout.findViewById(R.id.Rating_recycler_view);
        mRatingRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager4
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRatingRecyclerView.setLayoutManager(layoutManager4);
        RecyclerView.Adapter mRatingAdapter = new ArrivalTimeAdapter(ArrayUtils.getLengthRating());
        mRatingRecyclerView.setAdapter(mRatingAdapter);


    }

    private void initUI() {
        mFilterButton = (ImageView) findViewById(R.id.filterButton);
//        mSearchButton = (ImageView) findViewById(R.id.imageViewsearch);
//        mMapButton = (ImageView) findViewById(R.id.Mapbutton);
//        mProfileButton = (ImageView) findViewById(R.id.Profilebutton);
//        mSearchButton.setOnClickListener(onClick);
//        mMapButton.setOnClickListener(onClick);
//        mProfileButton.setOnClickListener(onClick);
        mFilterButton.setOnClickListener(onClick);
        bubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.filter_layout, null);
        popupWindow = BubblePopupHelper.create(this, bubbleLayout);

    }

    public void updateTitle(String title,boolean filterEnabled) {
        mTextViewTitle.setText(title);
        updateFilter(filterEnabled) ;
    }

    public void updateFilter(boolean filterEnabled){
        if(filterEnabled){
            mFilterButton.setVisibility(View.VISIBLE);
        }else{
            mFilterButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        if (position > -1 && position < 5) {
            displayView(ActivityConstants.SUBCATEGORY, position + 1);
            Log.e("if part ", "fffffffff");
        } else {
            if (position == 9) {
                if (mUserPefer.isLoggedIn()) {
                    mDialogUtils.doubleFunc(this, "Do you want to logout?", "PSS", this, DialogConstants.DIALOG_BUTTON_POSITIVE);
                    return;
                }
                displayView(9, 1002);
                return;
            }
            displayView(position, null);
            Log.e("else part ", "fffffffff");
        }
        // displayView(position, null);
    }

    private void displayView(int position, Object obj) {
        Fragment fragment = null;

        switch (position) {
            case ActivityConstants.HOTELS_FRAGMENT:
                FragmentHotels mFragmentHotels = new FragmentHotels();
                mFragmentHotels.getCategoryID((Integer) obj);
                navigateFragment(mFragmentHotels,false);
                isLog = false;
                break;
            case ActivityConstants.TRAVEL_FRAGMENT:
                FragmentTravels mFragmentTravels = new FragmentTravels();
                mFragmentTravels.getCategoryID((Integer) obj);
                navigateFragment(mFragmentTravels,false);
                isLog = false;
                break;
            case ActivityConstants.RENTING_FRAGMENT:
                fragment = new FragmentRenting();
                navigateFragment(fragment,false);
                isLog = false;

                break;
            case ActivityConstants.INDUSTRIES_FRAGMENT:
                fragment = new FragmentIndustriesEquipments();
                navigateFragment(fragment,false);
                isLog = false;
                break;
            case ActivityConstants.ABOUTUS_FRAGMENT:
                fragment = new FragmentAboutus();
                navigateFragment(fragment,false);
                isLog = false;
                break;
            case ActivityConstants.HELP_FRAGMENT:
                fragment = new FriendsFragment();
                navigateFragment(fragment,false);
                isLog = false;
                break;
            case ActivityConstants.TERMS_FRAGMENT:
                fragment = new FragmentTerms();
                navigateFragment(fragment,false);
                isLog = false;
                break;
            case ActivityConstants.SERVICES_FRAGMENT:
                fragment = new FragmentServices();
                navigateFragment(fragment,false);
                isLog = false;
                break;
            case ActivityConstants.HIRE_FRAGMENT:
                fragment = new FragmentHire();
                //  fragment = new FragmentPost();
                navigateFragment(fragment,false);
                isLog = false;
                break;
            case ActivityConstants.CHAT_FRAGMENT:
                FragmentChat mFragmentChat = new FragmentChat();
                mFragmentChat.getData(obj);
                navigateFragment(mFragmentChat,false);
                isLog = false;
                break;

            case ActivityConstants.TRAVEL_DETAILS_FRAGMENT:
                fragment = new FragmentTravelTransport();
                navigateFragment(fragment,false);
                isLog = false;

                break;
            case ActivityConstants.PROFILE_FRAGMENT: {
                fragment = new FragmentProfile();
                navigateFragment(fragment,true);
                isLog = false;
            }
            break;
            case ActivityConstants.HOTELS_DETAILS_FRAGMENT: {
                FragmentHotelsDetails mFragmentHotelsDetails = new FragmentHotelsDetails();
                mFragmentHotelsDetails.getHotelDetails(obj);
                navigateFragment(mFragmentHotelsDetails,false);
                isLog = false;
            }
            break;
            case ActivityConstants.LOGIN_FRAGMENT: {
                if(!isLog) {
                    FragmentLogin mFragmentLogin = new FragmentLogin();
                    mFragmentLogin.getData(obj);
                    navigateFragment(mFragmentLogin, false);
                }
                isLog = true;
            }
            break;
            case ActivityConstants.LOGINADS_FRAGMENT: {
                FragmentLogin mFragmentLogin = new FragmentLogin();
                mFragmentLogin.getData(obj);
                navigateFragment(mFragmentLogin,false);
                isLog = false;
            }
            break;
            case ActivityConstants.SIGN_UP_FRAGMENT: {
                FragmentSignup mFragmentSignup = new FragmentSignup();
                mFragmentSignup.getProfileData(obj);
                navigateFragment(mFragmentSignup,false);
                isLog = false;
            }
            break;
            case ActivityConstants.FORGOT_PASSWORD: {
                fragment = new FragmentForgotPassword();
                navigateFragment(fragment,false);
                isLog = false;
            }
            break;
            case ActivityConstants.USER_TYPE: {
                fragment = new FragmentUserType();
                navigateFragment(fragment,false);
                isLog = false;
            }
            break;
            case ActivityConstants.BUSSINESS_REGISTER: {
                fragment = new FragmentBussinessRegister();
                navigateFragment(fragment,false);
                isLog = false;
            }
            break;
            case ActivityConstants.POST_CATEGORY: {
                fragment = new FragmentPostCategory();
                navigateFragment(fragment,true);
                isLog = false;

            }
            break;
            case ActivityConstants.POST_SUBCATEGORY: {
                FragmentPostSubCategory mFragmentPostSubCategory = new FragmentPostSubCategory();
                mFragmentPostSubCategory.getCategoryId((String) obj);
                navigateFragment(mFragmentPostSubCategory,false);
                isLog = false;
            }
            break;
            case ActivityConstants.POST_ADD: {
                FragmentPost mFragmentPost = new FragmentPost();
                mFragmentPost.getCategoryDetails((FragmentPostSubCategory.ItemCategoryModel) obj);
                navigateFragment(mFragmentPost,false);
                isLog = false;
            }
            break;
            case ActivityConstants.PROCES_CONFIRM: {
                FragmentProcessConfirm mFragmentPost = new FragmentProcessConfirm();
                mFragmentPost.getData(obj);
                navigateFragment(mFragmentPost,false);
                isLog = false;
            }
            break;
            case ActivityConstants.SUBCATEGORY: {
                FragmentSubCategory mFragmentPost = new FragmentSubCategory();
                mFragmentPost.getcategoryId((Integer) obj);
                navigateFragment(mFragmentPost,true);
                isLog = false;
            }
            break;
            default:
                break;
        }


    }

    private void clearAllFragments(){
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void navigateFragment(Fragment fragment,boolean isClear) {
//        if(isClear){
//            clearAllFragments();
//        }
        if (fragment != null) {
            if (fragment.isVisible()) {
                Log.e("ACTIVE -------> ", "FRAGMENT ACTIVE");
            } else {
                Log.e("Not ACTIVE -------> ", "FRAGMENT Not ACTIVE");

            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);

            if(!fragment.getClass().getName().equals("com.pss.booking.activity.FragmentSubCategory")){

                fragmentTransaction.addToBackStack(fragment.getClass().getName());
           }
            if(isClear){
                fragmentManager.popBackStackImmediate();
            }
            fragmentTransaction.commit();
            // set the toolbar title
        }
    }

    @Override
    public void onChangeListener(int index, Object mObj) {
        displayView(index, mObj);
    }

    @Override
    public void onDialogClick(int selectedIndex, Object mObj, int dialogIndex) {
        if (dialogIndex == DialogConstants.DIALOG_BUTTON_POSITIVE) {
            Log.e("Logout ----->", "wow");


            mUserPefer.logoutUser();
            finish();
        }
    }
}