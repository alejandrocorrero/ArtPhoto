package com.correro.alejandro.artphoto.iu.Main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
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

public class MainFragment extends Fragment implements MyItemRecyclerViewAdapter.callback {

    ImageView imageView;
    private Database database;
    Callback listener;
    Artist artist;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        database = Database.getInstance();
        getArgumentsValues();
    }

    private void getArgumentsValues() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            artist = bundle.getParcelable("artist");
        }
    }

    public interface Callback {
        void onElementClick(Artist artist);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_main, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.rvArtList);
        imageView = view.findViewById(R.id.ivArt);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        imageView.setImageResource(artist.getAvatar());
        viewModel.setActualArtist(artist);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(database.getArtists(), this, artist));
        recyclerView.scrollToPosition(database.getArtistPosition(viewModel.getActualArtist()));
        return view;
    }

    //Save item and set it, when click it
    @Override
    public void clickItemAdapter(Artist artist) {
        this.artist = artist;
        viewModel.setActualArtist(artist);
        imageView.setImageResource(artist.getAvatar());
        listener.onElementClick(artist);

    }

    //Start activity detail  when orientation is portrait
    @Override
    public void startActivityCallback(Artist artist) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("artist", artist);
            startActivity(intent);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " Should implement the interface callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static MainFragment newInstance(Artist artist) {
        MainFragment frg = new MainFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable("artist", artist);
        frg.setArguments(arguments);
        return frg;
    }

}
