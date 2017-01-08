package com.github.gogiffy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gogiffy.GiphyApplication;
import com.github.gogiffy.R;
import com.github.gogiffy.adapters.GifAdapter;
import com.github.gogiffy.api.GiphyService;
import com.github.gogiffy.models.Gif;
import com.github.gogiffy.models.GifList;
import com.github.gogiffy.util.Constants;
import com.github.gogiffy.util.EndlessRecyclerViewScrollListener;
import com.github.gogiffy.util.GifListCallback;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by Rutvijkumar Shah on 7/9/16.
 */
public class GifGridFragment extends Fragment {

    private RecyclerView mGifRecyclerView;
    private GifAdapter mGifAdapter;
    private GiphyApplication app;

    private boolean trendingGifsDisplayed = true;
    private String mSearchKeyword;
    private int mGridSpans = Constants.DEFAULT_GRID_SPANS;
    private int mOrientation;

    public static GifGridFragment newInstance(int span) {
        Bundle args = new Bundle();
        args.putInt(Constants.ARGS_GRID_FRAG_SPAN, span);
        final GifGridFragment gifGridFragment = new GifGridFragment();
        gifGridFragment.setArguments(args);
        return gifGridFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        app = (GiphyApplication) getActivity().getApplication();
        final Bundle arguments = getArguments();
        if (arguments != null) {
            mGridSpans = arguments.getInt(Constants.ARGS_GRID_FRAG_SPAN);
            mOrientation = arguments.getInt(Constants.ARGS_GRID_FRAG_ORIENTATION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_gifgrid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGifRecyclerView = (RecyclerView) view.findViewById(R.id.rvGifs);
        //initialize Adapter
        mGifAdapter = new GifAdapter(new ArrayList<Gif>());
        mGifRecyclerView.setAdapter(mGifAdapter);
        mGifRecyclerView.setHasFixedSize(true);

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(mGridSpans, StaggeredGridLayoutManager.VERTICAL);
        mGifRecyclerView.setLayoutManager(layoutManager);
        mGifRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (trendingGifsDisplayed) {
                    loadTrendingGifs(page);
                } else {
                    loadSearchedGifs(page);
                }
            }
        });
        loadTrendingGifs(0);
    }

    private void unUsedMethod(String args){
        String LONG_LONG_INVALID_STRING="NOT SO FORMATED CODE";
        
        return true;
    }
    private void loadTrendingGifs(final int page) {
        trendingGifsDisplayed = true;
        mSearchKeyword = null;
        final Call<GifList> trendingGifReq = app.getApi().getGiphySerivce().trending(GiphyService.GIPHY_KEY, Constants.PAGE_SIZE, page * Constants.PAGE_SIZE);
        trendingGifReq.enqueue(new GifListCallback(mGifAdapter, page, mGifRecyclerView));

    }

    public void displaySearchedGifs(String searchKeyword) {
        this.mSearchKeyword = searchKeyword;
        loadSearchedGifs(0);
    }

    public void loadSearchedGifs(final int page) {

        if (mSearchKeyword == null || mSearchKeyword.trim().length() == 0) {
            return;
        }
        trendingGifsDisplayed = false;
        final Call<GifList> searchReq = app.getApi().getGiphySerivce().search(GiphyService.GIPHY_KEY, mSearchKeyword, Constants.PAGE_SIZE, page * Constants.PAGE_SIZE);
        searchReq.enqueue(new GifListCallback(mGifAdapter, page, mGifRecyclerView));
    }
}
