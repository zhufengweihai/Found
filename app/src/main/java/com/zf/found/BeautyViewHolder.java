package com.zf.found;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BeautyViewHolder extends RecyclerView.ViewHolder {
    public final ImageView photoView;
    public final ImageButton leftButton;
    public final ImageButton rightButton;
    public final EditText commentEditText;
    public final ImageButton addButton;

    public final RecyclerView commentListView;

    public BeautyViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beauty, parent, false));
        photoView = itemView.findViewById(R.id.photoView);
        leftButton = itemView.findViewById(R.id.leftButton);
        rightButton = itemView.findViewById(R.id.rightButton);
        commentEditText = itemView.findViewById(R.id.commentEditText);
        addButton = itemView.findViewById(R.id.addButton);
        commentListView = itemView.findViewById(R.id.commentListView);
        commentListView.addItemDecoration(new DividerItemDecoration(parent.getContext(), LinearLayoutManager.HORIZONTAL));
    }
}