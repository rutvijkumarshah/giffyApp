package com.github.gogiffy.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rutvijkumar Shah on 7/8/16.
 */
public class Api {

    public final static String GIPHY_BASE_URL = "http://api.giphy.com/";
    private Retrofit retrofit;
    private GiphyService service;

    public Api() {
        init();
    }

    private void init() {

        if (service == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(GIPHY_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(GiphyService.class);
        }
    }

    public GiphyService getGiphySerivce() {
        return service;
    }

}
