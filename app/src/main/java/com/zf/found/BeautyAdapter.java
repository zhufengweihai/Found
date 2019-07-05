package com.zf.found;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        Glide.with(holder.photoView).load(photo.getMediumUrl()).placeholder(R.drawable.foundme).into(holder.photoView);

        Flickr.getInstance().getCommentList(photo.getId(), new SimpleResponseListener<JSONObject>() {
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                try {
                    List<Comment> commentList = CommentUtils.createCommentList(response.get());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}