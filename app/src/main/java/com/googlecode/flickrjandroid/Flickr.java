/*
 * Copyright (c) 2005 Aetrion LLC.
 */
package com.googlecode.flickrjandroid;

import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.PhotoUtils;
import com.googlecode.flickrjandroid.photos.SearchParameters;
import com.googlecode.flickrjandroid.photos.comments.Comment;
import com.googlecode.flickrjandroid.util.UrlUtilities;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Flickr {
    private static final String API_KEY = "67ac340c3c22c652860354ac0e42d1a8";
    private static final String DEFAULT_HOST = "www.flickr.com";
    private static final String PATH = "/services/rest/";
    private static final int PORT = 80;
    private static final String METHOD_SEARCH = "flickr.photos.search";
    private static final String METHOD_GET_LIST = "flickr.photos.comments.getList";

    private static Flickr flickr = new Flickr();

    private Flickr() {
    }

    public static Flickr getInstance() {
        return flickr;
    }

    public PhotoList search(SearchParameters params, int perPage, int page) throws FlickrException, JSONException {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(createUrl(params, perPage, page));
        return PhotoUtils.createPhotoList(NoHttp.startRequestSync(request).get());
    }

    private String createUrl(SearchParameters params, int perPage, int page) throws FlickrException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("method", METHOD_SEARCH));
        parameters.add(new Parameter("api_key", API_KEY));
        parameters.addAll(params.getAsParameters());
        if (perPage > 0) {
            parameters.add(new Parameter("per_page", "" + perPage));
        }
        if (page > 0) {
            parameters.add(new Parameter("page", "" + page));
        }
        parameters.add(new Parameter("nojsoncallback", "1"));
        parameters.add(new Parameter("format", "json"));

        return UrlUtilities.buildUrlString(DEFAULT_HOST, PORT, PATH, parameters);
    }

    public void getCommentList(String photoId, OnResponseListener<JSONObject> listener) {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("method", METHOD_GET_LIST));
        parameters.add(new Parameter("api_key", API_KEY));
        parameters.add(new Parameter("photo_id", photoId));
        parameters.add(new Parameter("nojsoncallback", "1"));
        parameters.add(new Parameter("format", "json"));

        String urlString = UrlUtilities.buildUrlString(DEFAULT_HOST, PORT, PATH, parameters);
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(urlString);
        NoHttp.getRequestQueueInstance().add(0, request, listener);
    }
}
