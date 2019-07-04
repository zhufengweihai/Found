package com.zf.found;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.googlecode.flickrjandroid.photos.Photo;

public class PhotoDataSourceFactory extends DataSource.Factory<Integer, Photo> {
    private Context context = null;
    public PhotoDataSourceFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DataSource<Integer, Photo> create() {
        return new PhotoDataSource(context);
    }
}
