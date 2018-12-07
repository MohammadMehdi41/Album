package com.example.admin.album.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.admin.album.Adapter.SecondaryAdapter;
import com.example.admin.album.ApiServiceSecond;
import com.example.admin.album.R;
import com.example.admin.album.datamodel.Album;

import java.util.List;

public class SecondaryActivity extends AppCompatActivity {
    private static final String TAG = "SecondaryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);


        Bundle bundle  = getIntent().getExtras();
        int value = bundle.getInt("key");


        ApiServiceSecond apiServiceSecond = new ApiServiceSecond(this ,value);

        apiServiceSecond.getCurrentPrimaryAlbum(new ApiServiceSecond.OnSecondaryAlbumInfoRecieved() {
            @Override
            public void onRecieved(List<Album> news) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_secondary);
                SecondaryAdapter secondaryAdapter = new SecondaryAdapter(SecondaryActivity.this, news);
                recyclerView.setLayoutManager(new GridLayoutManager(SecondaryActivity.this,5, GridLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(secondaryAdapter);
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
