package com.ray.taxi.rider.activities.travel.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.model.LatLng;
import com.innomalist.taxi.common.components.BaseFragment;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.LocationHelper;
import com.innomalist.taxi.common.utils.TravelRepository;
import com.innomalist.taxi.rider.R;
import com.innomalist.taxi.rider.databinding.FragmentTravelStatsBinding;
import com.ray.taxi.rider.events.GetTravelInfoResultEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TabStatisticsFragment extends BaseFragment {
    private FragmentTravelStatsBinding binding;
    private onTravelInfoReceived listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRegisterEventBus(true);
        super.onCreate(savedInstanceState);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Activity activity = getActivity();
                if(activity == null)
                    return;
                activity.runOnUiThread(() -> {
                    Travel travel = TravelRepository.get(getContext(), TravelRepository.AppType.RIDER);
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

    public static TabStatisticsFragment newInstance() {
        return new TabStatisticsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_travel_stats,container,false);
        Travel travel = TravelRepository.get(getContext(), TravelRepository.AppType.RIDER);
        binding.costText.setText(getString(R.string.unit_money, travel.getCostBest()));
        binding.balanceText.setText(getString(R.string.unit_money, CommonUtils.rider.getBalance()));
        return binding.getRoot();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelInfoReceived(GetTravelInfoResultEvent event) {
        if(listener != null)
            listener.onReceived(event.location);
        Travel travel = TravelRepository.get(getContext(), TravelRepository.AppType.RIDER);
        LatLng destination = travel.getStartTimestamp() == null ? travel.getPickupPoint() : travel.getDestinationPoint();
        double distance = LocationHelper.distFrom(event.location, destination);
        if(binding.getRoot().getResources().getBoolean(R.bool.use_miles))
            binding.distanceText.setText(binding.getRoot().getContext().getString(R.string.unit_distance_miles, distance / 1609.344f));
        else
            binding.distanceText.setText(getString(R.string.unit_distance, distance / 1000f));
    }

    public void onUpdatePrice(Double price) {
        binding.costText.setText(getString(R.string.unit_money, price));
    }

    public interface onTravelInfoReceived {
        void onReceived(LatLng driverLocation);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReviewDialog.onReviewFragmentInteractionListener) {
            listener = (onTravelInfoReceived) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onEditAddressInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
