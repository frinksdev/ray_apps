package com.ray.taxi.driver.activities.profile.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.innomalist.taxi.common.models.Service;
import com.innomalist.taxi.driver.databinding.ItemServiceBinding;

import java.util.List;

public class ServicesRecyclerViewAdapter extends RecyclerView.Adapter<ServicesRecyclerViewAdapter.ViewHolder> {
    private List<Service> services;
    public final OnServiceItemInteractionListener listener;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemServiceBinding binding;
        ViewHolder(ItemServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Service service, OnServiceItemInteractionListener listener) {
            binding.setService(service);
            binding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked)
                    listener.onChecked(service);
                else
                    listener.onUnchecked(service);
            });
            binding.executePendingBindings();
        }
    }

    public ServicesRecyclerViewAdapter(List<Service> services, OnServiceItemInteractionListener listener) {
        this.services = services;
        this.listener = listener;
    }

    @Override
    public ServicesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemServiceBinding itemBinding = ItemServiceBinding.inflate(layoutInflater, parent, false);
        return new ServicesRecyclerViewAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ServicesRecyclerViewAdapter.ViewHolder holder, int position) {
        Service service = services.get(position);
        holder.bind(service, listener);

    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public interface OnServiceItemInteractionListener {
        void onChecked(Service service);
        void onUnchecked(Service service);
    }
}