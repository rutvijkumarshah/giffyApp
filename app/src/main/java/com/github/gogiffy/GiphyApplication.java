package com.github.gogiffy;

import android.app.Application;

import com.github.gogiffy.api.Api;
import com.squareup.leakcanary.LeakCanary;
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

        LeakCanary.install(this); //only for testing debugging

    }

    private void setupPicasso() {

        Picasso picasso = new Picasso.Builder(this)
                .build();
        //picasso.setIndicatorsEnabled(true); // For debugging
        //picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }
    
    private void VERYVERYVERYBADMETHOD(){}

}
