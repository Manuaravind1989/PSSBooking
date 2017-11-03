package com.pss.booking.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by mdev3 on 1/25/17.
 */

public class NotificationModel {


    /**
     * success : 1
     * messages : [{"id":"1","message":"hi"},{"id":"2","message":"hi"},{"id":"3","message":"hi"},{"id":"4","message":"hi"},{"id":"5","message":"hi how are you"},{"id":"6","message":"hi"}]
     */

    private int success;
    /**
     * id : 1
     * message : hi
     */

    private List<MessagesEntity> messages;

    public static NotificationModel objectFromData(String str) {

        return new Gson().fromJson(str, NotificationModel.class);
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<MessagesEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesEntity> messages) {
        this.messages = messages;
    }

    public static class MessagesEntity {
        private String id;
        private String message;

        public static MessagesEntity objectFromData(String str) {

            return new Gson().fromJson(str, MessagesEntity.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
