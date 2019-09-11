package com.ray.taxi.driver.activities.profile.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.innomalist.taxi.common.models.Media;
import com.innomalist.taxi.driver.databinding.ItemDocumentBinding;

import java.util.List;

public class DocumentsRecyclerViewAdapter extends RecyclerView.Adapter<DocumentsRecyclerViewAdapter.ViewHolder> {
    private List<Media> documents;
    public final OnDocumentItemInteractionListener listener;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDocumentBinding binding;
        ViewHolder(ItemDocumentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Media media, OnDocumentItemInteractionListener listener) {
            binding.setMedia(media);
            binding.image.setOnClickListener(v -> listener.onClicked(media));
            binding.executePendingBindings();
        }
    }

    public DocumentsRecyclerViewAdapter(List<Media> documents, OnDocumentItemInteractionListener listener) {
        this.documents = documents;
        this.listener = listener;
    }

    @Override
    public DocumentsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDocumentBinding itemBinding = ItemDocumentBinding.inflate(layoutInflater, parent, false);
        return new DocumentsRecyclerViewAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(DocumentsRecyclerViewAdapter.ViewHolder holder, int position) {
        Media media = documents.get(position);
        holder.bind(media, listener);

    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    public interface OnDocumentItemInteractionListener {
        void onClicked(Media media);
    }
}