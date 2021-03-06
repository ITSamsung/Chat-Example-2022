package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.MessageD;

import java.util.Date;

public class Message extends MessageD {
    public String id;
    public User from;
    public User to;
    public Date creationDate;

    public Message(MessageD messageD, User from, User to, String id) {
        this.creationDate = new Date(messageD.createDate);
        this.createDate = messageD.createDate;
        this.from = from;
        this.to = to;
        this.id = id;

        text = messageD.text;
        isRead = messageD.isRead;
        isSent = messageD.isSent;
        fromID = messageD.fromID;
    }
}
