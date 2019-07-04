package com.zf.found;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.googlecode.flickrjandroid.photos.Photo;
import com.yanzhenjie.nohttp.NoHttp;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView listView = findViewById(R.id.listView);
        ((LinearLayoutManager)listView.getLayoutManager()).setOrientation(LinearLayoutManager.HORIZONTAL);

        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        scrollHelper.setUpRecycleView(listView);
        BeautyAdapter beautyAdapter = new BeautyAdapter();
        listView.setAdapter(beautyAdapter);
        LiveData<PagedList<Photo>> data = new LivePagedListBuilder<>(new PhotoDataSourceFactory(this),
                new PagedList.Config.Builder()
                        .setPageSize(10) // 每页 10 条
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10) // 最初加载 20 条
                        .build()
        ).build();
        data.observe(this, beautyAdapter::submitList);

        NoHttp.initialize(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
