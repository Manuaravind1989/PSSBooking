package com.pss.booking.httpManager;

/**
 * Created by mdev3 on 10/17/16.
 */
public interface RequestType {
    String LOGIN_SERVICE = "login";
    String REGISTER_SERVICE = "register";
    String BUSINESS_REGISTER_SERVICE = "businessregister";
    String FORGOT_PASSWORD = "forgot";
    String SUBCATEGORY_TYPE = "subcategory";
    String HOTEL_TYPE = "hotel";
    String TRAVEL_TYPE = "travel";
    String RENTING_TYPE= "renting";
    String INDUSTRIES_TYPE = "industries";
    String HIRE_TYPE = "hire";
    String USER_PROFILE = "userprofile";
    String EDIT_PROFILE = "editprofile";
    String ADD_POST = "addpost";
    String ADD_BOOKING = "addBooking";
    String SEND_CHATMESSAGE = "sendChat";
    String CHAT_BETWEEN = "chatbetween";
    String PUSH_NOTIFICATION ="fcmNotification";
    String LIST_NOTIFICATION = "listNotification";
    String MY_BOOKING_LIST = "mybookingList";
}
