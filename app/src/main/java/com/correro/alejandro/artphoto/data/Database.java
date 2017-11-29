package com.correro.alejandro.artphoto.data;

import com.correro.alejandro.artphoto.R;
import com.correro.alejandro.artphoto.data.model.Artist;

import java.util.ArrayList;

public class Database {

    private static Database instance;

    private ArrayList<Artist> artists;

    private Database() {

        artists = new ArrayList<Artist>();


        artists.add(new Artist("Resacon en las vegas", "Jack Napier", "2015", R.drawable.cat1));
        artists.add(new Artist("Navidad", "Familia", "2017", R.drawable.cat2));
        artists.add(new Artist("Black mirror", "Simon Funk", "2015", R.drawable.cat3));
        artists.add(new Artist("Puente de diciembre", "Alejandro", "2017", R.drawable.cat4));
        artists.add(new Artist("Madrugadas", "Alejandro", "2000", R.drawable.cat5));
        artists.add(new Artist("Practicas android", "Pedro", "2017", R.drawable.cat6));
        artists.add(new Artist("Examen android", "Pedro", "2017", R.drawable.cat7));

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


    public int getUserPosition(Artist artist) {
        return artists.indexOf(artist);
    }
}
