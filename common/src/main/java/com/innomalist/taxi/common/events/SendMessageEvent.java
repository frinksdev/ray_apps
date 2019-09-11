package com.innomalist.taxi.common.events;

public class SendMessageEvent extends BaseRequestEvent {
    public String content;

    public SendMessageEvent(String content) {
        this.content = content;
    }
}
