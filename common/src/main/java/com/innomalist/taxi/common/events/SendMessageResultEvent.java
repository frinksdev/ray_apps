package com.innomalist.taxi.common.events;

import com.google.gson.Gson;
import com.innomalist.taxi.common.models.ChatMessage;

public class SendMessageResultEvent extends BaseResultEvent {
    public ChatMessage message;
    public SendMessageResultEvent(Object... args) {
        super(args);
        message = new Gson().fromJson(args[1].toString(), ChatMessage.class);
    }
}
