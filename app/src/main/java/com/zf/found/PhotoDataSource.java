package com.zf.found;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.photos.Extras;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.SearchParameters;

public class PhotoDataSource extends PageKeyedDataSource<Integer, Photo> {
    private SearchParameters parameters = null;
    private Context context = null;
    private int page = 0;

    public PhotoDataSource(Context context) {
        this.context = context;
        parameters = new SearchParameters();
        //parameters.setTags(new String[]{"beauty girl"});
        parameters.setText("beauty girl");
        parameters.setSort(SearchParameters.INTERESTINGNESS_DESC);
        parameters.setExtras(Extras.ALL_EXTRAS);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Photo> callback) {
        try {
            PhotoList photoList = Flickr.getInstance().search(parameters, 10, page);
            callback.onResult(photoList, null, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        try {
            PhotoList photoList = Flickr.getInstance().search(parameters, 10, --page);
            callback.onResult(photoList, params.key - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        try {
            PhotoList photoList = Flickr.getInstance().search(parameters, 10, ++page);
            callback.onResult(photoList, params.key + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
