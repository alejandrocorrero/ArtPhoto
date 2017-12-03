package com.correro.alejandro.artphoto.iu.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;
import com.correro.alejandro.artphoto.data.utils.FragmentUtils;

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
        getIntentValues();
        loadFragment();
    }

    private void loadFragment() {
        ActivityDetailFragment frgDetail = ActivityDetailFragment.newInstance(artist);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.detailFrame, frgDetail);
        transaction.commit();
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        artist=intent.getParcelableExtra("artist");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
