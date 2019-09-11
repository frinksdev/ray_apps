package com.ray.taxi.rider.activities.looking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.innomalist.taxi.common.events.GetStatusEvent;
import com.innomalist.taxi.common.events.GetStatusResultEvent;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.AlertDialogBuilder;
import com.innomalist.taxi.rider.R;
import com.innomalist.taxi.rider.databinding.ActivityLookingBinding;
import com.ray.taxi.rider.events.DriverAcceptedEvent;
import com.ray.taxi.rider.events.CancelRequestRequestEvent;
import com.ray.taxi.rider.ui.RiderBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.databinding.DataBindingUtil;

public class LookingActivity extends RiderBaseActivity {
    ActivityLookingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersive(true);
        binding = DataBindingUtil.setContentView(LookingActivity.this, R.layout.activity_looking);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDriverAccepted(DriverAcceptedEvent event) {
        binding.loadingIndicator.pauseAnimation();
        Intent intent = new Intent();
        setTravel(event.travel);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    public void onCancelRequest(View view) {
        eventBus.post(new CancelRequestRequestEvent());
        this.setResult(RESULT_CANCELED);
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.post(new GetStatusEvent());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelStatusReceived(GetStatusResultEvent event) {
        if(event.hasError()) {
            this.setResult(RESULT_CANCELED);
            this.finish();
            return;
        }
        setTravel(event.travel);
        refreshPage();
    }

    private void refreshPage() {
        Travel travel = getTravel();
        switch (travel.getStatus()) {
            case RIDER_ACCEPTED:
            case DRIVER_ACCEPTED:
                binding.loadingIndicator.pauseAnimation();
                Intent intent = new Intent();
                this.setResult(RESULT_OK, intent);
                this.finish();
                break;

            case DRIVER_CANCELED:
            case RIDER_CANCELED:
                this.setResult(RESULT_CANCELED);
                this.finish();
                break;

            case EXPIRED:
                AlertDialogBuilder.show(LookingActivity.this, "Sadly your request wasn't accepted in appropriate time and it is expired now.", AlertDialogBuilder.DialogButton.OK, result -> {
                    LookingActivity.this.setResult(RESULT_CANCELED);
                    LookingActivity.this.finish();
                });
                break;
        }

    }
}
