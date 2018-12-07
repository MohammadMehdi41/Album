package com.example.admin.album.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.album.R;
import com.example.admin.album.activity.SecondaryActivity;
import com.example.admin.album.datamodel.Album;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Admin on 12/5/2018.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {


    private Context context;
    private List<Album> albums;


    public AlbumsAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @Override
    public AlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_albums, parent, false);

        return new AlbumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumsViewHolder holder, int position) {
        Album albumInfo = albums.get(position);


        holder.bindAppFeature(albums.get(position));


    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class AlbumsViewHolder extends RecyclerView.ViewHolder {
        private ImageView primary_image;
        private TextView primary_text;

        public AlbumsViewHolder(final View itemView) {
            super(itemView);

            primary_image = (ImageView) itemView.findViewById(R.id.primitive_picture);
            primary_text = (TextView) itemView.findViewById(R.id.primitive_txt_title);


        }

        public void bindAppFeature(final Album album) {
            Picasso.with(itemView.getContext()).load(album.getPrimaryImageUrl()).into(primary_image);
            primary_text.setText(album.getPrimaryTitle());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(itemView.getContext(), SecondaryActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("key" , album.getAlbumId());
                    intent.putExtras(mBundle);
                    itemView.getContext().startActivity(intent);



                }
            });
        }
    }
}
