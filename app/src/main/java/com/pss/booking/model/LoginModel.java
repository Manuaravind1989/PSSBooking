package com.pss.booking.model;

/**
 * Created by mdev3 on 10/17/16.
 */
public class LoginModel {


    /**
     * success : 1
     * user_id : 26
     * email : manuaravindklr@gmail.com
     * user_type : business
     */

    private int success;
    private String user_id;
    private String email;
    private String user_type;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public int getSuccess() {
        return success;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_type() {
        return user_type;
    }
}
