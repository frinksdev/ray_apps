package com.innomalist.taxi.common.activities.chat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.innomalist.taxi.common.R;
import com.innomalist.taxi.common.components.BaseActivity;
import com.innomalist.taxi.common.events.GetMessagesEvent;
import com.innomalist.taxi.common.events.GetMessagesResultEvent;
import com.innomalist.taxi.common.events.MessageReceivedEvent;
import com.innomalist.taxi.common.events.SendMessageEvent;
import com.innomalist.taxi.common.events.SendMessageResultEvent;
import com.innomalist.taxi.common.models.ChatMessage;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.AlertDialogBuilder;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.TravelRepository;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

public class ChatActivity extends BaseActivity {
    MessagesList messagesList;
    MessageInput messageInput;
    String app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initializeToolbar(getString(R.string.chat_activity_title));
        ImageLoader imageLoader = (imageView, url, payload) -> Glide.with(ChatActivity.this).load(url).into(imageView);
        app = Objects.requireNonNull(getIntent().getExtras()).getString("app");
        if(app == null)
            app = "rider";
        //TODO: Correct id based on being run from driver or rider.
        MessagesListAdapter<ChatMessage> adapter = new MessagesListAdapter<>(app.equals("driver") ? CommonUtils.driver.getId() : CommonUtils.rider.getId(), imageLoader);
        messagesList = findViewById(R.id.messages_list);
        messageInput = findViewById(R.id.message_input);
        messagesList.setAdapter(adapter);
        messageInput.setInputListener(input -> {
            eventBus.post(new SendMessageEvent(input.toString()));
            return true;
        });
        eventBus.post(new GetMessagesEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessagesReceived(GetMessagesResultEvent event) {
        MessagesListAdapter adapter = ((MessagesListAdapter)messagesList.getAdapter());
        if(adapter == null) {
            AlertDialogBuilder.show(ChatActivity.this, "Adapter is null");
            return;
        }
        adapter.clear();
        Travel travel = TravelRepository.get(ChatActivity.this, app.equals("driver") ? TravelRepository.AppType.DRIVER : TravelRepository.AppType.RIDER);
        for(ChatMessage message : event.messages) {
            message.setTravel(travel);
        }
        adapter.addToEnd(event.messages, true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSendMessageResult(SendMessageResultEvent event) {
        MessagesListAdapter adapter = ((MessagesListAdapter)messagesList.getAdapter());
        Travel travel = TravelRepository.get(ChatActivity.this, app.equals("driver") ? TravelRepository.AppType.DRIVER : TravelRepository.AppType.RIDER);
        event.message.setTravel(travel);
        adapter.addToStart(event.message, true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageReceived(MessageReceivedEvent event) {
        MessagesListAdapter adapter = ((MessagesListAdapter)messagesList.getAdapter());
        Travel travel = TravelRepository.get(ChatActivity.this, app.equals("driver") ? TravelRepository.AppType.DRIVER : TravelRepository.AppType.RIDER);
        event.message.setTravel(travel);
        adapter.addToStart(event.message, true);
    }

}
