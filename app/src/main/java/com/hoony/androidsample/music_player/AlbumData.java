package com.hoony.androidsample.music_player;

import android.net.Uri;

class AlbumData {
    private Uri albumArtUri;
    private String artist;
    private int numOfSong;

    AlbumData(Uri albumArtUri, String artist, int numOfSong) {
        this.albumArtUri = albumArtUri;
        this.artist = artist;
        this.numOfSong = numOfSong;
    }

    Uri getAlbumArtUri() {
        return albumArtUri;
    }

    String getArtist() {
        return artist;
    }

    int getNumOfSong() {
        return numOfSong;
    }
}
