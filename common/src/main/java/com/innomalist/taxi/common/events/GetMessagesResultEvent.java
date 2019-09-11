package com.innomalist.taxi.common.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.innomalist.taxi.common.models.ChatMessage;
import com.innomalist.taxi.common.utils.ServerResponse;

import java.lang.reflect.Type;
import java.util.List;

public class GetMessagesResultEvent extends BaseResultEvent {
    public List<ChatMessage> messages;

    public GetMessagesResultEvent(Object... args) {
        super(args);
        if(response != ServerResponse.OK)
            return;
        Type type = new TypeToken<List<ChatMessage>>() {}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson customGson = gsonBuilder.create();
        this.messages = customGson.fromJson(args[1].toString(),type);
    }
}
