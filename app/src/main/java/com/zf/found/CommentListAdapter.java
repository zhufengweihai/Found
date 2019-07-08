package com.zf.found;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.googlecode.flickrjandroid.photos.comments.Comment;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter {
    List<Comment> commentList = null;

    public CommentListAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }
}
