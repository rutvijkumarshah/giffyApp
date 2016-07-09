package com.github.gogiffy.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.gogiffy.R;
import com.github.gogiffy.fragments.GifGridFragment;
import com.github.gogiffy.util.Constants;

/**
 *
 * Created by Rutvijkumar Shah on 7/8/16.
 *
 */
public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupFragment();
    }

    private void setupFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        GifGridFragment gridFragment = (GifGridFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAG_TAG_GIFGRID);
        if(gridFragment == null){
            gridFragment=GifGridFragment.newInstance();
        }
        ft.replace(R.id.frm_fragment,gridFragment,Constants.FRAG_TAG_GIFGRID);
        ft.commit();
    }
}
