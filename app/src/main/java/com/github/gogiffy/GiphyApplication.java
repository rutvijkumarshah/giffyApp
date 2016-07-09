package com.github.gogiffy;

import android.app.Application;

import com.github.gogiffy.api.Api;


/**
 *
 * Created by Rutvijkumar Shah on 7/9/16.
 *
 */
public class GiphyApplication extends Application {

    private  Api api;

    @Override
    public void onCreate() {
        super.onCreate();
        setup();
    }

    public Api getApi(){
        return api;
    }

    private void setup() {
        //Setup API Endpoint
        api=new Api();

    }

}
