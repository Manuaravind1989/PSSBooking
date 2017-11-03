package com.pss.booking.constants;

/**
 * Created by Manu on 9/11/2016.
 */
public interface HttpConstants {
    String BASE_URL = "http://pssbooking.com/apps/users/";
    String REGISTRATION_URL = BASE_URL + "registration.php?";
    String LOGIN_API = "http://pssbooking.com/apps/users/login.php?";
    String FORGOT_API = BASE_URL + "forget_pass.php?";
    String BUSSINESS_REGISTER  = "http://pssbooking.com/apps/users/business_registration.php?";
    String CATEGORY_API= "http://pssbooking.com/apps/users/get_subcat_or_post.php?";
    String USER_PROFILE = "http://pssbooking.com/apps/users/get_user_profile.php?";
    String EDIT_PROFILE = "http://pssbooking.com/apps/users/update_user_profile.php?";
    String ADD_POST = "http://pssbooking.com/apps/users/add_post.php?";
    String ADD_BOOKING = "http://pssbooking.com/apps/users/add_booking.php?";
        //http://pssbooking.com/apps/users/business_registration.php?user_type=business&name=qwerty&email=mohitjonwal9@gmail.com&password=12345&mobile=0123456789&landline=&location=indore
    //http://pssbooking.com/apps/users/business_registration.php?user_type=business&name=qwerty&email=jhhhgfhgfhf@kjhkjh.lklk&password=12345&mobile=0123456789&landline=0731-0731&location=indore&company_name=pss
//http://pssbooking.com/apps/users/business_registration.php?

    String SEND_CHATMESSAGE = "http://pssbooking.com/apps/chat/send_message.php?";
    String CHAT_BETWEEN = "http://pssbooking.com/apps/chat/conver_btw_two.php?";
    String PUSH_NOTIFICATION ="http://pssbooking.com/apps/notification/fcm_reg.php?";//token=12345
    String LIST_NOTIFICATION = "http://pssbooking.com/apps/notification/get_all_push_msg.php";
    String MY_BOOKING_LIST = "http://pssbooking.com/apps/users/get_booking_by_userid.php?";
}
