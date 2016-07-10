package com.github.gogiffy.util;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.github.gogiffy.R;

import com.github.gogiffy.adapters.ResultUpdater;
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
    private ResultUpdater mResultUpdater;

    public GifListCallback(ResultUpdater resultUpdater, int page, RecyclerView recyclerView){
        this.mWKRecyclerView=new WeakReference<>(recyclerView);
        this.page=page;
        this.mResultUpdater=resultUpdater;
    }
    @Override
    public void onResponse(Call<GifList> call, Response<GifList> response) {
        final GifList list = response.body();
        if(page == 0) {
            //adapter.addNewResults(list.getData());
            mResultUpdater.clearAll();
            mResultUpdater.addAll(list.getData());
        }else{
            mResultUpdater.addAll(list.getData());
        }
    }

    @Override
    public void onFailure(Call<GifList> call, Throwable t) {
        Log.d(Constants.TAG, "Error : "+t.getLocalizedMessage());
        final RecyclerView rv = mWKRecyclerView.get();
        if(rv !=null) {
            final String neworkError = rv.getContext().getResources().getString(R.string.network_error);
            Snackbar.make(rv, neworkError, Snackbar.LENGTH_SHORT)
                    .setDuration(4000).show();
        }
    }
}
