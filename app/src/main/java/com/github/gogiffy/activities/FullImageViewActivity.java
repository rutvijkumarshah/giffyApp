package com.github.gogiffy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.gogiffy.R;
import com.github.gogiffy.util.Constants;
import com.squareup.picasso.Picasso;

public class FullImageViewActivity extends AppCompatActivity {

    private String mUrl;
    private ImageView mImgView;
    private TextView mTvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);
        final Intent intent = getIntent();
        if (intent != null) {
            mUrl = intent.getExtras().getString(Constants.EXTRA_FULL_IMG_URL, null);
        }
        mImgView = (ImageView) findViewById(R.id.imgFullScreenImage);
        mTvUrl = (TextView) findViewById(R.id.tvURL);
        setTheme(R.style.FullScreenDialogTheme);
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Picasso.with(this)
                .load(mUrl)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_not_available)
                .into(mImgView);
        mTvUrl.setText(mUrl);
    }
}
