package com.innomalist.taxi.common.activities.travels;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.innomalist.taxi.common.R;
import com.innomalist.taxi.common.activities.travels.adapters.TravelsRecyclerViewAdapter;
import com.innomalist.taxi.common.activities.travels.fragments.WriteComplaintDialog;
import com.innomalist.taxi.common.components.BaseActivity;
import com.innomalist.taxi.common.databinding.ActivityTravelsBinding;
import com.innomalist.taxi.common.events.GetTravelsEvent;
import com.innomalist.taxi.common.events.GetTravelsResultEvent;
import com.innomalist.taxi.common.events.HideTravelEvent;
import com.innomalist.taxi.common.events.HideTravelResultEvent;
import com.innomalist.taxi.common.events.WriteComplaintEvent;
import com.innomalist.taxi.common.events.WriteComplaintResultEvent;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.AlertDialogBuilder;
import com.innomalist.taxi.common.utils.AlerterHelper;
import com.tylersuehr.esr.ContentItemLoadingStateFactory;
import com.tylersuehr.esr.EmptyStateRecyclerView;
import com.tylersuehr.esr.ImageTextStateDisplay;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class TravelsActivity extends BaseActivity implements WriteComplaintDialog.onWriteComplaintInteractionListener {

    private String subjectText = "";
    private String contentText = "";
    private long lastSelectedTravelId;
    ActivityTravelsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(TravelsActivity.this, R.layout.activity_travels);
        initializeToolbar(getString(R.string.title_travel));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_LOADING, ContentItemLoadingStateFactory.newListLoadingState(this));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_EMPTY, new ImageTextStateDisplay(this, R.drawable.empty_state, "Oops!", "Nothing to show here :("));
        binding.recyclerView.setStateDisplay(EmptyStateRecyclerView.STATE_ERROR, new ImageTextStateDisplay(this, R.drawable.empty_state, "SORRY...!", "Something went wrong :("));
        binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_LOADING);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTravels();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWriteComplaintResult(WriteComplaintResultEvent event) {
        if (event.hasError()) {
            event.showError(TravelsActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY) {
                    eventBus.post(new WriteComplaintEvent(lastSelectedTravelId, subjectText, contentText));
                }
            });
        } else {
            AlerterHelper.showInfo(TravelsActivity.this, getString(R.string.message_complaint_sent));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHideTravelResult(HideTravelResultEvent event) {
        if (event.hasError())
            return;
        AlerterHelper.showInfo(TravelsActivity.this, getString(R.string.info_travel_hidden));
        getTravels();

    }

    private void getTravels() {
        eventBus.post(new GetTravelsEvent());
        binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_LOADING);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTravelsReceived(GetTravelsResultEvent event) {
        if (event.hasError()) {
            binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_ERROR);
            event.showError(TravelsActivity.this, result -> {
                if (result == AlertDialogBuilder.DialogResult.RETRY)
                    getTravels();
                else
                    finish();
            });
            return;
        }
        if (event.travels.size() == 0) {
            binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_EMPTY);
            return;
        }
        binding.recyclerView.invokeState(EmptyStateRecyclerView.STATE_OK);
        loadList(event.travels);
    }

    private void loadList(final ArrayList<Travel> travels) {
        if (travels == null)
            return;

        final TravelsRecyclerViewAdapter adapter = new TravelsRecyclerViewAdapter(TravelsActivity.this, travels, new TravelsRecyclerViewAdapter.OnTravelItemInteractionListener() {
            @Override
            public void onHideTravel(Travel travel) {
                AlertDialogBuilder.show(TravelsActivity.this, getString(R.string.question_hide_travel), AlertDialogBuilder.DialogButton.OK_CANCEL, result -> {
                    if (result == AlertDialogBuilder.DialogResult.OK)
                        eventBus.post(new HideTravelEvent(travel.getId()));
                });
            }

            @Override
            public void onWriteComplaint(Travel travel) {
                lastSelectedTravelId = travel.getId();
                FragmentManager fm = getSupportFragmentManager();
                (new WriteComplaintDialog()).show(fm, "fragment_complaint");

            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(TravelsActivity.this);
        llm.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(llm);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveComplaintClicked(WriteComplaintEvent event) {
        event.travelId = lastSelectedTravelId;
        eventBus.post(event);
    }
}