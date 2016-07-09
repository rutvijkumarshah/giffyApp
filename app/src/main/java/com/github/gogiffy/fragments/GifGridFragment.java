package com.github.gogiffy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gogiffy.GiphyApplication;
import com.github.gogiffy.R;
import com.github.gogiffy.adapters.GifAdapter;
import com.github.gogiffy.api.GiphyService;
import com.github.gogiffy.models.Gif;
import com.github.gogiffy.models.GifList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rutvijkumar Shah on 7/9/16.
 */
public class GifGridFragment extends Fragment {

    private RecyclerView mGifRecyclerView;
    private GifAdapter mGifAdapter;
    private GiphyApplication app;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        app= (GiphyApplication) getActivity().getApplication();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         return inflater.inflate(R.layout.fragment_gifgrid,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGifRecyclerView=(RecyclerView) view.findViewById(R.id.rvGifs);
        //initialize Adapter
        mGifAdapter=new GifAdapter(new ArrayList<Gif>());
        mGifRecyclerView.setAdapter(mGifAdapter);
        mGifRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadTrendingGifs();
    }

    private void loadTrendingGifs() {
        final Call<GifList> search = app.getApi().getGiphySerivce().trending(GiphyService.GIPHY_KEY, 10, 0);
        search.enqueue(new Callback<GifList>() {
            @Override
            public void onResponse(Call<GifList> call, Response<GifList> response) {
                final GifList list = response.body();
                mGifAdapter.addAll(list.getData());
            }

            @Override
            public void onFailure(Call<GifList> call, Throwable t) {

            }
        });
    }

    public static GifGridFragment newInstance() {
        return new GifGridFragment();
    }
}
