package com.ray.taxi.driver.activities.travel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.transition.TransitionManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.PolyUtil;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.innomalist.taxi.common.activities.chat.ChatActivity;
import com.innomalist.taxi.common.events.GetTravelStatus;
import com.innomalist.taxi.common.events.GetTravelStatusResultEvent;
import com.innomalist.taxi.common.events.ServiceCallRequestEvent;
import com.innomalist.taxi.common.events.ServiceCallRequestResultEvent;
import com.innomalist.taxi.common.events.ServiceCancelEvent;
import com.innomalist.taxi.common.events.ServiceCancelResultEvent;
import com.innomalist.taxi.common.location.MapHelper;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.models.TravelStatus;
import com.innomalist.taxi.common.utils.AlertDialogBuilder;
import com.innomalist.taxi.common.utils.LocationHelper;
import com.innomalist.taxi.common.utils.ServerResponse;
import com.innomalist.taxi.driver.R;
import com.innomalist.taxi.driver.databinding.ActivityTravelBinding;
import com.ray.taxi.driver.events.SendTravelInfoEvent;
import com.ray.taxi.driver.events.ServiceFinishEvent;
import com.ray.taxi.driver.events.ServiceFinishResultEvent;
import com.ray.taxi.driver.events.ServiceInLocationEvent;
import com.ray.taxi.driver.events.ServiceStartEvent;
import com.ray.taxi.driver.events.ServiceStartResultEvent;
import com.ray.taxi.driver.models.FinishService;
import com.ray.taxi.driver.ui.DriverBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TravelActivity extends DriverBaseActivity implements OnMapReadyCallback {
    GoogleMap gMap;
    LatLng currentLocation;
    ActivityTravelBinding binding;
    Marker pickupMarker;
    Marker driverMarker;
    Marker destinationMarker;
    List<LatLng> geoLog = new ArrayList<>();
    String encodedPoly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        LocationHelper locationHelper = new LocationHelper(this);
        locationHelper.loadGps(mLocationListener);
        binding.slideStart.setOnSlideCompleteListener(slideView -> startTravel());
        binding.slideFinish.setOnSlideCompleteListener(slideView -> finishTravel());
        binding.slideCancel.setOnSlideCompleteListener(slideView -> eventBus.post(new ServiceCancelEvent()));
        binding.costText.setText(getString(R.string.unit_money, getTravel().getCostBest()));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    Travel travel = getTravel();
                    long timeStamp = travel.getStartTimestamp() == null ? travel.getEtaPickup().getTime() : travel.getStartTimestamp().getTime() + travel.getDurationBest() * 1000;
                    long seconds = (timeStamp - new Date().getTime()) / 1000;
                    if(seconds <= 0)
                        binding.etaText.setText(R.string.eta_soon);
                    else
                        binding.etaText.setText(String.format(Locale.getDefault(),"%02d:%02d", seconds / 60, seconds % 60));
                });
            }
        }, 0, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.post(new GetTravelStatus(getTravel().getId()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelStatusReceived(GetTravelStatusResultEvent event) {
        setTravel(event.travel);
        refreshPage();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setTrafficEnabled(true);
        googleMap.setOnMapLoadedCallback(() -> {
            /*directionToPassengerRouter = new DirectionsJSONParser(gMap, driverMarker.getPosition(), destinationMarker.getPosition());
            directionToPassengerRouter.run();*/
        });
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            eventBus.post(new SendTravelInfoEvent(latLng));
            geoLog.add(new LatLng(location.getLatitude(), location.getLongitude()));
            currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
            refreshPage();
            Travel travel = getTravel();
            LatLng destination = travel.getStartTimestamp() == null ? travel.getPickupPoint() : travel.getDestinationPoint();
            double distance = LocationHelper.distFrom(latLng, destination);
            if(binding.getRoot().getResources().getBoolean(R.bool.use_miles))
                binding.distanceText.setText(binding.getRoot().getContext().getString(R.string.unit_distance_miles, distance / 1609.344f));
            else
                binding.distanceText.setText(getString(R.string.unit_distance, distance / 1000f));
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };

    void startTravel() {
        eventBus.post(new ServiceStartEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void travelStarted(ServiceStartResultEvent event) {
        setTravel(event.travel);
        refreshPage();
    }


    private void refreshPage() {
        Travel travel = getTravel();
        switch (travel.getStatus()) {
            case RIDER_ACCEPTED:
            case DRIVER_ACCEPTED:
                if (pickupMarker == null)
                    pickupMarker = gMap.addMarker(new MarkerOptions()
                            .position(travel.getPickupPoint())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)));
                else
                    pickupMarker.setPosition(travel.getPickupPoint());
                if(driverMarker == null) {
                    if(currentLocation != null)
                        driverMarker = gMap.addMarker(new MarkerOptions().position(currentLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_taxi)));
                } else {
                    if(currentLocation != null)
                        driverMarker.setPosition(currentLocation);
                    else {
                        driverMarker.remove();
                        driverMarker = null;
                    }
                }
                if (destinationMarker != null) {
                    destinationMarker.remove();
                    destinationMarker = null;
                }
                if(driverMarker == null)
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pickupMarker.getPosition(), 16));
                else {
                    List<LatLng> locations = new ArrayList<>();
                    locations.add(pickupMarker.getPosition());
                    locations.add(driverMarker.getPosition());
                    MapHelper.centerLatLngsInMap(gMap, locations, true);
                }
                break;

            case DRIVER_CANCELED:
            case RIDER_CANCELED:
                AlertDialogBuilder.show(TravelActivity.this, getString(R.string.service_canceled), AlertDialogBuilder.DialogButton.OK, result -> finish());
                break;

            case TRAVEL_STARTED:
                if(pickupMarker != null) {
                    pickupMarker.remove();
                    pickupMarker = null;
                }
                if(driverMarker == null) {
                    if(currentLocation != null)
                        driverMarker = gMap.addMarker(new MarkerOptions().position(currentLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_taxi)));
                } else {
                    if (currentLocation != null)
                        driverMarker.setPosition(currentLocation);
                }
                if (destinationMarker == null)
                    destinationMarker = gMap.addMarker(new MarkerOptions()
                            .position(travel.getDestinationPoint())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_destination)));
                else
                    destinationMarker.setPosition(travel.getPickupPoint());
                if(driverMarker == null)
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destinationMarker.getPosition(), 16));
                else {
                    List<LatLng> locations = new ArrayList<>();
                    locations.add(destinationMarker.getPosition());
                    locations.add(currentLocation);
                    MapHelper.centerLatLngsInMap(gMap, locations, true);
                }
                TransitionManager.beginDelayedTransition((ViewGroup) (binding.getRoot()));
                binding.slideStart.setVisibility(View.GONE);
                binding.slideCancel.setVisibility(View.GONE);
                binding.slideFinish.setVisibility(View.VISIBLE);
                binding.inLocationButton.setVisibility(View.GONE);
                binding.chatButton.setVisibility(View.GONE);
                binding.callButton.setVisibility(View.GONE);
                break;
        }
    }

    void finishTravel() {
        if (geoLog.size() > 0)
            encodedPoly = PolyUtil.encode(PolyUtil.simplify(geoLog, 10));
        if(getTravel().getConfirmationCode() == null) {
            FinishService finishService = new FinishService(encodedPoly, getTravel().getCostBest(), getTravel().getDistanceReal());
            eventBus.post(new ServiceFinishEvent(finishService));
        } else {
            showConfirmationDialog();
        }
    }

    void showConfirmationDialog() {
        new MaterialDialog.Builder(this)
                .title("Verification")
                .content("Finishing this service needs Confirmation code.")
                .input("Confirmation Code", "", (dialog, input) -> {

                }).onPositive((dialog, which) -> {
                    FinishService finishService = new FinishService(encodedPoly, getTravel().getCostBest(), getTravel().getDistanceReal(), Integer.parseInt(dialog.getInputEditText().getText().toString()));
                    eventBus.post(new ServiceFinishEvent(finishService));
                }).show();
    }

    public void onInLocationButtonClicked(View v) {
        eventBus.post(new ServiceInLocationEvent());
        TransitionManager.beginDelayedTransition(binding.layoutActions);
        binding.inLocationButton.setVisibility(View.GONE);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCallRequested(ServiceCallRequestResultEvent event) {
        if (event.hasError()) {
            event.showError(TravelActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    onCallDriverClicked(null);
            });
            return;
        }
        AlertDialogBuilder.show(TravelActivity.this, getString(R.string.call_request_sent));
    }

    PermissionListener callPermissionListener = new PermissionListener() {
        @SuppressLint("MissingPermission")
        @Override
        public void onPermissionGranted() {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+" + getTravel().getRider().getMobileNumber()));
            startActivity(intent);
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }

    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServiceCanceled(ServiceCancelResultEvent event) {
        if (event.hasError()) {
            event.showError(TravelActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    eventBus.post(new ServiceCancelEvent());
            });
            return;
        }
        Travel travel = getTravel();
        travel.setStatus(TravelStatus.DRIVER_CANCELED);
        setTravel(travel);
        refreshPage();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServicedFinished(ServiceFinishResultEvent event) {
        if (event.hasError()) {
            if(event.response == ServerResponse.CONFIRMATION_CODE_NOT_PROVIDED) {
                showConfirmationDialog();
                return;
            }
            event.showError(TravelActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    finishTravel();
            });
            return;
        }
        if (event.isCreditUsed) {
            new MaterialDialog.Builder(this)
                    .title(R.string.message_default_title)
                    .content(R.string.service_finished_credit)
                    .positiveText(R.string.alert_ok)
                    .onPositive((dialog, which) -> finish())
                    .show();
        } else {
            new MaterialDialog.Builder(this)
                    .title(R.string.message_default_title)
                    .content(getString(R.string.service_finished_cash, getString(R.string.unit_money, event.amount)))
                    .positiveText(R.string.alert_ok)
                    .onPositive((dialog, which) -> finish())
                    .show();
        }
    }

    public void onCallDriverClicked(View view) {
        boolean isCallRequestEnabled = getResources().getBoolean(R.bool.is_call_request_enabled_driver);
        boolean isDirectCallEnabled = getResources().getBoolean(R.bool.is_direct_call_enabled_driver);
        if (isCallRequestEnabled && !isDirectCallEnabled)
            eventBus.post(new ServiceCallRequestEvent());
        if (!isCallRequestEnabled && isDirectCallEnabled)
            TedPermission.with(this)
                    .setPermissionListener(callPermissionListener)
                    .setDeniedMessage(R.string.message_permission_denied)
                    .setPermissions(Manifest.permission.CALL_PHONE)
                    .check();
        new MaterialDialog.Builder(this)
                .title(R.string.select_contact_approach)
                .items(new String[]{getString(R.string.direct_call), getString(R.string.operator_call)})
                .itemsCallback((dialog, view1, which, text) -> {
                    if (which == 0)
                        TedPermission.with(TravelActivity.this)
                                .setPermissionListener(callPermissionListener)
                                .setDeniedMessage(R.string.message_permission_denied)
                                .setPermissions(Manifest.permission.CALL_PHONE)
                                .check();
                    if (which == 1)
                        eventBus.post(new ServiceCallRequestEvent());
                })
                .show();
    }

    public void onChatButtonClicked(View view) {
        Intent intent = new Intent(TravelActivity.this, ChatActivity.class);
        intent.putExtra("app", "driver");
        startActivity(intent);
    }
}
