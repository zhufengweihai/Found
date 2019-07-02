package com.zf.found;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.googlecode.flickrjandroid.photos.Photo;

public class PhotoDataSourceFactory extends DataSource.Factory<Integer, Photo> {
    @NonNull
    @Override
    public DataSource<Integer, Photo> create() {
        return new PhotoDataSource();
    }
}
