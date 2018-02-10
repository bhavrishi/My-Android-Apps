package com.myonic.rishibhv.firebasepush2;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rishi on 12/11/17.
 */
@IgnoreExtraProperties
public class Artist {
    String artistId,artistGenre,artistName;

    public String getArtistId() {
        return artistId;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

    public String getArtistName() {
        return artistName;
    }
    public Artist(){

    }

    public Artist(String artistId, String artistName, String artistGenre) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }
}
