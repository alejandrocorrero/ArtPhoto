package com.correro.alejandro.artphoto.iu.Main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;
import com.correro.alejandro.artphoto.iu.Detail.ActivityDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements  MyItemRecyclerViewAdapter.callback{

    @BindView(R.id.mainFrame)
    FrameLayout mainFrame;

    FrameLayout FrameDetail;
    private FragmentManager mGestorFragmentos;

    //Todo cambiar posicion horizontal que cargue 2 fragmento,boton atras listview,flecha atras,guardar estados
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, new ItemFragment());
        transaction.commit();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            ActivityDetailFragment frgDetail = ActivityDetailFragment.newInstance(new Artist("Test", "alex", "2020", R.drawable.cat3));
            FrameDetail = findViewById(R.id.FrameDetail);
            transaction2.add(R.id.FrameDetail, frgDetail);
            transaction2.commit();

        }


    }

    @Override
    public void changeImage(Artist item) {
        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
        ActivityDetailFragment frgDetail = ActivityDetailFragment.newInstance(item);
        FrameDetail = findViewById(R.id.FrameDetail);
        transaction3.add(R.id.FrameDetail, frgDetail);
        transaction3.commit();
    }

    @Override
    public void startActivity(Artist item) {

    }
}
