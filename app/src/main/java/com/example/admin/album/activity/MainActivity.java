package com.example.admin.album.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.admin.album.Adapter.AlbumsAdapter;
import com.example.admin.album.ApiService;
import com.example.admin.album.R;
import com.example.admin.album.datamodel.Album;

import java.util.List;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = new ApiService(this);

        apiService.getCurrentPrimaryAlbum(new ApiService.OnPrimaryAlbumInfoRecieved() {
            @Override
            public void onRecieved(List<Album> news) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                AlbumsAdapter albumsAdapter = new AlbumsAdapter(MainActivity.this, news);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3, GridLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(albumsAdapter);
            }
        });


        ImageButton imageButton = (ImageButton) findViewById(R.id.ic_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
