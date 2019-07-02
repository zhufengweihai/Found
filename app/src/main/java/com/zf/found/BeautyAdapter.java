package com.zf.found;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.googlecode.flickrjandroid.photos.Photo;

public class BeautyAdapter extends PagedListAdapter<Photo, BeautyViewHolder> {

    public BeautyAdapter() {
        super(getDiffCallback());
    }

    @NonNull
    @Override
    public BeautyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BeautyViewHolder(parent);
    }

    @NonNull
    private static DiffUtil.ItemCallback<Photo> getDiffCallback() {
        return new DiffUtil.ItemCallback<Photo>() {
            @Override
            public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
                return oldItem.equals(newItem);
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull BeautyViewHolder holder, int position) {
        Photo photo = getItem(position);
        Glide.with(holder.photoView).load(photo.getMediumUrl()).fitCenter().into(holder.photoView);
    }

}