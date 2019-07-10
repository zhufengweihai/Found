package com.zf.found;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.googlecode.flickrjandroid.photos.Photo;
import com.yanzhenjie.nohttp.NoHttp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPhotoList();

        NoHttp.initialize(this);
    }

    private void initPhotoList() {
        RecyclerView beautyListView = findViewById(R.id.listView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        beautyListView.setLayoutManager(layoutManager);
        BeautyAdapter beautyAdapter = new BeautyAdapter(beautyListView);
        beautyListView.setAdapter(beautyAdapter);
        LiveData<PagedList<Photo>> data = new LivePagedListBuilder<>(new PhotoDataSourceFactory(this),
                new PagedList.Config.Builder()
                        .setPageSize(10) // 每页 10 条
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10) // 最初加载 20 条
                        .build()
        ).build();
        data.observe(this, beautyAdapter::submitList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
