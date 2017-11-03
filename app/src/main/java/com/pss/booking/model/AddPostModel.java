package com.pss.booking.model;

/**
 * Created by Manu on 12/11/2016.
 */
public class AddPostModel {

    /**
     * success : 1
     * msg :  Post successfully Added
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
