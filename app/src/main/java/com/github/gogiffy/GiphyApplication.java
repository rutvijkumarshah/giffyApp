package com.github.gogiffy;

import android.app.Application;

import com.github.gogiffy.api.Api;
import com.squareup.picasso.Picasso;


/**
 * Created by Rutvijkumar Shah on 7/9/16.
 */
public class GiphyApplication extends Application {

    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();
        setup();
    }

    public Api getApi() {
        return api;
    }

    private void setup() {
        //Setup API Endpoint
        api = new Api();
        //setup Picasso
        setupPicasso();

    }

    private void setupPicasso() {
        /**Cache diskCache = new Cache(getDir(CacheConstants.DISK_CACHE_DIRECTORY, Context.MODE_PRIVATE), CacheConstants.DISK_CACHE_SIZE);
         OkHttpClient okHttpClient = new OkHttpClient();
         okHttpClient.setCache(diskCache);
         */

        Picasso picasso = new Picasso.Builder(this)
                .build();
        picasso.setIndicatorsEnabled(true); // For debugging
        picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }


}
