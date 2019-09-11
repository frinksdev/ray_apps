package com.ray.taxi.rider.activities.main.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innomalist.taxi.common.models.Service;
import com.innomalist.taxi.common.utils.ItemClickSupport;
import com.ray.taxi.rider.activities.main.adapters.ServicesListAdapter;
import com.ray.taxi.rider.ui.gravitySnapHelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

public class ServiceCarousalFragment extends Fragment {
    private static final String ARG_SERVICES = "services";

    private List<Service> services;

    private OnServicesCarousalFragmentListener mListener;

    public ServiceCarousalFragment() {
        // Required empty public constructor
    }

    public static ServiceCarousalFragment newInstance(ArrayList<Service> services) {
        ServiceCarousalFragment fragment = new ServiceCarousalFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SERVICES, services);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            services = (ArrayList<Service>) getArguments().getSerializable(ARG_SERVICES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);
        ServicesListAdapter adapter = new ServicesListAdapter(services);
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> mListener.onServiceSelected(services.get(position)));
        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnServicesCarousalFragmentListener) {
            mListener = (OnServicesCarousalFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnServicesCarousalFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnServicesCarousalFragmentListener {
        void onServiceSelected(Service service);
    }
}
