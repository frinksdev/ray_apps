package com.innomalist.taxi.common.events;

import com.google.gson.Gson;
import com.innomalist.taxi.common.models.ChatMessage;

public class MessageReceivedEvent {
    public ChatMessage message;
    public MessageReceivedEvent(Object... args) {
        this.message = new Gson().fromJson(args[0].toString(), ChatMessage.class);
    }
}
