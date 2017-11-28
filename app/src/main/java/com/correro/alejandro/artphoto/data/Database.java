package com.correro.alejandro.artphoto.data;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;

import java.util.ArrayList;

public class Database {

    private static Database instance;

    private ArrayList<Artist> artists;

    private Database() {

        artists = new ArrayList<Artist>();


       artists.add(new Artist("La luz del mundo","Simon Funk","2017",R.drawable.ic_launcher_background));
        artists.add(new Artist("La luz del mundo2","Simon Funk","2017",R.drawable.ic_launcher_background));
        artists.add(new Artist("La luz del mundo3","Simon Funk","2017",R.drawable.ic_launcher_background));
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void addUser(Artist artist) {
        artists.add(artist);
    }

    public void updateUser(Artist artist, int position) {
        artists.set(position, artist);
    }


    public int getUserPosition(Artist artist){
        return artists.indexOf(artist);
    }
}
