package com.github.gogiffy.util;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.github.gogiffy.adapters.GifAdapter;
import com.github.gogiffy.models.GifList;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rutvijkumar Shah on 7/9/16.
 */
public class GifListCallback implements Callback<GifList> {

    private final int page;
    private WeakReference<RecyclerView> mWKRecyclerView;
    private GifAdapter adapter;

    public GifListCallback(GifAdapter adapter, int page, RecyclerView recyclerView){
        this.mWKRecyclerView=new WeakReference<RecyclerView>(recyclerView);
        this.page=page;
        this.adapter=adapter;
    }
    @Override
    public void onResponse(Call<GifList> call, Response<GifList> response) {
        final GifList list = response.body();
        if(page == 0) {
            adapter.addNewResults(list.getData());
        }else{
            adapter.addAll(list.getData());
        }

    }

    @Override
    public void onFailure(Call<GifList> call, Throwable t) {
        final RecyclerView rv = mWKRecyclerView.get();
        if(rv !=null) {
            Snackbar.make(rv, "Hello SnackBar!", Snackbar.LENGTH_SHORT)
                    .setDuration(4000).show();
        }
    }
}
