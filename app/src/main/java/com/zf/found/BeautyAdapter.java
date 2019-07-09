package com.zf.found;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
        initPhotoView(holder, photo);

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
    }

    private void initPhotoView(@NonNull BeautyViewHolder holder, Photo photo) {
        Resources resources = holder.photoView.getResources();

        Glide.with(holder.photoView).load(photo.getMediumUrl()).placeholder(R.drawable.foundme).into(holder.photoView);
    }
}