package com.hoony.androidsample.music_player;

import android.net.Uri;

public class AlbumData {
    private Uri albumArtUri;
    private String artist;
    private int numOfSong;

    public AlbumData(Uri albumArtUri, String artist, int numOfSong) {
        this.albumArtUri = albumArtUri;
        this.artist = artist;
        this.numOfSong = numOfSong;
    }

    public Uri getAlbumArtUri() {
        return albumArtUri;
    }

    public String getArtist() {
        return artist;
    }

    public int getNumOfSong() {
        return numOfSong;
    }
}
