package com.pss.booking.model;

/**
 * Created by Manu on 12/9/2016.
 */
public class UserProfileModel {

    /**
     * success : 1
     * user_id : 26
     * user_type : business
     * logo : default.jpg
     * logo_url : http://pssbooking.com/apps/users/uploading/default.jpg
     * first_name : Manu
     * last_name : Aravind
     * company_name : qwerty
     * description : hi
     * email : manuaravindklr@gmail.com
     * mobile : 0123456789
     * landline : 123456
     * location : indore
     */

    private int success;
    private String user_id;
    private String user_type;
    private String logo;
    private String logo_url;
    private String first_name;
    private String last_name;
    private String company_name;
    private String description;
    private String email;
    private String mobile;
    private String landline;
    private String location;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSuccess() {
        return success;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getLogo() {
        return logo;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLandline() {
        return landline;
    }

    public String getLocation() {
        return location;
    }
}
