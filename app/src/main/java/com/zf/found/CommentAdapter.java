package com.zf.found;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.googlecode.flickrjandroid.photos.comments.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.nameView.setText(comment.getAuthorName()+":");
        holder.contentView.setText(Html.fromHtml(comment.getText()));
    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameView;
        //public final TextView timeView;
        public final TextView contentView;

        public CommentViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
            nameView = itemView.findViewById(R.id.nameView);
            //timeView = itemView.findViewById(R.id.timeView);
            contentView = itemView.findViewById(R.id.contentView);
        }
    }
}
