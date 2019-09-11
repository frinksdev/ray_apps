package com.innomalist.taxi.common.models;

import com.google.gson.annotations.SerializedName;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

public class ChatMessage implements IMessage {
    long id;

    @SerializedName("content")
    private String content;

    @SerializedName("sent_by")
    private String sentBy;

    @SerializedName("sent_at")
    private Date sentAt;

    String state;

    Travel travel;

    public ChatMessage(String content) {
        this.content = content;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public IUser getUser() {
        if(sentBy.equals("driver")) {
            return  travel.getDriver();
        } else {
            return travel.getRider();
        }
    }

    @Override
    public Date getCreatedAt() {
        return sentAt;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
