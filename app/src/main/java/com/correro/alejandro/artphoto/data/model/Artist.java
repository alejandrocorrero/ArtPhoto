package com.correro.alejandro.artphoto.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Artist implements Parcelable {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String name;
    private String artist;
    private String year;
    private int avatar;

    public Artist(String name, String artist, String year, int avatar) {
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.avatar = avatar;
    }

    protected Artist(Parcel in) {
        this.name = in.readString();
        this.artist = in.readString();
        this.year = in.readString();
        this.avatar = in.readInt();
        
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.artist);
        dest.writeString(this.year);
        dest.writeInt(this.avatar);
    }


    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
