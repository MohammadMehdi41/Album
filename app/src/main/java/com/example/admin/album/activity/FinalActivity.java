package com.example.admin.album.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.admin.album.ApiServiceFinal;
import com.example.admin.album.R;
import com.example.admin.album.datamodel.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);



        final ImageView final_image = (ImageView) findViewById(R.id.final_picture);
        final Context context;


        final int valueFinal = getIntent().getExtras().getInt("key1");

        ApiServiceFinal apiServiceFinal = new ApiServiceFinal(this ,valueFinal);

        apiServiceFinal.getCurrentFinalAlbum(new ApiServiceFinal.OnFinalAlbumInfoRecieved() {
            @Override
            public void onRecieved(List<Album> albums) {


                for (int i=0 ; i<albums.size() ; i++){
                    Album album = new Album();
                    album = albums.get(i);
                    Picasso.with(FinalActivity.this).load(album.getPrimaryImageUrl()).into(final_image);

                }
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
