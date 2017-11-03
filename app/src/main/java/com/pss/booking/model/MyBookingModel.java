package com.pss.booking.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by mdev3 on 1/25/17.
 */

public class MyBookingModel {

    /**
     * success : 1
     * booking_details : [{"id":"2","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"","mobile":"","service_time":"23:36","date_from":"","date_to ":"","date_to":""},{"id":"3","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"thh","mobile":"1234588","service_time":"23:36","date_from":"24","date_to ":"","date_to":""},{"id":"4","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"rty","mobile":"2585858","service_time":"23:44","date_from":"10 December 2016","date_to ":"9 June 2017","date_to":"9 June 2017"},{"id":"5","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"tnyby","mobile":"2355688","service_time":"23:54","date_from":"8 December 2016","date_to ":"16 December 2016","date_to":"16 December 2016"},{"id":"6","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"rggb","mobile":"69699","service_time":"00:01","date_from":"25 December 2016","date_to ":"25 December 2016","date_to":"25 December 2016"},{"id":"7","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"dubai","mobile":"","service_time":"","date_from":"","date_to ":"","date_to":""},{"id":"8","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"dubai","mobile":"","service_time":"","date_from":"","date_to ":"","date_to":""},{"id":"9","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"dubai","mobile":"","service_time":"","date_from":"","date_to ":"","date_to":""},{"id":"10","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"dubai","mobile":"","service_time":"","date_from":"","date_to ":"","date_to":""},{"id":"11","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"dubai","mobile":"","service_time":"","date_from":"","date_to ":"","date_to":""},{"id":"12","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"dubai","mobile":"","service_time":"","date_from":"","date_to ":"","date_to":""},{"id":"13","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"thyb","mobile":"26889","service_time":"00:16","date_from":"25 December 2016","date_to ":"25 December 2016","date_to":"25 December 2016"},{"id":"14","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"","mobile":"","service_time":"00:17","date_from":"","date_to ":"","date_to":""},{"id":"15","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"","mobile":"","service_time":"00:17","date_from":"","date_to ":"","date_to":""},{"id":"16","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"","mobile":"","service_time":"00:17","date_from":"","date_to ":"","date_to":""},{"id":"17","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"byyb","mobile":"95959","service_time":"00:17","date_from":"23 December 2016","date_to ":"26 May 2017","date_to":"26 May 2017"},{"id":"18","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"byyb","mobile":"95959","service_time":"00:17","date_from":"23 December 2016","date_to ":"26 May 2017","date_to":"26 May 2017"},{"id":"19","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"byyb","mobile":"95959","service_time":"00:17","date_from":"23 December 2016","date_to ":"26 May 2017","date_to":"26 May 2017"},{"id":"20","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"byyb","mobile":"95959","service_time":"00:17","date_from":"23 December 2016","date_to ":"26 May 2017","date_to":"26 May 2017"},{"id":"21","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"byyb","mobile":"95959","service_time":"00:17","date_from":"23 December 2016","date_to ":"26 May 2017","date_to":"26 May 2017"},{"id":"23","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"byyb","mobile":"95959","service_time":"00:17","date_from":"23 December 2016","date_to ":"26 May 2017","date_to":"26 May 2017"},{"id":"24","user_id":"25","item_id":"2","amount":"500","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"20","margine":"10","end_user_name":"","mobile":"","service_time":"00:32","date_from":"","date_to ":"","date_to":""},{"id":"25","user_id":"25","item_id":"2","amount":"500","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"20","margine":"10","end_user_name":"","mobile":"","service_time":"00:32","date_from":"","date_to ":"","date_to":""},{"id":"26","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":" h  h ","mobile":"669","service_time":"00:37","date_from":"25 December 2016","date_to ":"25 December 2016","date_to":"25 December 2016"},{"id":"32","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"ugjj","mobile":"655996","service_time":"16:43","date_from":"January 13, 2017","date_to ":"January 13, 2017","date_to":"January 13, 2017"},{"id":"33","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"y6h7h","mobile":"366969","service_time":"16:45","date_from":"January 27, 2017","date_to ":"January 27, 2017","date_to":"January 27, 2017"},{"id":"34","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"y6h7h","mobile":"366969","service_time":"16:45","date_from":"January 27, 2017","date_to ":"January 13, 2017","date_to":"January 13, 2017"},{"id":"35","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"y6h7h","mobile":"366969","service_time":"16:45","date_from":"January 27, 2017","date_to ":"January 13, 2017","date_to":"January 13, 2017"},{"id":"36","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"y6h7h","mobile":"366969","service_time":"16:45","date_from":"January 27, 2017","date_to ":"November 25, 2017","date_to":"November 25, 2017"},{"id":"37","user_id":"25","item_id":"2","amount":"565","amount_paid":"600","transaction_id":"1002","transaction_status":"sucess","discount":"2","margine":"10","end_user_name":"y6h7h","mobile":"366969","service_time":"16:45","date_from":"January 27, 2017","date_to ":"November 25, 2017","date_to":"November 25, 2017"}]
     */

    private int success;
    /**
     * id : 2
     * user_id : 25
     * item_id : 2
     * amount : 565
     * amount_paid : 600
     * transaction_id : 1002
     * transaction_status : sucess
     * discount : 2
     * margine : 10
     * end_user_name :
     * mobile :
     * service_time : 23:36
     * date_from :
     * date_to  :
     * date_to :
     */

    private List<BookingDetailsEntity> booking_details;

    public static MyBookingModel objectFromData(String str) {

        return new Gson().fromJson(str, MyBookingModel.class);
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<BookingDetailsEntity> getBooking_details() {
        return booking_details;
    }

    public void setBooking_details(List<BookingDetailsEntity> booking_details) {
        this.booking_details = booking_details;
    }

    public static class BookingDetailsEntity {
        private String id;
        private String user_id;
        private String item_id;
        private String amount;
        private String amount_paid;
        private String transaction_id;
        private String transaction_status;
        private String discount;
        private String margine;
        private String end_user_name;
        private String mobile;
        private String service_time;
        private String date_from;

        private String date_to;

        public static BookingDetailsEntity objectFromData(String str) {

            return new Gson().fromJson(str, BookingDetailsEntity.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAmount_paid() {
            return amount_paid;
        }

        public void setAmount_paid(String amount_paid) {
            this.amount_paid = amount_paid;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getTransaction_status() {
            return transaction_status;
        }

        public void setTransaction_status(String transaction_status) {
            this.transaction_status = transaction_status;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getMargine() {
            return margine;
        }

        public void setMargine(String margine) {
            this.margine = margine;
        }

        public String getEnd_user_name() {
            return end_user_name;
        }

        public void setEnd_user_name(String end_user_name) {
            this.end_user_name = end_user_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public String getDate_from() {
            return date_from;
        }

        public void setDate_from(String date_from) {
            this.date_from = date_from;
        }


        public String getDate_to() {
            return date_to;
        }

        public void setDate_to(String date_to) {
            this.date_to = date_to;
        }
    }
}
