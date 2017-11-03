package com.pss.booking.httpManager;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pss.booking.R;
import com.pss.booking.Utils.DialogUtils;
import com.pss.booking.model.AddBookingModel;
import com.pss.booking.model.AddPostModel;
import com.pss.booking.model.ChatModel;
import com.pss.booking.model.ForgotModel;
import com.pss.booking.model.HotelModel;
import com.pss.booking.model.IndustriesModel;
import com.pss.booking.model.LoginModel;
import com.pss.booking.model.MyBookingModel;
import com.pss.booking.model.NotificationModel;
import com.pss.booking.model.RegisterModel;
import com.pss.booking.model.SubCategoryModel;
import com.pss.booking.model.TravelModel;
import com.pss.booking.model.UpdateProfileModel;
import com.pss.booking.model.UserProfileModel;


public class ParseJson {
    private Activity activity;

    public ParseJson(Activity activity) {
        this.activity = activity;
    }

    public Object parseJsonData(String requestType, String response) {
        Log.e("Request Type: ", requestType);
        Log.e("Parsing Json: ", response);

        Gson gson = new GsonBuilder().create();
        if (requestType.equals(RequestType.REGISTER_SERVICE)) {
            RegisterModel vModel = gson.fromJson(response, RegisterModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.BUSINESS_REGISTER_SERVICE)) {
            RegisterModel vModel = gson.fromJson(response, RegisterModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.LOGIN_SERVICE)) {
            LoginModel vModel = gson.fromJson(response, LoginModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.FORGOT_PASSWORD)) {
            ForgotModel vModel = gson.fromJson(response, ForgotModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.SUBCATEGORY_TYPE)) {
            response = response.replace("sub-category", "sub_category");
            response = response.replace("category-id", "category_id");
            SubCategoryModel vModel = gson.fromJson(response, SubCategoryModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.HOTEL_TYPE)) {
            response = response.replace("sub-category", "sub_category");
            response = response.replace("category-id", "category_id");
            HotelModel vModel = gson.fromJson(response, HotelModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.TRAVEL_TYPE)) {
            response = response.replace("sub-category", "sub_category");
            response = response.replace("category-id", "category_id");
            TravelModel vModel = gson.fromJson(response, TravelModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.INDUSTRIES_TYPE)) {
            response = response.replace("sub-category", "sub_category");
            response = response.replace("category-id", "category_id");
            IndustriesModel vModel = gson.fromJson(response, IndustriesModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }


        if (requestType.equals(RequestType.HIRE_TYPE)) {
            response = response.replace("sub-category", "sub_category");
            response = response.replace("category-id", "category_id");
            IndustriesModel vModel = gson.fromJson(response, IndustriesModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }


        if (requestType.equals(RequestType.USER_PROFILE)) {
            UserProfileModel vModel = gson.fromJson(response, UserProfileModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }


        if (requestType.equals(RequestType.EDIT_PROFILE)) {
            UpdateProfileModel vModel = gson.fromJson(response, UpdateProfileModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.ADD_POST)) {
            AddPostModel vModel = gson.fromJson(response, AddPostModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }

        if (requestType.equals(RequestType.ADD_BOOKING)) {
            AddBookingModel[] vModel = gson.fromJson(response, AddBookingModel[].class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.SEND_CHATMESSAGE)) {


            return "";
        }
        if (requestType.equals(RequestType.CHAT_BETWEEN)) {

            ChatModel vModel = gson.fromJson(response, ChatModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }

        if (requestType.equals(RequestType.LIST_NOTIFICATION)) {

            NotificationModel vModel = gson.fromJson(response, NotificationModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }
        if (requestType.equals(RequestType.MY_BOOKING_LIST)) {

            MyBookingModel vModel = gson.fromJson(response, MyBookingModel.class);
            boolean isValid = isValidResponse(vModel);
            return (isValid) ? vModel : null;
        }

        return null;
    }

    private boolean isValidResponseGeneral(Object responseObject) {
        if (responseObject == null) {
            DialogUtils.showAlert(activity, activity.getResources().getString(R.string.tryAgain));
            return false;
        }
        return true;
    }

    private boolean isValidResponse(Object responseState) {
        return isValidResponse(responseState, true);
    }

    private boolean isValidResponse(Object responseState, boolean toPrompt) {
        if ((responseState == null) && toPrompt) {
            DialogUtils.showAlert(activity, activity.getResources().getString(R.string.tryAgain));
            return false;
        }

//        if ((responseState.getStatus() == 0 || responseState.getStatus() == 1) && toPrompt) {
//            DialogUtils.showAlert(activity, (String) responseState.getMessage());
//            return false;
//        }
//       else if (responseState.getStatus() == 2) {
//            return true;
//        } else if (responseState.getStatus() == 3 && toPrompt) {
//            DialogUtils.showAlert(activity, activity.getResources().getString(R.string.noDatafound));
//            return false;
//        }

        return true;
    }


}
