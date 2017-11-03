package com.pss.booking.model;

/**
 * Created by Manu on 12/24/2016.
 */
public class AddBookingModel {

    /**
     * users : 17
     * user_type : business
     * photo : http://pssbooking.com/apps/users/uploading/Koala.jpg
     * name : qwerty
     * unread : 2
     * time : 08:03 AM
     */

    private String users;
    private String user_type;
    private String photo;
    private String name;
    private String unread;
    private String time;

    public void setUsers(String users) {
        this.users = users;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsers() {
        return users;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getUnread() {
        return unread;
    }

    public String getTime() {
        return time;
    }
}
