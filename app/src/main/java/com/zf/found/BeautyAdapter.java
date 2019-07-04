package com.zf.found;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.AT_MOST);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.AT_MOST);
        holder.itemView.measure(w, h);
        int height = holder.itemView.getMeasuredHeight();
        int width = holder.itemView.getMeasuredWidth();
       // holder.photoView.setMaxHeight(height - holder.likeButton.getMeasuredHeight() - holder.commentView.getMeasuredHeight());
        Glide.with(holder.photoView).load(photo.getMediumUrl()).into(holder.photoView);
    }

}