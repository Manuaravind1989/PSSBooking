package com.pss.booking.model;

import java.util.List;

/**
 * Created by Manu on 1/6/2017.
 */
public class ChatModel {


    /**
     * sucess : 1
     * messages : [{"msg_id":"1","_from":"21","_to":"17","messages":"ghsajefgvkj fgvjkdsfvjksdf vgsdfjvgh vdfjvsdgjsdfhb sdgsdf duigisdfvjhsduifgv","time":"08:09 AM"},{"msg_id":"2","_from":"21","_to":"17","messages":"sdvdfvsf rtgrthtnhrt 5hejhgtrh erehrtgh ","time":"08:09 AM"},{"msg_id":"8","_from":"21","_to":"17","messages":"I AM FINE AND HOW ARE YOU","time":"08:09 AM"},{"msg_id":"13","_from":"17","_to":"21","messages":"hi\n","time":"08:17 AM"},{"msg_id":"14","_from":"21","_to":"17","messages":"hi\n","time":"08:17 AM"},{"msg_id":"15","_from":"21","_to":"17","messages":"how are you?\n","time":"08:17 AM"},{"msg_id":"16","_from":"21","_to":"17","messages":"hello","time":"08:17 AM"},{"msg_id":"17","_from":"21","_to":"17","messages":"gopal","time":"08:17 AM"},{"msg_id":"18","_from":"17","_to":"21","messages":"tttttttttttttttt","time":"08:17 AM"},{"msg_id":"19","_from":"17","_to":"21","messages":"sunil","time":"08:17 AM"},{"msg_id":"20","_from":"21","_to":"17","messages":"sgwlabs","time":"08:17 AM"},{"msg_id":"21","_from":"17","_to":"21","messages":"sgw","time":"08:17 AM"},{"msg_id":"22","_from":"21","_to":"17","messages":"birla","time":"08:17 AM"},{"msg_id":"23","_from":"17","_to":"21","messages":"du3\n","time":"08:17 AM"},{"msg_id":"24","_from":"21","_to":"17","messages":"www","time":"08:17 AM"},{"msg_id":"25","_from":"17","_to":"21","messages":"gggggggggggggg","time":"08:17 AM"},{"msg_id":"26","_from":"21","_to":"17","messages":"keyboard","time":"08:17 AM"},{"msg_id":"27","_from":"21","_to":"17","messages":"medam","time":"08:17 AM"},{"msg_id":"28","_from":"21","_to":"17","messages":"buaji","time":"08:17 AM"},{"msg_id":"29","_from":"17","_to":"21","messages":"kam\n","time":"08:17 AM"},{"msg_id":"30","_from":"21","_to":"17","messages":"majedar","time":"08:17 AM"},{"msg_id":"31","_from":"17","_to":"21","messages":"praty\n","time":"08:17 AM"},{"msg_id":"32","_from":"21","_to":"17","messages":"kab dega?","time":"08:17 AM"},{"msg_id":"33","_from":"17","_to":"21","messages":"???","time":"08:17 AM"},{"msg_id":"34","_from":"21","_to":"17","messages":"sunday ko de de","time":"08:17 AM"},{"msg_id":"35","_from":"17","_to":"21","messages":"nahi yar pese nahi hai\n","time":"08:17 AM"},{"msg_id":"36","_from":"17","_to":"21","messages":"nahi yar pese nahi hai\n","time":"08:17 AM"},{"msg_id":"37","_from":"21","_to":"17","messages":"ye kya hai","time":"08:17 AM"},{"msg_id":"38","_from":"17","_to":"21","messages":"kuch","time":"08:17 AM"},{"msg_id":"39","_from":"21","_to":"17","messages":"tu sahi se reply kar","time":"08:17 AM"},{"msg_id":"40","_from":"17","_to":"21","messages":"kya","time":"08:17 AM"},{"msg_id":"41","_from":"21","_to":"17","messages":"ye sab database me ja raga hai","time":"08:17 AM"},{"msg_id":"42","_from":"17","_to":"21","messages":"pop","time":"08:17 AM"},{"msg_id":"43","_from":"21","_to":"17","messages":"fuc","time":"08:17 AM"},{"msg_id":"44","_from":"21","_to":"17","messages":"gar chale","time":"08:17 AM"},{"msg_id":"45","_from":"17","_to":"21","messages":"chalo bahiya","time":"08:17 AM"},{"msg_id":"46","_from":"21","_to":"17","messages":"ok bye","time":"08:17 AM"}]
     */

    private int sucess;
    private List<MessagesEntity> messages;

    public int getSucess() {
        return sucess;
    }

    public void setSucess(int sucess) {
        this.sucess = sucess;
    }

    public List<MessagesEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesEntity> messages) {
        this.messages = messages;
    }

    public static class MessagesEntity {
        /**
         * msg_id : 1
         * _from : 21
         * _to : 17
         * messages : ghsajefgvkj fgvjkdsfvjksdf vgsdfjvgh vdfjvsdgjsdfhb sdgsdf duigisdfvjhsduifgv
         * time : 08:09 AM
         */

        private String msg_id;
        private String _from;
        private String _to;
        private String messages;
        private String time;

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }

        public String get_from() {
            return _from;
        }

        public void set_from(String _from) {
            this._from = _from;
        }

        public String get_to() {
            return _to;
        }

        public void set_to(String _to) {
            this._to = _to;
        }

        public String getMessages() {
            return messages;
        }

        public void setMessages(String messages) {
            this.messages = messages;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
