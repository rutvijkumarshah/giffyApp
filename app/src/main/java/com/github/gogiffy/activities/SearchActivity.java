package com.github.gogiffy.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.github.gogiffy.R;
import com.github.gogiffy.fragments.GifGridFragment;
import com.github.gogiffy.util.Constants;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rutvijkumar Shah on 7/8/16.
 */
public class SearchActivity extends AppCompatActivity implements TextWatcher {

    private GifGridFragment gridFragment;
    private EditText mSearchText;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchText = (EditText) findViewById(R.id.etSearchGif);
        setupFragment();
        mSearchText.addTextChangedListener(this);
    }


    private void setupFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        gridFragment = (GifGridFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAG_TAG_GIFGRID);
        if (gridFragment == null) {
            gridFragment = GifGridFragment.newInstance(Constants.DEFAULT_GRID_SPANS);
        }
        ft.replace(R.id.frm_fragment, gridFragment, Constants.FRAG_TAG_GIFGRID);
        ft.commit();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void afterTextChanged(final Editable editable) {

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gridFragment.displaySearchedGifs(editable.toString());
            }
        }, Constants.SEARCH_DELAY_AFTER_CHANGED);
    }

}
