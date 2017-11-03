package com.pss.booking.model;

import java.util.List;

/**
 * Created by Manu on 12/1/2016.
 */
public class SubCategoryModel {

    /**
     * success : 1
     * posts : [{"id":"10","category":"0","sub_category":"6","picture":"1481435476225","picture_url":"http://pssbooking.com/apps/users/add-post/1481435476225","mobile":"65642","email":"vnch@hdhd.vvh","time_start":"","time_end":"","amount":"565","currency":"","amenities":"hff","fetures":"hdhd","location":"sgdh","date_creation":"","date_exp":"26","user_id":"26"},{"id":"11","category":"0","sub_category":"6","picture":"1481435488372","picture_url":"http://pssbooking.com/apps/users/add-post/1481435488372","mobile":"65642","email":"vnch@hdhd.vvh","time_start":"","time_end":"","amount":"565","currency":"","amenities":"hff","fetures":"hdhd","location":"sgdh","date_creation":"","date_exp":"26","user_id":"26"},{"id":"12","category":"0","sub_category":"6","picture":"1481435513980","picture_url":"http://pssbooking.com/apps/users/add-post/1481435513980","mobile":"65642","email":"vnch@hdhd.vvh","time_start":"","time_end":"","amount":"565","currency":"","amenities":"hff","fetures":"hdhd","location":"sgdh","date_creation":"","date_exp":"26","user_id":"26"},{"id":"13","category":"0","sub_category":"6","picture":"1481435513980","picture_url":"http://pssbooking.com/apps/users/add-post/1481435513980","mobile":"65642","email":"vnch@hdhd.vvh","time_start":"","time_end":"","amount":"565","currency":"","amenities":"hff","fetures":"hdhd","location":"sgdh","date_creation":"","date_exp":"26","user_id":"26"}]
     * sub_categories : [{"id":"6","sub_category":"room","category_id":"1"},{"id":"7","sub_category":"private room","category_id":"1"},{"id":"8","sub_category":"house","category_id":"1"},{"id":"9","sub_category":"apartmaint","category_id":"1"},{"id":"10","sub_category":"car camping","category_id":"1"},{"id":"11","sub_category":"bed and breakfast","category_id":"1"},{"id":"12","sub_category":"guesthouses","category_id":"1"},{"id":"13","sub_category":"villas","category_id":"1"},{"id":"14","sub_category":"vacationns rentals","category_id":"1"},{"id":"28","sub_category":"php","category_id":"1"}]
     */

    private int success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    /**
     * id : 10
     * category : 0
     * sub_category : 6
     * picture : 1481435476225
     * picture_url : http://pssbooking.com/apps/users/add-post/1481435476225
     * mobile : 65642
     * email : vnch@hdhd.vvh
     * time_start :
     * time_end :
     * amount : 565
     * currency :
     * amenities : hff
     * fetures : hdhd
     * location : sgdh
     * date_creation :
     * date_exp : 26
     * user_id : 26
     */

    private List<PostsEntity> posts;
    /**
     * id : 6
     * sub_category : room
     * category_id : 1
     */

    private List<SubCategoriesEntity> sub_categories;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }

    public void setSub_categories(List<SubCategoriesEntity> sub_categories) {
        this.sub_categories = sub_categories;
    }

    public int getSuccess() {
        return success;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public List<SubCategoriesEntity> getSub_categories() {
        return sub_categories;
    }

    public static class PostsEntity {
        private String id;
        private String category;
        private String sub_category;
        private String picture;
        private String picture_url;
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

        public void setSub_category(String sub_category) {
            this.sub_category = sub_category;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
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

        public String getSub_category() {
            return sub_category;
        }

        public String getPicture() {
            return picture;
        }

        public String getPicture_url() {
            return picture_url;
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

    public static class SubCategoriesEntity {
        private String id;
        private String sub_category;
        private String category_id;

        public void setId(String id) {
            this.id = id;
        }

        public void setSub_category(String sub_category) {
            this.sub_category = sub_category;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getId() {
            return id;
        }

        public String getSub_category() {
            return sub_category;
        }

        public String getCategory_id() {
            return category_id;
        }
    }
}
