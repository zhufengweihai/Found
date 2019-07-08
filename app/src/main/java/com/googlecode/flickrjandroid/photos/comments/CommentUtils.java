package com.googlecode.flickrjandroid.photos.comments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentUtils {
    public static List<Comment> createCommentList(JSONObject jsonObject) throws JSONException {
        List<Comment> comments = new ArrayList<>();
        JSONArray commentNodes = jsonObject.getJSONObject("comments").optJSONArray("comment");
        for (int i = 0; commentNodes != null && i < commentNodes.length(); i++) {
            Comment comment = new Comment();
            JSONObject commentElement = commentNodes.getJSONObject(i);
            comment.setId(commentElement.getString("id"));
            comment.setAuthor(commentElement.getString("author"));
            comment.setAuthorName(commentElement.getString("authorname"));
            comment.setPermaLink(commentElement.getString("permalink"));
            long unixTime = commentElement.optLong("datecreate");
            comment.setDateCreate(new Date(unixTime * 1000L));
            comment.setText(commentElement.getString("_content"));
            comments.add(comment);
        }
        return comments;
    }
}
