package com.innomalist.taxi.common.events;

import com.innomalist.taxi.common.utils.ServerResponse;

public class GetMessagesEvent extends BaseRequestEvent {
    public GetMessagesEvent() {
        super(new GetMessagesResultEvent(ServerResponse.REQUEST_TIMEOUT));
    }
}
