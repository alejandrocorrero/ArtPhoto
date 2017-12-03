package com.correro.alejandro.artphoto.iu.Main;
import android.arch.lifecycle.ViewModel;

import com.correro.alejandro.artphoto.data.Database;
import com.correro.alejandro.artphoto.data.model.Artist;

public class MainActivityViewModel extends ViewModel {
    public Artist getActualArtist() {
        return actualArtist;
    }

    public void setActualArtist(Artist actualArtist) {
        this.actualArtist = actualArtist;
    }

    public Artist actualArtist= Database.getInstance().getArtistWithPosition(0);
}
