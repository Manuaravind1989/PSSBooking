package com.pss.booking.httpManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.pss.booking.Utils.NetworkUtils;
import com.pss.booking.Utils.VolleySingleton;
import com.pss.booking.constants.HttpConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mdev3 on 10/17/16.
 */
public class NetworkManager {
    private ParseJson parseJson;
    Activity context;
    private ProgressDialog progressdialog;
    private ServiceResponseListener mServiceResponseListener;
    RequestQueue requestQueue;
    private String requestType = "";
    NetworkUtils mNetworkUtils;

    public NetworkManager(Activity context, ServiceResponseListener mServiceResponseListener) {
        this.context = context;
        this.mServiceResponseListener = mServiceResponseListener;
        parseJson = new ParseJson(context);
        mNetworkUtils = new NetworkUtils(context);
        requestQueue = VolleySingleton.getInstance().getRequestQueue();

        progressdialog = new ProgressDialog(context);
        progressdialog.setMessage("Loading...");
        progressdialog.setCancelable(false);
        progressdialog.setIndeterminate(true);
    }
////    public void unpublishodule(String uniquename, String AppID) {
//    requestType = RequestType.UNPUBLISH;
//    String urlRequest = HttpConstants.UNPUBLISH_URL;
//    urlRequest += "UniqueName=" + uniquename;
//    urlRequest += "Lang=" + App.getLANG_EN_AR();
//    urlRequest += "AppID=" + AppID;
//    jsonObjectVolleyArr(urlRequest, null, Request.Method.GET);

    // http://pssbooking.com/users/registration.php?user_type=private&first_name=mohit&last_name=jonwal&email=mohitjonwal9@gmail.com&password=12345&mobile=0123456789
    public void setRegistration(String firstname,
                                String lastname, String email,
                                String password, String mobNumber,
                                String location) {
        requestType = RequestType.REGISTER_SERVICE;
        String mRequestUrl = HttpConstants.REGISTRATION_URL;

        mRequestUrl += "&first_name=" + firstname;
        mRequestUrl += "&last_name=" + lastname;
        mRequestUrl += "&email=" + email;
        mRequestUrl += "&password=" + password;
        mRequestUrl += "&mobile=" + mobNumber;
        mRequestUrl += "&location=" + location;
        jsonObjectVolleyArr(mRequestUrl);
    }
//http://pssbooking.com/apps/users/business_registration.php?
// user_type=business&
// name=qwerty&
// email=jhhhgfhgfhf@kjhkjh.lklk&
// password=12345&
// mobile=0123456789&
// landline=0731-0731&
// location=indore&
// company_name=pss

    public void setBussinessRegistration(String usertype, String firstname,
                                         String lastname, String email,
                                         String password, String mobNumber, String landline, String location,
                                         String Compy, String description) {
        requestType = RequestType.BUSINESS_REGISTER_SERVICE;
        String mRequestUrl = HttpConstants.BUSSINESS_REGISTER;
        mRequestUrl += "user_type=" + usertype;
        mRequestUrl += "&first_name=" + firstname;
        mRequestUrl += "&last_name=" + lastname;
        mRequestUrl += "&company_name=" + Compy;
        mRequestUrl += "&description=" + description;
        mRequestUrl += "&email=" + email;
        mRequestUrl += "&password=" + password;
        mRequestUrl += "&mobile=" + mobNumber;
        mRequestUrl += "&landline=" + landline;
        mRequestUrl += "&location=" + location;
        jsonObjectVolleyArr(mRequestUrl);
    }


    public void Login(String email, String password, String device_id) {
        requestType = RequestType.LOGIN_SERVICE;
        String mRequestUrl = HttpConstants.LOGIN_API;
        mRequestUrl += "email=" + email;
        mRequestUrl += "&password=" + password;
        mRequestUrl += "&device_id=" + device_id;
        jsonObjectVolleyArr(mRequestUrl);
    }

    public void forgotPassword(String email) {
        requestType = RequestType.FORGOT_PASSWORD;
        String mRequestUrl = HttpConstants.FORGOT_API;
        mRequestUrl += "email=" + email;
        jsonObjectVolleyArr(mRequestUrl);
    }

    public void getHotels(String categoryId) {
        requestType = RequestType.HOTEL_TYPE;
        String mRequestUrl = HttpConstants.CATEGORY_API;
        mRequestUrl += "id=" + categoryId;
        jsonObjectVolleyArr(mRequestUrl);
    }

    public void getTravel(String categoryId) {
        requestType = RequestType.TRAVEL_TYPE;
        String mRequestUrl = HttpConstants.CATEGORY_API;
        mRequestUrl += "id=" + categoryId;
        jsonObjectVolleyArr(mRequestUrl);
    }

    public void getRenting(String categoryId) {
        requestType = RequestType.RENTING_TYPE;
        String mRequestUrl = HttpConstants.CATEGORY_API;
        mRequestUrl += "id=" + categoryId;
        jsonObjectVolleyArr(mRequestUrl);
    }

    public void getIndustries(String categoryId) {
        requestType = RequestType.INDUSTRIES_TYPE;
        String mRequestUrl = HttpConstants.CATEGORY_API;
        mRequestUrl += "id=" + categoryId;
        jsonObjectVolleyArr(mRequestUrl);
    }

    public void userProfile(String user_id) {
        requestType = RequestType.USER_PROFILE;
        String mRequestUrl = HttpConstants.USER_PROFILE;
        mRequestUrl += "user_id=" + user_id;
        jsonObjectVolleyArr(mRequestUrl);
    }


    public void registerPushNotification(String token) {
        requestType = RequestType.PUSH_NOTIFICATION;
        String mRequestUrl = HttpConstants.PUSH_NOTIFICATION;
        mRequestUrl += "token=" + token;
        jsonObjectVolleyArr(mRequestUrl);
    }

    public void ListNotification() {
        requestType = RequestType.LIST_NOTIFICATION;
        String mRequestUrl = HttpConstants.LIST_NOTIFICATION;

        jsonObjectVolleyArr(mRequestUrl);
    }
    public void myBookingList(String user_id) {
        requestType = RequestType.MY_BOOKING_LIST;
        String mRequestUrl = HttpConstants.MY_BOOKING_LIST;
        mRequestUrl += "user_id=" + user_id;
        jsonObjectVolleyArr(mRequestUrl);
    }
    public void sendingChat(String user_id, String to, String message) {
        requestType = RequestType.SEND_CHATMESSAGE;
        String mRequestUrl = HttpConstants.SEND_CHATMESSAGE;
        mRequestUrl += "user_id=" + user_id;
        mRequestUrl += "&to=" + to;
        mRequestUrl += "&message=" + message;
        jsonObjectVolleyArrOnPregress(mRequestUrl);
    }

    public void chatBetweenTwo(String user_id, String request_id) {
        requestType = RequestType.CHAT_BETWEEN;
        String mRequestUrl = HttpConstants.CHAT_BETWEEN;
        mRequestUrl += "user_id=" + user_id;
        mRequestUrl += "&request_id=" + request_id;

        jsonObjectVolleyArrOnPregress(mRequestUrl);
    }

    private String encodeUTF(String query) {
        String mQuery = "";
        try {
            mQuery = URLEncoder.encode(query, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mQuery;
    }

    public void addBooking(String userid, String amount, String discount, String username, String mobile, String serviceTime, String datefrom, String dateto) {
        requestType = RequestType.ADD_BOOKING;
        String mRequestUrl = HttpConstants.ADD_BOOKING;
        mRequestUrl += "user_id=" + userid;
        mRequestUrl += "&item_id=" + "2";
        mRequestUrl += "&amount=" + amount;
        mRequestUrl += "&amount_paid=" + "600";
        mRequestUrl += "&transaction_id=" + "1002";
        mRequestUrl += "&transaction_status=" + "sucess";
        mRequestUrl += "&discount=" + discount;
        mRequestUrl += "&margine=" + "10";
        mRequestUrl += "&end_user_name=" + encodeUTF(username);
        mRequestUrl += "&mobile=" + encodeUTF(mobile);
        mRequestUrl += "&service_time=" + encodeUTF(serviceTime);
        mRequestUrl += "&date_from=" + encodeUTF(datefrom);
        mRequestUrl += "&date_to=" + encodeUTF(dateto);
        mRequestUrl += "&remark=" + "5";

        jsonArrayVolleyArray(mRequestUrl);
    }

    public void getSubCategory(String categoryId) {
        requestType = RequestType.SUBCATEGORY_TYPE;
        String mRequestUrl = HttpConstants.CATEGORY_API;
        mRequestUrl += "id=" + categoryId;
        jsonObjectVolleyArr(mRequestUrl);
    }


    private void jsonObjectVolleyArrOnPregress(String url) {
        if (mNetworkUtils.isConnectingToInternet()) {
            // progressdialog.show();
            Log.e("URL++++===>", "" + url);
            // RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // handle response
                            Log.e("Response ", "" + response.toString());
                            Object obj = parseJson.parseJsonData(requestType, response.toString());
                            mServiceResponseListener.onSuccessResponse(obj, requestType);
                            // progressdialog.dismiss();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley00000000y errror ", "" + error.toString());

                    // progressdialog.dismiss();
                    //if (error.networkResponse.statusCode == 401) {
                    return;
                    //}
                }
            }) {
            };
// add the request object to the queue to be executed
            requestQueue.add(req);
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void jsonObjectVolleyArr(String url) {
        if (mNetworkUtils.isConnectingToInternet()) {
            progressdialog.show();
            Log.e("URL++++===>", "" + url);
            // RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // handle response
                            Log.e("Response ", "" + response.toString());
                            Object obj = parseJson.parseJsonData(requestType, response.toString());
                            mServiceResponseListener.onSuccessResponse(obj, requestType);
                            progressdialog.dismiss();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley00000000y errror ", "" + error.toString());

                    progressdialog.dismiss();
                    //if (error.networkResponse.statusCode == 401) {
                    return;
                    //}
                }
            }) {
            };
// add the request object to the queue to be executed
            requestQueue.add(req);
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void jsonArrayVolleyArray(String url) {
        if (mNetworkUtils.isConnectingToInternet()) {
            progressdialog.show();
            Log.e("URL++++===>", "" + url);
            // RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // handle response
                            Log.e("Response ", "" + response.toString());
                            Object obj = parseJson.parseJsonData(requestType, response.toString());
                            mServiceResponseListener.onSuccessResponse(obj, requestType);
                            progressdialog.dismiss();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley00000000y errror ", "" + error.toString());

                    progressdialog.dismiss();
                    //if (error.networkResponse.statusCode == 401) {
                    return;
                    //}
                }
            }) {
            };
// add the request object to the queue to be executed
            requestQueue.add(req);
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUser(final String user_id, final String firstname, final String lastname, final String emailid, final String mobile) {
        if (mNetworkUtils.isConnectingToInternet()) {
            requestType = RequestType.EDIT_PROFILE;

            progressdialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpConstants.EDIT_PROFILE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Response === >", response);
                            Object obj = parseJson.parseJsonData(requestType, response.toString());
                            mServiceResponseListener.onSuccessResponse(obj, requestType);
                            progressdialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressdialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", user_id);
                    params.put("first_name", firstname);
                    params.put("last_name", lastname);
                    params.put("email", emailid);
                    params.put("mobile", mobile);
                    return params;
                }

            };

            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }


    public void addPost(final HashMap<String, String> mHashMap) {
        if (mNetworkUtils.isConnectingToInternet()) {
            requestType = RequestType.ADD_POST;

            progressdialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpConstants.ADD_POST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Response === >", response);
                            Object obj = parseJson.parseJsonData(requestType, response.toString());
                            mServiceResponseListener.onSuccessResponse(obj, requestType);
                            progressdialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressdialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", mHashMap.get("user_id"));
                    params.put("cat_id", mHashMap.get("cat_id"));
                    params.put("subcat_id", mHashMap.get("subcat_id"));
                    params.put("time_start", mHashMap.get("time_start"));
                    params.put("time_end", mHashMap.get("time_end"));
                    params.put("amenities", mHashMap.get("amenities"));
                    params.put("fetures", mHashMap.get("fetures"));
                    params.put("location", mHashMap.get("location"));
                    params.put("amount", mHashMap.get("amount"));
                    params.put("currency", mHashMap.get("currency"));
                    params.put("email", mHashMap.get("email"));
                    params.put("mobile", mHashMap.get("mobile"));
                    params.put("date_create", mHashMap.get("date_create"));
                    params.put("date_exp", mHashMap.get("user_id"));
                    params.put("title", mHashMap.get("title"));
                    params.put("sub_title", mHashMap.get("sub_title"));
                    params.put("description", mHashMap.get("description"));
                    params.put("count", mHashMap.get("count"));
                    params.put("discount", mHashMap.get("discount"));
                    params.put("lat", mHashMap.get("lat"));
                    params.put("long", mHashMap.get("long"));
                    params.put("imgname", mHashMap.get("imgname"));
                    params.put("image", mHashMap.get("image"));
                    return params;
                }

            };

            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

}

