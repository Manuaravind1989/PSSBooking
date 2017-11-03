package com.pss.booking.model;

import java.util.List;

/**
 * Created by Manu on 12/11/2016.
 */
public class RentingModel {

    /**
     * success : 1
     * posts : [{"id":"1","category":"3","picture":"default.png","mobile":"094251 87145","email":"paradiseindore@gmail.com ","time_start":"12:00 pm","time_end":"12:00pm","amount":"1990","currency":"$","amenities":"Air-conditioning,Satellite tv,Snack basket,Tea/coffee maker(suite room)","fetures":"On entering this beautiful hotel, one can immediately sense its special intimate atmosphere that makes you feel like being in your own home. The hotel rooms are extremely comfortable, equipped with all modern amenities chosen selectively. This passion for detail contributes to the intimate, tranquil atmosphere which transforms each room into a pleasant refuge where guests can relax after a hectic day. Each room has been individually designed in a fresh concept mixing tradition, variety of fabrics, furniture, prints & paintings.","location":"indore","date_creation":"18-11-2016","date_exp":"19-11-2016","user_id":"8"},{"id":"2","category":"3","picture":"default.png","mobile":"123456","email":"$email","time_start":"$time_start","time_end":"$time_end","amount":"$amount","currency":"$currency","amenities":"$amenities","fetures":"$fetures","location":"$location","date_creation":"$date_create","date_exp":"$date_exp","user_id":"5"}]
     */

    private int success;
    /**
     * id : 1
     * category : 3
     * picture : default.png
     * mobile : 094251 87145
     * email : paradiseindore@gmail.com
     * time_start : 12:00 pm
     * time_end : 12:00pm
     * amount : 1990
     * currency : $
     * amenities : Air-conditioning,Satellite tv,Snack basket,Tea/coffee maker(suite room)
     * fetures : On entering this beautiful hotel, one can immediately sense its special intimate atmosphere that makes you feel like being in your own home. The hotel rooms are extremely comfortable, equipped with all modern amenities chosen selectively. This passion for detail contributes to the intimate, tranquil atmosphere which transforms each room into a pleasant refuge where guests can relax after a hectic day. Each room has been individually designed in a fresh concept mixing tradition, variety of fabrics, furniture, prints & paintings.
     * location : indore
     * date_creation : 18-11-2016
     * date_exp : 19-11-2016
     * user_id : 8
     */

    private List<PostsEntity> posts;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }

    public int getSuccess() {
        return success;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public static class PostsEntity {
        private String id;
        private String category;
        private String picture;
        private String mobile;
        private String email;
        private String time_start;
        private String time_end;
        private String amount;
        private String currency;
        private String amenities;
        private String fetures;
        private String location;
        private String date_creation;
        private String date_exp;
        private String user_id;

        public void setId(String id) {
            this.id = id;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setAmenities(String amenities) {
            this.amenities = amenities;
        }

        public void setFetures(String fetures) {
            this.fetures = fetures;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setDate_creation(String date_creation) {
            this.date_creation = date_creation;
        }

        public void setDate_exp(String date_exp) {
            this.date_exp = date_exp;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getId() {
            return id;
        }

        public String getCategory() {
            return category;
        }

        public String getPicture() {
            return picture;
        }

        public String getMobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }

        public String getTime_start() {
            return time_start;
        }

        public String getTime_end() {
            return time_end;
        }

        public String getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        public String getAmenities() {
            return amenities;
        }

        public String getFetures() {
            return fetures;
        }

        public String getLocation() {
            return location;
        }

        public String getDate_creation() {
            return date_creation;
        }

        public String getDate_exp() {
            return date_exp;
        }

        public String getUser_id() {
            return user_id;
        }
    }
}
