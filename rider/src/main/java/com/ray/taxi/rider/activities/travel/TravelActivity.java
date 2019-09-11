package com.ray.taxi.rider.activities.travel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
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
import com.google.android.material.tabs.TabLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.innomalist.taxi.common.activities.chargeAccount.ChargeAccountActivity;
import com.innomalist.taxi.common.activities.chat.ChatActivity;
import com.innomalist.taxi.common.events.GetTravelStatus;
import com.innomalist.taxi.common.events.GetTravelStatusResultEvent;
import com.innomalist.taxi.common.events.ServiceCallRequestEvent;
import com.innomalist.taxi.common.events.ServiceCallRequestResultEvent;
import com.innomalist.taxi.common.events.ServiceCancelEvent;
import com.innomalist.taxi.common.events.ServiceCancelResultEvent;
import com.innomalist.taxi.common.location.MapHelper;
import com.innomalist.taxi.common.models.Coupon;
import com.innomalist.taxi.common.models.Review;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.models.TravelStatus;
import com.innomalist.taxi.common.utils.AlertDialogBuilder;
import com.innomalist.taxi.common.utils.AlerterHelper;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.ServerResponse;
import com.innomalist.taxi.rider.R;
import com.ray.taxi.rider.activities.coupon.CouponActivity;
import com.ray.taxi.rider.activities.travel.adapters.TravelTabsViewPagerAdapter;
import com.ray.taxi.rider.activities.travel.fragments.ReviewDialog;
import com.ray.taxi.rider.activities.travel.fragments.TabStatisticsFragment;
import com.innomalist.taxi.rider.databinding.ActivityTravelBinding;
import com.ray.taxi.rider.events.ConfirmationCodeResultEvent;
import com.ray.taxi.rider.events.ConfirmationCodeEvent;
import com.ray.taxi.rider.events.ReviewDriverEvent;
import com.ray.taxi.rider.events.ReviewDriverResultEvent;
import com.ray.taxi.rider.events.ServiceFinishedEvent;
import com.ray.taxi.rider.events.ServiceStartedEvent;
import com.ray.taxi.rider.ui.RiderBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends RiderBaseActivity implements OnMapReadyCallback, ReviewDialog.onReviewFragmentInteractionListener, TabStatisticsFragment.onTravelInfoReceived {
    private static final int ACTIVITY_COUPON = 700;
    ActivityTravelBinding binding;
    Marker pickupMarker;
    Marker driverMarker;
    Marker destinationMarker;
    LatLng driverLocation;
    GoogleMap gMap;
    TravelTabsViewPagerAdapter travelTabsViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        binding.slideCancel.setOnSlideCompleteListener(slideView -> eventBus.post(new ServiceCancelEvent()));
        binding.slideCall.setOnSlideCompleteListener(slideView -> TravelActivity.this.onCallDriverClicked(null));
        binding.chatButton.setOnClickListener(v -> {
            Intent intent = new Intent(TravelActivity.this, ChatActivity.class);
            intent.putExtra("app", "rider");
            startActivity(intent);
        });
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        travelTabsViewPagerAdapter = new TravelTabsViewPagerAdapter(getSupportFragmentManager(), TravelActivity.this, getTravel());
        binding.viewpager.setAdapter(travelTabsViewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
        Travel travel = getTravel();
        if(travel.getRating() != null) {
            travelTabsViewPagerAdapter.deletePage(2);
            TabLayout.Tab tab = binding.tabLayout.getTabAt(0);
            if (tab != null)
                tab.select();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gMap != null) {
            eventBus.post(new GetTravelStatus(getTravel().getId()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelStatusReceived(GetTravelStatusResultEvent event) {
        setTravel(event.travel);
        this.driverLocation = event.driverLocation;
        refreshPage();
    }

    private void refreshPage() {
        Travel travel = getTravel();
        if(travel.getService() != null && travel.getService().isCanUseConfirmationCode()) {
            binding.enableVerificationButton.setVisibility(View.VISIBLE);
        } else {
            binding.enableVerificationButton.setVisibility(View.GONE);
        }
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
                    if(driverLocation != null)
                        driverMarker = gMap.addMarker(new MarkerOptions().position(driverLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_taxi)));
                } else {
                    if(driverLocation != null)
                        driverMarker.setPosition(driverLocation);
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
                    locations.add(driverLocation);
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
                    if(driverLocation != null)
                        driverMarker = gMap.addMarker(new MarkerOptions().position(driverLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_taxi)));
                } else {
                    if (driverLocation != null)
                        driverMarker.setPosition(driverLocation);
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
                    locations.add(driverLocation);
                    MapHelper.centerLatLngsInMap(gMap, locations, true);
                }
                TransitionManager.beginDelayedTransition((ViewGroup) (binding.getRoot()));
                binding.slideCall.setVisibility(View.GONE);
                binding.chatButton.setVisibility(View.GONE);
                //binding.contactPanel.setVisibility(View.GONE);
                binding.slideCancel.setVisibility(View.GONE);
                break;

            case TRAVEL_FINISHED_CASH:
            case TRAVEL_FINISHED_CREDIT:
                String message;
                travel.setFinishTimestamp(new Timestamp(System.currentTimeMillis()));
                setTravel(travel);
                if (travel.getStatus() == TravelStatus.TRAVEL_FINISHED_CREDIT)
                    message = getString(R.string.travel_finished_taken_from_balance, travel.getCostAfterCoupon());
                else
                    message = getString(R.string.travel_finished_not_sufficient_balance, travel.getCostAfterCoupon());
                new MaterialDialog.Builder(this)
                        .title(R.string.message_default_title)
                        .content(message)
                        .positiveText(R.string.alert_ok)
                        .onPositive((dialog, which) -> {
                            if (travelTabsViewPagerAdapter.getCount() == 2) {
                                finish();
                                return;
                            }
                            FragmentManager fm = getSupportFragmentManager();
                            ReviewDialog reviewDialog = ReviewDialog.newInstance();
                            reviewDialog.show(fm, "fragment_review_travel");
                        }).show();

                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServiceFinished(ServiceFinishedEvent event) {
        Travel travel = getTravel();
        travel.setCostAfterCoupon(event.amount);
        travel.setStatus(event.isCreditUsed ? TravelStatus.TRAVEL_FINISHED_CREDIT : TravelStatus.TRAVEL_FINISHED_CASH);
        setTravel(travel);
        refreshPage();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServiceCanceled(ServiceCancelResultEvent event) {
        updateTravelStatus(TravelStatus.RIDER_CANCELED);
        refreshPage();
    }

    private void updateTravelStatus(TravelStatus status) {
        Travel travel = getTravel();
        travel.setStatus(status);
        setTravel(travel);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReviewResult(ReviewDriverResultEvent event) {
        if (event.response == ServerResponse.OK) {
            if (getTravel().getFinishTimestamp() != null) {
                finish();
                return;
            }
            AlerterHelper.showInfo(TravelActivity.this, getString(R.string.message_review_sent));
            travelTabsViewPagerAdapter.deletePage(2);
            TabLayout.Tab tab = binding.tabLayout.getTabAt(0);
            if (tab != null)
                tab.select();

        } else
            event.showAlert(TravelActivity.this);
    }

    public void onChargeAccountClicked(View view) {
        Intent intent = new Intent(TravelActivity.this, ChargeAccountActivity.class);
        if (getTravel().getCostBest() - CommonUtils.rider.getBalance() > 0)
            intent.putExtra("defaultAmount", getTravel().getCostBest() - CommonUtils.rider.getBalance());
        startActivity(intent);
    }

    public void onApplyCouponClicked(View view) {
        Intent intent = new Intent(TravelActivity.this, CouponActivity.class);
        intent.putExtra("select_mode", true);
        startActivityForResult(intent,ACTIVITY_COUPON);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelStarted(ServiceStartedEvent event) {
        setTravel(event.travel);
        AlerterHelper.showInfo(TravelActivity.this, getString(R.string.message_travel_started));
        refreshPage();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setTrafficEnabled(true);
        gMap.setMaxZoomPreference(17f);
        eventBus.post(new GetTravelStatus(getTravel().getId()));
    }

    PermissionListener callPermissionListener = new PermissionListener() {
        @SuppressLint("MissingPermission")
        @Override
        public void onPermissionGranted() {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+" + getTravel().getDriver().getMobileNumber()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {

        }

    };


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

    @Override
    public void onReviewTravelClicked(Review review) {
        eventBus.post(new ReviewDriverEvent(review));
    }

    public void onCallDriverClicked(View view) {
        boolean isCallRequestEnabled = getResources().getBoolean(R.bool.is_call_request_enabled_rider);
        boolean isDirectCallEnabled = getResources().getBoolean(R.bool.is_direct_call_enabled_rider);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ACTIVITY_COUPON){
            if(resultCode == RESULT_OK){
                Coupon coupon = (Coupon) data.getSerializableExtra("coupon");
                String message = "";
                if(coupon.getFlatDiscount() == 0 && coupon.getDiscountPercent() != 0)
                    message = "Coupon with discount of " + coupon.getDiscountPercent() + "% has been applied.";
                if(coupon.getFlatDiscount() != 0 && coupon.getDiscountPercent() == 0)
                    message = "Coupon with discount of " + getString(R.string.unit_money,coupon.getFlatDiscount()) + " has been applied.";
                if(coupon.getFlatDiscount() != 0 && coupon.getDiscountPercent() != 0)
                    message = "Coupon with discount of " + getString(R.string.unit_money,coupon.getFlatDiscount()) + " and " + coupon.getDiscountPercent() + "% has been applied.";
                if(message.equals(""))
                    return;
                AlerterHelper.showInfo(TravelActivity.this,message);
                travelTabsViewPagerAdapter.statisticsFragment.onUpdatePrice(data.getDoubleExtra("costAfterCoupon",getTravel().getCostBest()));

            }
        }
    }

    @Override
    public void onReceived(LatLng driverLocation) {
        this.driverLocation = driverLocation;
        refreshPage();
    }

    public void onEnableVerification(View view) {
        eventBus.post(new ConfirmationCodeEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConfirmationCodeReceived(ConfirmationCodeResultEvent event) {
        AlertDialogBuilder.show(TravelActivity.this, getString(R.string.confirmation_code_message, event.code));
    }
}
