package com.zf.found;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BeautyViewHolder extends RecyclerView.ViewHolder {
    public final ImageView photoView;
    public final ImageView likeButton;
    public final ImageView chatButton;
    public final RecyclerView commentListView;

    public BeautyViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beauty, parent, false));
        photoView = itemView.findViewById(R.id.photoView);
        likeButton = itemView.findViewById(R.id.likeButton);
        chatButton = itemView.findViewById(R.id.chatButton);
        commentListView = itemView.findViewById(R.id.commentListView);
        commentListView.addItemDecoration(new DividerItemDecoration(parent.getContext(), LinearLayoutManager.HORIZONTAL));
        //init();
    }

    private void init() {
        itemView.setOnTouchListener(new View.OnTouchListener() {
            float starty;
            float startx;
            int originHeight = 0;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (originHeight == 0) {
                    originHeight = photoView.getHeight();
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    starty = event.getY();
                    startx = event.getX();
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    ViewGroup.LayoutParams layoutParams = photoView.getLayoutParams();
                    int height = (int) (photoView.getHeight() + event.getY() - starty);
                    layoutParams.height = height < 0 ? 0 : (height > originHeight ? originHeight : height);
                    layoutParams.width = photoView.getWidth();
                    photoView.setVisibility(height <= 0 ? View.GONE : View.VISIBLE);
                    photoView.setLayoutParams(layoutParams);
                    starty = event.getY();
                }
                return false;
            }
        });
    }
}