package com.github.gogiffy.api;

import com.github.gogiffy.models.GifList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rutvijkumar Shah on 7/8/16.
 */
public interface GiphyService {

    public static String GIPHY_KEY = "dc6zaTOxFJmzC";


    @GET("/v1/gifs/search")
    Call<GifList> search(
            @Query("api_key") String apiKey,
            @Query("q") String query,
            @Query("limit") int limit,
            @Query("offset") int offset
    );


    @GET("/v1/gifs/trending")
    Call<GifList> trending(
            @Query("api_key") String apiKey,
            @Query("limit") int limit,
            @Query("offset") int offset
    );

}
