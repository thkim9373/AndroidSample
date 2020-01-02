package com.hoony.androidsample.media_data_list;

public class MediaData {

    private String id;
    private String albumId;
    private String artistId;
    private String displayName;
    private String title;
    private String albumArtist;

    public MediaData(String id, String albumId, String artistId, String displayName, String title, String albumArtist) {
        this.id = id;
        this.albumId = albumId;
        this.artistId = artistId;
        this.displayName = displayName;
        this.title = title;
        this.albumArtist = albumArtist;
    }

    public String getId() {
        return id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }
}
