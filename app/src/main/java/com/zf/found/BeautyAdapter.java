package com.zf.found;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.comments.Comment;
import com.googlecode.flickrjandroid.photos.comments.CommentUtils;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BeautyAdapter extends PagedListAdapter<Photo, BeautyViewHolder> {
    private RecyclerView beautyListView;

    public BeautyAdapter(RecyclerView beautyListView) {
        super(getDiffCallback());
        this.beautyListView = beautyListView;
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
        Glide.with(holder.photoView).load(photo.getMediumUrl()).placeholder(R.drawable.foundme).into(holder.photoView);

        holder.likeText.setText(String.valueOf(photo.getFavorites()));
        holder.chatText.setText(String.valueOf(photo.getComments()));

        Flickr.getInstance().getCommentList(photo.getId(), new SimpleResponseListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                try {
                    List<Comment> commentList = CommentUtils.createCommentList(response.get());
                    holder.commentListView.setAdapter(new CommentAdapter(commentList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.leftButton.setOnClickListener(v -> {
            int itemPosition = ((LinearLayoutManager) beautyListView.getLayoutManager()).findFirstVisibleItemPosition();
            beautyListView.smoothScrollToPosition(itemPosition > 0 ? --itemPosition : 0);
        });
        holder.rightButton.setOnClickListener(v -> {
            int itemPosition = ((LinearLayoutManager) beautyListView.getLayoutManager()).findFirstVisibleItemPosition();
            beautyListView.smoothScrollToPosition(++itemPosition);
        });
    }
}