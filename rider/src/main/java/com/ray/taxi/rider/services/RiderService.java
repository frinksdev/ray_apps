package com.ray.taxi.rider.services;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Vibrator;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.innomalist.taxi.common.events.BackgroundServiceStartedEvent;
import com.innomalist.taxi.common.events.ChangeProfileImageEvent;
import com.innomalist.taxi.common.events.ChangeProfileImageResultEvent;
import com.innomalist.taxi.common.events.ChargeAccountEvent;
import com.innomalist.taxi.common.events.ChargeAccountResultEvent;
import com.innomalist.taxi.common.events.ConnectEvent;
import com.innomalist.taxi.common.events.ConnectResultEvent;
import com.innomalist.taxi.common.events.EditProfileInfoEvent;
import com.innomalist.taxi.common.events.EditProfileInfoResultEvent;
import com.innomalist.taxi.common.events.GetMessagesEvent;
import com.innomalist.taxi.common.events.GetMessagesResultEvent;
import com.innomalist.taxi.common.events.GetStatusEvent;
import com.innomalist.taxi.common.events.GetStatusResultEvent;
import com.innomalist.taxi.common.events.GetTransactionsRequestEvent;
import com.innomalist.taxi.common.events.GetTransactionsResultEvent;
import com.innomalist.taxi.common.events.GetTravelStatus;
import com.innomalist.taxi.common.events.GetTravelStatusResultEvent;
import com.innomalist.taxi.common.events.GetTravelsEvent;
import com.innomalist.taxi.common.events.GetTravelsResultEvent;
import com.innomalist.taxi.common.events.HideTravelEvent;
import com.innomalist.taxi.common.events.HideTravelResultEvent;
import com.innomalist.taxi.common.events.LoginEvent;
import com.innomalist.taxi.common.events.MessageReceivedEvent;
import com.innomalist.taxi.common.events.NotificationPlayerId;
import com.innomalist.taxi.common.events.ProfileInfoChangedEvent;
import com.innomalist.taxi.common.events.SendMessageEvent;
import com.innomalist.taxi.common.events.SendMessageResultEvent;
import com.innomalist.taxi.common.events.ServiceCallRequestEvent;
import com.innomalist.taxi.common.events.ServiceCallRequestResultEvent;
import com.innomalist.taxi.common.events.ServiceCancelEvent;
import com.innomalist.taxi.common.events.ServiceCancelResultEvent;
import com.innomalist.taxi.common.events.SocketConnectionEvent;
import com.innomalist.taxi.common.events.WriteComplaintEvent;
import com.innomalist.taxi.common.events.WriteComplaintResultEvent;
import com.innomalist.taxi.common.models.CRUD;
import com.innomalist.taxi.common.models.Rider;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.MyPreferenceManager;
import com.innomalist.taxi.common.utils.ServerResponse;
import com.innomalist.taxi.rider.BuildConfig;
import com.innomalist.taxi.rider.R;
import com.ray.taxi.rider.events.AcceptDriverEvent;
import com.ray.taxi.rider.events.AddCouponRequestEvent;
import com.ray.taxi.rider.events.AddCouponResultEvent;
import com.ray.taxi.rider.events.ApplyCouponRequestEvent;
import com.ray.taxi.rider.events.ApplyCouponResultEvent;
import com.ray.taxi.rider.events.CRUDAddressRequestEvent;
import com.ray.taxi.rider.events.CRUDAddressResultEvent;
import com.ray.taxi.rider.events.CalculateFareRequestEvent;
import com.ray.taxi.rider.events.CalculateFareResultEvent;
import com.ray.taxi.rider.events.ConfirmationCodeEvent;
import com.ray.taxi.rider.events.ConfirmationCodeResultEvent;
import com.ray.taxi.rider.events.GetCouponsRequestEvent;
import com.ray.taxi.rider.events.GetCouponsResultEvent;
import com.ray.taxi.rider.events.GetDriversLocationEvent;
import com.ray.taxi.rider.events.GetDriversLocationResultEvent;
import com.ray.taxi.rider.events.GetPromotionsRequestEvent;
import com.ray.taxi.rider.events.GetPromotionsResultEvent;
import com.ray.taxi.rider.events.GetTravelInfoEvent;
import com.ray.taxi.rider.events.GetTravelInfoResultEvent;
import com.ray.taxi.rider.events.LoginResultEvent;
import com.ray.taxi.rider.events.DriverAcceptedEvent;
import com.ray.taxi.rider.events.ReviewDriverEvent;
import com.ray.taxi.rider.events.ReviewDriverResultEvent;
import com.ray.taxi.rider.events.CancelRequestRequestEvent;
import com.ray.taxi.rider.events.ServiceFinishedEvent;
import com.ray.taxi.rider.events.ServiceRequestErrorEvent;
import com.ray.taxi.rider.events.ServiceRequestEvent;
import com.ray.taxi.rider.events.ServiceRequestResultEvent;
import com.ray.taxi.rider.events.ServiceStartedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;

public class RiderService extends Service {
    Socket socket;
    Vibrator vibe;
    EventBus eventBus = EventBus.getDefault();

    @Subscribe
    public void connectSocket(ConnectEvent connectEvent) {
        try {
            IO.Options options = new IO.Options();
            if(socket != null && socket.connected()) {
                eventBus.post(new ConnectResultEvent(ServerResponse.OK.getValue()));
                return;
            }
            options.query = "token=" + connectEvent.token + "&os=android&version=" + BuildConfig.VERSION_CODE;
            socket = IO.socket(getString(R.string.server_address) + "client", options);
            socket.on(Socket.EVENT_CONNECT, args -> {
                eventBus.post(new ConnectResultEvent(ServerResponse.OK.getValue()));
                eventBus.post(new SocketConnectionEvent(Socket.EVENT_CONNECT));
            })
                    .on(Socket.EVENT_DISCONNECT, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_DISCONNECT)))
                    .on(Socket.EVENT_CONNECTING, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_CONNECTING)))
                    .on(Socket.EVENT_CONNECT_ERROR, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_CONNECT_ERROR)))
                    .on(Socket.EVENT_CONNECT_TIMEOUT, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_CONNECT_TIMEOUT)))
                    .on(Socket.EVENT_RECONNECT, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_RECONNECT)))
                    .on(Socket.EVENT_RECONNECTING, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_RECONNECTING)))
                    .on(Socket.EVENT_RECONNECT_ATTEMPT, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_RECONNECT_ATTEMPT)))
                    .on(Socket.EVENT_RECONNECT_ERROR, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_RECONNECT_ERROR)))
                    .on(Socket.EVENT_RECONNECT_FAILED, args -> eventBus.post(new SocketConnectionEvent(Socket.EVENT_RECONNECT_FAILED)))
            .on("error", args -> {
                try {
                    socket.disconnect();
                    JSONObject obj = new JSONObject(args[0].toString());
                    eventBus.post(new ConnectResultEvent(ServerResponse.UNKNOWN_ERROR.getValue(), obj.getString("message")));
                } catch (JSONException c) {
                    eventBus.post(new ConnectResultEvent(ServerResponse.UNKNOWN_ERROR.getValue(), args[0].toString()));
                }
            }).on("driverInLocation", args -> {
                try {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(RiderService.this)
                                    .setContentTitle(getString(R.string.app_name))
                                    .setDefaults(NotificationCompat.DEFAULT_LIGHTS | NotificationCompat.DEFAULT_SOUND | NotificationCompat.DEFAULT_VIBRATE)
                                    .setContentText(getString(R.string.notification_driver_in_location));
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    if (notificationManager != null) {
                        notificationManager.notify(0, mBuilder.build());
                    }
                } catch (Exception c) {
                    c.printStackTrace();
                }
            }).on("startTravel", args -> eventBus.post(new ServiceStartedEvent(args))).on("cancelTravel", args -> eventBus.post(new ServiceCancelResultEvent(200)))
                    .on("driverAccepted", args -> eventBus.post(new DriverAcceptedEvent(args)))
                    .on("finishedTaxi", args -> eventBus.post(new ServiceFinishedEvent((Integer) args[0], (Boolean) args[1], Double.parseDouble(args[2].toString()))))
                    .on("riderInfoChanged", args -> {
                        MyPreferenceManager SP = new MyPreferenceManager(getApplicationContext());
                        SP.putString("rider_user", args[0].toString());
                        CommonUtils.rider = Rider.fromJson(args[0].toString());
                        eventBus.postSticky(new ProfileInfoChangedEvent());
                    })
                    .on("messageReceived", args -> eventBus.post(new MessageReceivedEvent(args)))
                    .on("travelInfoReceived", args -> eventBus.post(new GetTravelInfoResultEvent(args)));
            socket.connect();

        } catch (Exception exc) {
            Log.e(exc.toString(), "Socket connection error");
        }
    }

    @Subscribe
    public void login(LoginEvent event) {
        new LoginRequest().execute(String.valueOf(event.userName), String.valueOf(event.versionNumber));
    }

    @SuppressLint("StaticFieldLeak")
    private class LoginRequest extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... uri) {
            try {
                URL url = new URL(getString(R.string.server_address) + "rider_login");
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                client.setDoOutput(true);
                client.setDoInput(true);
                DataOutputStream wr = new DataOutputStream(client.getOutputStream());

                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("user_name", uri[0]);
                postDataParams.put("version", uri[1]);
                StringBuilder result = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, String> entry : postDataParams.entrySet()) {
                    if (first)
                        first = false;
                    else
                        result.append("&");
                    result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                wr.write(result.toString().getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                    sb.append(line);
                return sb.toString();
            } catch (Exception c) {
                c.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject obj = new JSONObject(result);
                int status = obj.getInt("status");
                if (status == 200)
                    eventBus.post(new LoginResultEvent(obj.getInt("status"), obj.getString("user"), obj.getString("token")));
                else
                    eventBus.post(new LoginResultEvent(status, obj.getString("error")));
            } catch (Exception ex) {
                Log.e("JSON Parse Failed", "Parse in Login Request Failed");
            }
        }
    }

    @Subscribe
    public void getStatus(GetStatusEvent event){
        socket.emit("getStatus", (Ack) args -> eventBus.post(new GetStatusResultEvent(args)));
    }

    @Subscribe
    public void EditProfile(EditProfileInfoEvent editProfileInfoEvent) {
        socket.emit("editProfile", editProfileInfoEvent.userInfo, (Ack) args -> eventBus.post(new EditProfileInfoResultEvent((int) args[0])));
    }

    @Subscribe
    public void requestTaxi(ServiceRequestEvent event) {
        socket.emit("requestTaxi", event.pickupPoint, event.destinationPoint, event.pickupLocation, event.dropOffLocation, event.serviceId, event.minutesFromNow, (Ack) args -> {
            if ((Integer) args[0] == 200)
                eventBus.post(new ServiceRequestResultEvent((int) args[1]));
            else {
                if ((int) args[0] == 666)
                    eventBus.post(new ServiceRequestErrorEvent((int) args[0], args[1].toString()));
                else
                    eventBus.post(new ServiceRequestErrorEvent((int) args[0]));
            }
        });
    }

    @Subscribe
    public void cancelTravel(ServiceCancelEvent event) {
        socket.emit("cancelTravel", (Ack) args -> eventBus.post(new ServiceCancelResultEvent(ServerResponse.OK.getValue())));
    }

    @Subscribe
    public void cancelRequest(CancelRequestRequestEvent event) {
        socket.emit("cancelRequest");
    }

    @Subscribe
    public void acceptDriver(AcceptDriverEvent acceptDriverEvent) {
        socket.emit("riderAccepted", acceptDriverEvent.driverId);
    }

    @Subscribe
    public void getTravels(GetTravelsEvent getTravelsEvent) {
        socket.emit("getTravels", (Ack) args -> eventBus.postSticky(new GetTravelsResultEvent((int) args[0], args[1].toString())));
    }

    @Subscribe
    public void getTravelStatus(GetTravelStatus event) {
        socket.emit("getTravelStatus", event.travelId, (Ack) args -> eventBus.post(new GetTravelStatusResultEvent(args)));
    }

    @Subscribe
    public void getDriverLocation(final GetTravelInfoEvent event) {
        socket.emit("getTravelInfo");
    }

    @Subscribe
    public void reviewDriver(ReviewDriverEvent event) {
        socket.emit("reviewDriver", event.review.getScore(), event.review.getReview(), (Ack) args -> eventBus.post(new ReviewDriverResultEvent((int) args[0])));
    }

    @Subscribe
    public void ChangeProfileImage(ChangeProfileImageEvent changeProfileImageEvent) {
        File file = new File(changeProfileImageEvent.path);
        byte[] data = new byte[(int) file.length()];
        try {
            int len = new FileInputStream(file).read(data);
            if (len > 0)
                socket.emit("changeProfileImage", data, (Ack) args -> eventBus.post(new ChangeProfileImageResultEvent((int) args[0], args[1].toString())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void getDriversLocation(final GetDriversLocationEvent event) {
        socket.emit("getDriversLocation", event.point, (Ack) args -> eventBus.post(new GetDriversLocationResultEvent((Integer) args[0], (JSONArray) args[1])));
    }

    @Subscribe
    public void WriteComplaint(final WriteComplaintEvent event) {
        socket.emit("writeComplaint", event.travelId, event.subject, event.content, (Ack) args -> eventBus.post(new WriteComplaintResultEvent((int) args[0])));
    }

    @Subscribe
    public void chargeAccount(ChargeAccountEvent event) {
        socket.emit("chargeAccount", event.type, event.stripeToken, event.amount, (Ack) args -> eventBus.post(new ChargeAccountResultEvent(args)));
    }

    @Subscribe
    public void HideTravel(final HideTravelEvent event) {
        socket.emit("hideTravel", event.travelId, (Ack) args -> eventBus.post(new HideTravelResultEvent(ServerResponse.OK.getValue())));
    }

    @Subscribe
    public void callRequest(ServiceCallRequestEvent event) {
        socket.emit("callRequest", (Ack) args -> eventBus.post(new ServiceCallRequestResultEvent((int) args[0])));
    }

    @Subscribe
    public void onCalculateFareRequested(CalculateFareRequestEvent event) {
        socket.emit("calculateFare", event.pickUpPoint, event.destinationPoint, (Ack) args -> {
            eventBus.post(new CalculateFareResultEvent(args));
        });
    }

    @Subscribe
    public void crudAddress(CRUDAddressRequestEvent event) {
        socket.emit("crudAddress", event.crud.getValue(), event.address, (Ack) args -> {
            if (event.crud == CRUD.READ)
                eventBus.post(new CRUDAddressResultEvent((int) args[0], event.crud, (JSONArray) args[1]));
            else
                eventBus.post(new CRUDAddressResultEvent((int) args[0], event.crud));
        });
    }

    @Subscribe
    public void getCoupons(GetCouponsRequestEvent event) {
        socket.emit("getCoupons", (Ack) args -> eventBus.post(new GetCouponsResultEvent(args)));
    }

    @Subscribe
    public void getPromotions(GetPromotionsRequestEvent event) {
        socket.emit("getPromotions", (Ack) args -> eventBus.post(new GetPromotionsResultEvent(args)));
    }

    @Subscribe
    public void applyCoupon(ApplyCouponRequestEvent event) {
        socket.emit("applyCoupon", event.code, (Ack) args -> eventBus.post(new ApplyCouponResultEvent(args)));
    }

    @Subscribe
    public void addCoupon(AddCouponRequestEvent event) {
        socket.emit("addCoupon", event.code, (Ack) args -> eventBus.post(new AddCouponResultEvent(args)));
    }

    @Subscribe
    public void getTransactions(GetTransactionsRequestEvent event) {
        socket.emit("getTransactions", (Ack) args -> eventBus.post(new GetTransactionsResultEvent(args)));
    }

    @Subscribe
    public void notificationPlayerId(NotificationPlayerId event) {
        socket.emit("notificationPlayerId", event.playerId);
    }

    @Subscribe
    public void sendMessage(SendMessageEvent event) {
        socket.emit("sendMessage", event.content, (Ack) args -> eventBus.post(new SendMessageResultEvent(args)));
    }

    @Subscribe
    public void enableVerification(ConfirmationCodeEvent event) {
        socket.emit("enableConfirmation", (Ack) args -> eventBus.post(new ConfirmationCodeResultEvent(args)));
    }

    @Subscribe
    public void getMessages(GetMessagesEvent event) {
        socket.emit("getMessages", (Ack) args -> eventBus.post(new GetMessagesResultEvent(args)));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        EventBus.getDefault().register(this);
        eventBus.post(new BackgroundServiceStartedEvent());
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
