/*
 * Copyright (c) 2005 Aetrion LLC.
 */
package com.googlecode.flickrjandroid;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.PhotoUtils;
import com.googlecode.flickrjandroid.photos.SearchParameters;
import com.googlecode.flickrjandroid.util.UrlUtilities;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class Flickr {
    private static final String DEFAULT_HOST = "www.flickr.com";
    private static final String PATH = "/services/rest/";
    private static final int PORT = 80;
    private static final String METHOD_SEARCH = "flickr.photos.search";
    private static final String PARAM_OAUTH_CONSUMER_KEY = "oauth_consumer_key";

    private String apiKey;

    public Flickr(String apiKey) {
        this.apiKey = apiKey;
    }

    public void search(SearchParameters params, int perPage, int page, OnResponseListener<JSONObject> responseListener) throws FlickrException {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(createUrl(params, perPage, page));
        NoHttp.getRequestQueueInstance().add(0, request, responseListener);
    }

    public PhotoList search(SearchParameters params, int perPage, int page) throws FlickrException, JSONException {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(createUrl(params, perPage, page));
        return PhotoUtils.createPhotoList(NoHttp.startRequestSync(request).get());
    }

    public String createUrl(SearchParameters params, int perPage, int page) throws FlickrException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("method", METHOD_SEARCH));
        parameters.add(new Parameter("api_key", apiKey));
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
}
