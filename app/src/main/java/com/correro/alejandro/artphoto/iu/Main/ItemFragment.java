package com.correro.alejandro.artphoto.iu.Main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.Database;
import com.correro.alejandro.artphoto.data.model.Artist;
import com.correro.alejandro.artphoto.iu.Detail.DetailActivity;

public class ItemFragment extends Fragment implements MyItemRecyclerViewAdapter.callback {

    ImageView imageView;
    private Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        database = Database.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_main, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.rvArtList);
        imageView = view.findViewById(R.id.ivArt);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(database.getArtists(), this));
        return view;
    }


    @Override
    public void changeImage(Artist item) {
        imageView.setImageResource(item.getAvatar());

    }

    @Override
    public void startActivity(Artist item) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("artist", item);
            startActivity(intent);
        }
    }
}
