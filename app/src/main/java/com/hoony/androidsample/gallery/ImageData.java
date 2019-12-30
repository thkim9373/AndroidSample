package com.hoony.androidsample.gallery;

import android.net.Uri;

public class ImageData {
    private Uri uri;
    private String Name;

    public ImageData(Uri uri, String name) {
        this.uri = uri;
        Name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public String getName() {
        return Name;
    }
}
