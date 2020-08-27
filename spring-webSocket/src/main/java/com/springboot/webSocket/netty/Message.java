package com.springboot.webSocket.netty;

import lombok.Data;

/**
 * @author :  yanwenhui
 * @date : 2020/7/6
 */
@Data
public class Message {

    private User fromUser;
    private User toUser;
    private String message;

    // 群发0，单发1
    private int type;

    private MessageBuild messageBuild = null;

    public static MessageBuild builder() {
        return new MessageBuild();
    }

    public static class MessageBuild {
        private User fromUser;
        private User toUser;
        private String message;

        // 群发0，单发1
        private int type = 1;

        public MessageBuild withFromUser(User fromUser) {
            this.fromUser = fromUser;
            return this;
        }

        public MessageBuild withToUser(User toUser) {
            this.toUser = toUser;
            return this;
        }

        public MessageBuild withMessage(String message) {
            this.message = message;
            return this;
        }

        public MessageBuild withType(int type) {
            this.type = type;
            return this;
        }

        public Message build() {
            Message m = new Message();
            m.setFromUser(this.fromUser);
            m.setToUser(this.toUser);
            m.setMessage(this.message);
            m.setType(this.type);
            return m;
        }
    }
}
