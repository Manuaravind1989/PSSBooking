package com.pss.booking.model;

import com.google.gson.Gson;

/**
 * Created by mdev3 on 10/19/16.
 */
public class ForgotModel {

    /**
     * success : 0
     * msg : The email is not registered, please register your email
     */

    private int success;
    private String msg;

    public static ForgotModel objectFromData(String str) {

        return new Gson().fromJson(str, ForgotModel.class);
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
}
