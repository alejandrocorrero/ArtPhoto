package com.correro.alejandro.artphoto.iu.Main;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.correro.alejandro.artphoto.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
        transaccion.replace(R.id.huec222o, new ItemFragment());
        transaccion.commit();
    }
}
