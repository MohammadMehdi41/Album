package com.example.admin.album.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.album.R;
import com.example.admin.album.activity.FinalActivity;
import com.example.admin.album.datamodel.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 12/6/2018.
 */

public class SecondaryAdapter extends RecyclerView.Adapter<SecondaryAdapter.SecondaryViewHolder> {

    private Context context;
    private List<Album> albums;

    public SecondaryAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;

    }


    @Override
    public SecondaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_photos, parent, false);

        return new SecondaryAdapter.SecondaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SecondaryViewHolder holder, int position) {
        Album albumInfo = albums.get(position);


        holder.bindAppFeature(albums.get(position));

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class SecondaryViewHolder extends RecyclerView.ViewHolder {

        private ImageView secondary_image;


        public SecondaryViewHolder(View itemView) {
            super(itemView);
            secondary_image = (ImageView) itemView.findViewById(R.id.second_picture);

        }

        public void bindAppFeature(final Album album) {
            Picasso.with(itemView.getContext()).load(album.getPrimaryImageUrl()).into(secondary_image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    Intent intent = new Intent(itemView.getContext(), FinalActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("key1" , album.getId());
                    intent.putExtras(mBundle);
                    itemView.getContext().startActivity(intent);

                }
            });

        }
    }
}

