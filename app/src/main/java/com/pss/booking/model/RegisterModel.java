package com.pss.booking.model;

import com.google.gson.Gson;

/**
 * Created by mdev3 on 10/17/16.
 */
public class RegisterModel {
    /**
     * success : 0
     * msg : The email is already registered, please login with your email.
     */

    private int success;
    private String msg;

    public static RegisterModel objectFromData(String str) {

        return new Gson().fromJson(str, RegisterModel.class);
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * success : 1
     * msg : Registration successful, please check your mail for activate your account.
     */

//    private int success;
//    private String msg;
//
//    public static RegisterModel objectFromData(String str) {
//
//        return new Gson().fromJson(str, RegisterModel.class);
//    }
//
//    public int getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(int success) {
//        this.success = success;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }




}
