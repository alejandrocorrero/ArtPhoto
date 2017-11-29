package com.correro.alejandro.artphoto.iu.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detailFrame)
    FrameLayout detailFrame;
    Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        artist=intent.getParcelableExtra("artist");
        //obtainData();
        ActivityDetailFragment frgDetail = ActivityDetailFragment.newInstance(artist);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.detailFrame, frgDetail);
        transaction.commit();
    }

    private void obtainData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (intent.hasExtra("artist")) {
                    artist=extras.getParcelable("artist");

                }
            }
        }
    }
}
