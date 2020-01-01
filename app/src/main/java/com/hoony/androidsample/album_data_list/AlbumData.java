package com.hoony.androidsample.album_data_list;

public class AlbumData {
    private String albumArt;
    private String id;
    private String album;
    private String albumKey;
    private String artist;
    private String artistId;
    private String artistKey;
    private String firstYear;
    private String lastYear;
    private String numberOfSongs;

    AlbumData(String albumArt, String id, String album, String albumKey, String artist, String artistId, String artistKey, String firstYear, String lastYear, String numberOfSongs) {
        this.albumArt = albumArt;
        this.id = id;
        this.album = album;
        this.albumKey = albumKey;
        this.artist = artist;
        this.artistId = artistId;
        this.artistKey = artistKey;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.numberOfSongs = numberOfSongs;
    }

    String getAlbumArt() {
        return albumArt;
    }

    public String getId() {
        return id;
    }

    String getAlbum() {
        return album;
    }

    String getAlbumKey() {
        return albumKey;
    }

    String getArtist() {
        return artist;
    }

    String getArtistId() {
        return artistId;
    }

    String getArtistKey() {
        return artistKey;
    }

    String getFirstYear() {
        return firstYear;
    }

    String getLastYear() {
        return lastYear;
    }

    String getNumberOfSongs() {
        return numberOfSongs;
    }

}
