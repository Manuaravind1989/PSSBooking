package com.pss.booking.activity;

/**
 * Created by Ravi on 29/07/15.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pss.booking.R;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.adapter.NavigationDrawerAdapter;
import com.pss.booking.constants.ActivityConstants;
import com.pss.booking.model.NavDrawerItem;

import java.util.ArrayList;


public class FragmentDrawer extends Fragment implements View.OnClickListener {
    MainActivity mActivity;
    private Button mPostAddButton;
  //  private static String[] titles = null;
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private FragmentDrawerListener drawerListener;
    private ArrayList<NavDrawerItem> itemList;
    int positionIndex=0;
    UserPefer mUserPefer;
    ImageView mImageView;
    public FragmentDrawer() {

    }

//    public static List<NavDrawerItem> getData() {
//        List<NavDrawerItem> data = new ArrayList<>();
//
//
//        // preparing navigation drawer items
//        for (int i = 0; i < titles.length; i++) {
//            NavDrawerItem navItem = new NavDrawerItem();
//            navItem.setTitle(titles[i]);
//            data.add(navItem);
//        }
//        return data;
//    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareArrayLits();
        // drawer labels
       // titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
        mActivity = (MainActivity) getActivity();
        mUserPefer = new UserPefer(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mImageView = (ImageView)layout.findViewById(R.id.profile);
//        if(mUserPefer.getUserId()!=null){
//            Picasso.with(mActivity)
//                    .load(mUserPefer.getLogo_url())
//                    .placeholder(R.drawable.profile) // optional
//                    .error(R.drawable.profile)         // optional
//                    .into(mImageProfile);
//        }

        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        mPostAddButton = (Button)layout.findViewById(R.id.PodtAddButton);
        mPostAddButton.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        adapter = new NavigationDrawerAdapter(getActivity(), itemList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
                for(int i=0; i<itemList.size();i++){
                    itemList.get(i).setShowNotify(false);
                }
               itemList.get(position).setShowNotify(true);
                adapter.notifyItemRangeChanged(0,itemList.size());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return layout;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar,int index) {
        positionIndex = index;
        Log.e("New position ---->  " ,""+index);
        for(int i=0; i<itemList.size();i++){
            itemList.get(i).setShowNotify(false);
        }
        itemList.get(positionIndex).setShowNotify(true);
        Log.e("positionIndex ===>  ", ""+positionIndex);
        adapter.notifyItemRangeChanged(0, itemList.size());
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.PodtAddButton){
            int ActivityIndex = ActivityConstants.LOGIN_FRAGMENT;
            if(mUserPefer.getUserId()==null){
                ActivityIndex= ActivityConstants.LOGIN_FRAGMENT;

            }else{
               if( mUserPefer.getUserType().equals("private")){
                   Toast.makeText(mActivity,"Private user cannot add the post",Toast.LENGTH_SHORT).show();
                   return;
               }else {
                   ActivityIndex = ActivityConstants.POST_CATEGORY;
               }

            }

            drawerListener.onDrawerItemSelected(view,ActivityIndex);
            mDrawerLayout.closeDrawer(containerView);
        }
        else if(view.getId()==R.id.profile){
            drawerListener.onDrawerItemSelected(view, ActivityConstants.PROFILE_FRAGMENT);
            mDrawerLayout.closeDrawer(containerView);
        }

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }


    public void prepareArrayLits() {
        itemList = new ArrayList<NavDrawerItem>();

      //  AddObjectToList(R.drawable.icn_user_inactive_icon, R.drawable.icn_user_active_icon,"MY PROFILE",false);
        AddObjectToList(R.drawable.icn_hotel_inactive_icon,R.drawable.icn_hotel_active_icon, "HOTELS",false);
        AddObjectToList(R.drawable.icn_car_inactive_icon,R.drawable.icn_car_active_icon, "TRAVEL & TRANSPORT",false);
        AddObjectToList(R.drawable.icn_renting_inactive_icon,R.drawable.icn_renting_active_icon, "PRIVATE RENTING",false);
        AddObjectToList(R.drawable.icn_setting_inactive,R.drawable.icn_setting_active, "INDUSTRIES & EQUPMENT",false);
        AddObjectToList(R.drawable.icn_hire_inactive,R.drawable.icn_hire_active, "HIRE",false);
        AddObjectToList(R.drawable.icn_services_inactive,R.drawable.icn_services_active, "ABOUT US",false);
        AddObjectToList(R.drawable.icn_help_inactive,R.drawable.icn_help_active, "HELP",false);
        AddObjectToList(R.drawable.icn_condition_inactive, R.drawable.icn_condition_active,"TERMS & CONDITIONS",false);
        AddObjectToList(R.drawable.icn_services_inactive,R.drawable.icn_services_active, "SERVICES",false);
        AddObjectToList(R.drawable.icn_services_inactive,R.drawable.icn_services_active, "LOGIN",false);
       // AddObjectToList(R.drawable.icn_services_inactive,R.drawable.icn_services_active, "CHAT",false);


    }

    // Add one item into the Array List
    public void AddObjectToList(int image,int imageHighlight, String title, boolean isHighlight) {
        NavDrawerItem bean = new NavDrawerItem();

        bean.setImage(image);
        bean.setImageHighlight(imageHighlight);
        bean.setTitle(title);
        bean.setShowNotify(isHighlight);

        itemList.add(bean);
    }
}
