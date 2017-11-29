package com.correro.alejandro.artphoto.iu.Main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.correro.alejandro.artphoto.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainFrame)
    FrameLayout huec222o;
    //Todo cambiar posicion horizontal que cargue 2 fragmento,boton atras listview,flecha atras,guardar estados
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, new ItemFragment());
        transaction.commit();

    }
}
