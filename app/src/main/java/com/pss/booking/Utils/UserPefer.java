package com.pss.booking.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Manu on 12/9/2016.
 */
public class UserPefer {

    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "PSS";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_FIRST_NAME = "first_name";
    // User name (make variable public to access from outside)
    public static final String KEY_LAST_NAME = "last_name";
    // User name (make variable public to access from outside)
    public static final String KEY_USER_ID = "user_id";
    // User name (make variable public to access from outside)
    public static final String KEY_USER_TYPE = "user_type";
    // User name (make variable public to access from outside)
    public static final String KEY_LOG_URL = "logo_url";
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";



    // Constructor
    public UserPefer(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String userId, String email,String UserType ){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
Log.e("USER ID+++++++>",""+userId);
        // Storing name in pref
        editor.putString(KEY_USER_ID, userId);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_USER_TYPE, UserType);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){


    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_USER_TYPE, pref.getString(KEY_USER_TYPE, null));
        // return user
        return user;
    }

    public String getUserId(){
       return pref.getString(KEY_USER_ID, null);
    }
    public String getUserType(){
        return pref.getString(KEY_USER_TYPE, null);
    }


    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
