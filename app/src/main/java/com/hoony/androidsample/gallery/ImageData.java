package com.hoony.androidsample.gallery;

import android.net.Uri;

class ImageData {
    private Uri uri;
    private String Name;

    ImageData(Uri uri, String name) {
        this.uri = uri;
        Name = name;
    }

    Uri getUri() {
        return uri;
    }

    String getName() {
        return Name;
    }
}
