package com.ray.taxi.driver.activities.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.innomalist.taxi.common.activities.chargeAccount.ChargeAccountActivity;
import com.innomalist.taxi.common.activities.transactions.TransactionsActivity;
import com.innomalist.taxi.common.activities.travels.TravelsActivity;
import com.innomalist.taxi.common.events.AcceptOrderEvent;
import com.innomalist.taxi.common.events.GetStatusEvent;
import com.innomalist.taxi.common.events.GetStatusResultEvent;
import com.innomalist.taxi.common.events.NotificationPlayerId;
import com.innomalist.taxi.common.events.ProfileInfoChangedEvent;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.AlertDialogBuilder;
import com.innomalist.taxi.common.utils.AlerterHelper;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.DataBinder;
import com.innomalist.taxi.common.utils.MyPreferenceManager;
import com.innomalist.taxi.common.utils.ServerResponse;
import com.innomalist.taxi.driver.R;
import com.ray.taxi.driver.activities.about.AboutActivity;
import com.ray.taxi.driver.activities.main.adapters.RequestsFragmentPagerAdapter;
import com.ray.taxi.driver.activities.main.fragments.RequestFragment;
import com.ray.taxi.driver.activities.profile.ProfileActivity;
import com.ray.taxi.driver.activities.statistics.StatisticsActivity;
import com.ray.taxi.driver.activities.travel.TravelActivity;
import com.innomalist.taxi.driver.databinding.ActivityMainBinding;
import com.ray.taxi.driver.events.AcceptOrderResultEvent;
import com.ray.taxi.driver.events.CancelRequestEvent;
import com.ray.taxi.driver.events.ChangeStatusEvent;
import com.ray.taxi.driver.events.ChangeStatusResultEvent;
import com.ray.taxi.driver.events.GetRequestsRequestEvent;
import com.ray.taxi.driver.events.GetRequestsResultEvent;
import com.ray.taxi.driver.events.LocationChangedEvent;
import com.ray.taxi.driver.events.RequestReceivedEvent;
import com.ray.taxi.driver.events.StartTrackingRequestEvent;
import com.ray.taxi.driver.events.StopTrackingRequestEvent;
import com.ray.taxi.driver.ui.DriverBaseActivity;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static org.greenrobot.eventbus.ThreadMode.MAIN;

public class MainActivity extends DriverBaseActivity implements OnMapReadyCallback, LocationListener, RequestFragment.OnFragmentInteractionListener, OSSubscriptionObserver {
    MyPreferenceManager SP;
    private GoogleMap mMap;
    Marker markerDriver;
    Marker markerPickup;
    Marker markerDropOff;
    ActivityMainBinding binding;
    private RequestsFragmentPagerAdapter requestCardsAdapter;
    static final int ACTIVITY_PROFILE = 11;
    static final int ACTIVITY_WALLET = 12;
    static final int ACTIVITY_TRAVEL = 14;
    SupportMapFragment mapFragment;
    private MaterialDialog loadingRequestsLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.addSubscriptionObserver(this);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        requestCardsAdapter = new RequestsFragmentPagerAdapter(getSupportFragmentManager(), new ArrayList<>());
        binding.requestsViewPager.setAdapter(requestCardsAdapter);
        binding.requestsViewPager.setOffscreenPageLimit(3);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        SP = MyPreferenceManager.getInstance(this.getApplicationContext());
        setSupportActionBar(binding.appbar);
        ActionBar actionBar = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.md_teal_700));
        }
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            binding.drawerLayout.closeDrawers();
            switch (menuItem.getItemId()) {
                case (R.id.nav_item_travels):
                    startActivity(new Intent(MainActivity.this, TravelsActivity.class));
                    break;
                case (R.id.nav_item_profile):
                    startActivityForResult(new Intent(MainActivity.this, ProfileActivity.class), ACTIVITY_PROFILE);
                    break;
                case (R.id.nav_item_statistics):
                    startActivity(new Intent(MainActivity.this, StatisticsActivity.class));
                    break;
                case (R.id.nav_item_charge_account):
                    startActivityForResult(new Intent(MainActivity.this, ChargeAccountActivity.class), ACTIVITY_WALLET);
                    break;
                case(R.id.nav_item_transactions):
                    startActivity(new Intent(MainActivity.this, TransactionsActivity.class));
                    break;
                case (R.id.nav_item_about):
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    break;
                case (R.id.nav_item_exit):
                    logout();
                    break;
                default:
                    Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
        fillInfo();
        eventBus.post(new GetStatusEvent());
        binding.switchConnection.setOnCheckedChangeListener(onConnectionSwitchChanged);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        getLastKnownLocation();
        if (getResources().getBoolean(R.bool.isNightMode)) {
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_night));
            if (!success)
                Log.e("MapsActivityRaw", "Style parsing failed.");
        }
    }

    public void moveDriverPin(double lat, double lng) {
        LatLng driver = new LatLng(lat, lng);
        markerDriver.setPosition(driver);
        CommonUtils.currentLocation = driver;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.post(new GetRequestsRequestEvent());
        loadingRequestsLoadingDialog = new MaterialDialog.Builder(this)
                .title("Reloading status")
                .content("Please wait...")
                .progress(true, 0)
                .cancelable(false)
                .show();

    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onRequestsReceived(GetRequestsResultEvent event) {
        loadingRequestsLoadingDialog.dismiss();
        if(event.response == ServerResponse.DRIVER_IS_OFFLINE) {
            binding.switchConnection.setOnCheckedChangeListener(null);
            binding.switchConnection.setChecked(false);
            binding.switchConnection.setOnCheckedChangeListener(onConnectionSwitchChanged);
            return;
        }
        binding.switchConnection.setOnCheckedChangeListener(null);
        binding.switchConnection.setChecked(true);
        binding.switchConnection.setOnCheckedChangeListener(onConnectionSwitchChanged);
        requestCardsAdapter = new RequestsFragmentPagerAdapter(getSupportFragmentManager(), event.travels);
        binding.requestsViewPager.setAdapter(requestCardsAdapter);
        binding.requestsViewPager.setOffscreenPageLimit(3);

    }

    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onAnotherDriverAcceptedRequest(CancelRequestEvent event) {
        int position = requestCardsAdapter.getPositionWithTravelId(event.travelId);
        if (position >= 0)
            requestCardsAdapter.remove(position);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestReceived(RequestReceivedEvent event) {
        requestCardsAdapter.add(event.travel);
        requestCardsAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onProfileChanged(ProfileInfoChangedEvent event) {
        fillInfo();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStatusChanged(ChangeStatusResultEvent event) {
        if (event.hasError()) {
            event.showError(MainActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    onConnectionSwitchChanged.onCheckedChanged(null, binding.switchConnection.isChecked());
                else {
                    binding.switchConnection.setEnabled(true);
                    binding.switchConnection.setOnCheckedChangeListener(null);
                    binding.switchConnection.setChecked(!binding.switchConnection.isChecked());
                    binding.switchConnection.setOnCheckedChangeListener(onConnectionSwitchChanged);
                }
            });
            return;
        }
        binding.switchConnection.setEnabled(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDriverAcceptedResult(AcceptOrderResultEvent event) {
        Intent intentTravel = new Intent(MainActivity.this, TravelActivity.class);
        setTravel(event.travel);
        startActivityForResult(intentTravel, ACTIVITY_TRAVEL);
    }

    private CompoundButton.OnCheckedChangeListener onConnectionSwitchChanged = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (binding.switchConnection.isChecked()) {
                eventBus.post(new ChangeStatusEvent(ChangeStatusEvent.Status.ONLINE));
                eventBus.post(new StartTrackingRequestEvent());
                if(markerDriver != null) {
                    eventBus.post(new LocationChangedEvent(markerDriver.getPosition()));
                }
            } else {
                eventBus.post(new ChangeStatusEvent(ChangeStatusEvent.Status.OFFLINE));
                eventBus.post(new StopTrackingRequestEvent());
            }
            binding.switchConnection.setEnabled(false);

        }
    };

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager manager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers;
        if (manager != null) {
            providers = manager.getProviders(true);
        } else
            return;
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = manager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        LatLng latLng;
        if (bestLocation == null)
            latLng = new LatLng(Float.parseFloat(getString(R.string.defaultLocation).split(",")[0]), Float.parseFloat(getString(R.string.defaultLocation).split(",")[1]));
        else
            latLng = new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());
        if (markerDriver == null)
            markerDriver = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_taxi)));
        else
            markerDriver.setPosition(latLng);
        if (binding.switchConnection.isChecked())
            eventBus.post(new LocationChangedEvent(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }

    private void fillInfo() {
        try {
            String name;
            if ((CommonUtils.driver.getFirstName() == null || CommonUtils.driver.getFirstName().isEmpty()) && (CommonUtils.driver.getLastName() == null || CommonUtils.driver.getLastName().isEmpty()))
                name = String.valueOf(CommonUtils.driver.getMobileNumber());
            else
                name = CommonUtils.driver.getFirstName() + " " + CommonUtils.driver.getLastName();
            View header = binding.navigationView.getHeaderView(0);
            ((TextView) header.findViewById(R.id.navigation_header_name)).setText(name);
            ((TextView) header.findViewById(R.id.navigation_header_charge)).setText(getString(R.string.drawer_header_balance, CommonUtils.driver.getBalance()));
            ImageView imageView = header.findViewById(R.id.navigation_header_image);
            ImageView headerView = header.findViewById(R.id.navigation_background);
            DataBinder.setMedia(imageView, CommonUtils.driver.getMedia());
            DataBinder.setMedia(headerView, CommonUtils.driver.getCarMedia());
        } catch (Exception ignored) {
        }
    }

    private void logout() {
        SP.putString("driver_token", "");
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (ACTIVITY_PROFILE):
                if (resultCode == RESULT_OK)
                    AlerterHelper.showInfo(MainActivity.this, getString(R.string.info_edit_profile_success));
                fillInfo();
                break;

            case (ACTIVITY_WALLET):
                if (resultCode == RESULT_OK)
                    AlerterHelper.showInfo(MainActivity.this, getString(R.string.account_charge_success));
                fillInfo();
                break;

            case (ACTIVITY_TRAVEL):
                /*binding.switchConnection.setOnCheckedChangeListener(null);
                binding.switchConnection.setChecked(true);
                binding.switchConnection.setOnCheckedChangeListener(onConnectionSwitchChanged);
                onConnectionSwitchChanged.onCheckedChanged(binding.switchConnection, binding.switchConnection.isChecked());*/
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (binding.switchConnection.isChecked())
            eventBus.post(new LocationChangedEvent(latLng));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, mMap.getCameraPosition().zoom > 5 ? mMap.getCameraPosition().zoom : 16);
        mMap.animateCamera(cameraUpdate);
        moveDriverPin(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onAccept(Travel travel) {
        eventBus.post(new AcceptOrderEvent(travel.getId(), travel.getCostBest()));
        removeMarkers();
        while (requestCardsAdapter.getCount() > 0)
            requestCardsAdapter.remove(0);
    }

    @Subscribe(threadMode = MAIN)
    public void OnGetStatusResultReceived(GetStatusResultEvent event) {
        if (event.hasError())
            return;
        Intent intent = new Intent(MainActivity.this, TravelActivity.class);
        setTravel(event.travel);
        startActivityForResult(intent, ACTIVITY_TRAVEL);
    }

    @Override
    public void onDecline(Travel travel) {
        int position = requestCardsAdapter.getPositionWithTravelId(travel.getId());
        if (position >= 0)
            requestCardsAdapter.remove(position);
        requestCardsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onVisible(Travel travel) {
        if(markerPickup == null) {
            markerPickup = mMap.addMarker(new MarkerOptions()
                    .position(travel.getPickupPoint())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)));
        } else {
            markerPickup.setPosition(travel.getPickupPoint());
        }
        if(markerDropOff == null) {
            markerDropOff = mMap.addMarker(new MarkerOptions()
                    .position(travel.getDestinationPoint())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_destination)));
        } else {
            markerDropOff.setPosition(travel.getDestinationPoint());
        }
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(travel.getPickupPoint());
        latLngs.add(travel.getDestinationPoint());
        latLngs.add(markerDriver.getPosition());
        mMap.setPadding(0,0,0, 850);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng location : latLngs)
            builder.include(location);
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 150);
        mMap.animateCamera(cu);
        //((RequestFragment)requestCardsAdapter.getFragment(travel,requestCardsAdapter.getPositionWithTravelId(travel.getId()))).locationChanged(markerDriver.getPosition());
    }

    @Override
    public void onInvisible(Travel travel) {
        removeMarkers();
    }

    private void removeMarkers() {
        if(markerPickup != null)
            markerPickup.remove();
        if(markerDropOff != null)
            markerDropOff.remove();
        markerPickup = null;
        markerDropOff = null;
        mMap.setPadding(0,0,0,0);
    }


    @Override
    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (!stateChanges.getFrom().getSubscribed() && stateChanges.getTo().getSubscribed())
            eventBus.post(new NotificationPlayerId(stateChanges.getTo().getUserId()));
    }
}
