package com.example.admin.album;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.album.datamodel.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/6/2018.
 */

public class ApiServiceFinal {
    private static final String TAG = "ApiService";
    private Context context;
    private int value;


    public ApiServiceFinal(Context context , int value){
        this.context=context;
        this.value = value;
    }

    public void getCurrentFinalAlbum(final ApiServiceFinal.OnFinalAlbumInfoRecieved  onFinalAlbumInfoRecieved ){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/photos",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Album> albums = new ArrayList<>();

                for(int i=value-1 ; i<value ; i++){
                    Album albumInfo = new Album();
                    try {


                        JSONObject albumJsonObject=response.getJSONObject(i);
                        albumInfo.setAlbumId(albumJsonObject.getInt("albumId"));
                        albumInfo.setId(albumJsonObject.getInt("id"));
                        albumInfo.setPrimaryTitle(albumJsonObject.getString("title"));
                        albumInfo.setPrimaryImageUrl(albumJsonObject.getString("url"));


                        albums.add(albumInfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i(TAG, "onResponse: "+response.toString());
                onFinalAlbumInfoRecieved.onRecieved(albums);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.toString());
                onFinalAlbumInfoRecieved.onRecieved(null);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public interface OnFinalAlbumInfoRecieved {
        void onRecieved(List<Album> album);
    }

}