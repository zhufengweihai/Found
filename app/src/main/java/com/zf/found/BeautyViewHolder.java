package com.zf.found;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class BeautyViewHolder extends RecyclerView.ViewHolder {
    public final ImageView photoView;
    public final ImageView likeButton;
    public final ImageView chatButton;

    public BeautyViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beauty, parent, false));
        photoView = itemView.findViewById(R.id.photoView);
        likeButton = itemView.findViewById(R.id.likeButton);
        chatButton = itemView.findViewById(R.id.chatButton);
    }
}