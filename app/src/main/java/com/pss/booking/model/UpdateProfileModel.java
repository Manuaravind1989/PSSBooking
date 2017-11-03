package com.pss.booking.model;

/**
 * Created by Manu on 12/9/2016.
 */
public class UpdateProfileModel {

    /**
     * success : 1
     * msg : Profile updated successfully
     */

    private int success;
    private String msg;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }
}
