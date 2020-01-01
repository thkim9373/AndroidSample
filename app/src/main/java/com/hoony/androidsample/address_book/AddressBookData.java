package com.hoony.androidsample.address_book;

class AddressBookData {
    private String contactLastUpdatedTimestamp;
    private String displayName;
    private String number;
    private String hasPhoneNumber;
    private String inDefaultDirectory;
    private String inVisibleGroup;
    private String lookupKey;
    private String nameRawContactId;
    private String photoFileId;
    private String photoId;
    private String photoThumbnailUri;
    private String photoUri;

    AddressBookData(String contactLastUpdatedTimestamp, String displayName, String number, String hasPhoneNumber, String inDefaultDirectory, String inVisibleGroup, String lookupKey, String nameRawContactId, String photoFileId, String photoId, String photoThumbnailUri, String photoUri) {
        this.contactLastUpdatedTimestamp = contactLastUpdatedTimestamp;
        this.displayName = displayName;
        this.number = number;
        this.hasPhoneNumber = hasPhoneNumber;
        this.inDefaultDirectory = inDefaultDirectory;
        this.inVisibleGroup = inVisibleGroup;
        this.lookupKey = lookupKey;
        this.nameRawContactId = nameRawContactId;
        this.photoFileId = photoFileId;
        this.photoId = photoId;
        this.photoThumbnailUri = photoThumbnailUri;
        this.photoUri = photoUri;
    }

    String getContactLastUpdatedTimestamp() {
        return contactLastUpdatedTimestamp;
    }

    String getDisplayName() {
        return displayName;
    }

    String getNumber() {
        return number;
    }

    String getHasPhoneNumber() {
        return hasPhoneNumber;
    }

    String getInDefaultDirectory() {
        return inDefaultDirectory;
    }

    String getInVisibleGroup() {
        return inVisibleGroup;
    }

    String getLookupKey() {
        return lookupKey;
    }

    String getNameRawContactId() {
        return nameRawContactId;
    }

    String getPhotoFileId() {
        return photoFileId;
    }

    String getPhotoId() {
        return photoId;
    }

    String getPhotoThumbnailUri() {
        return photoThumbnailUri;
    }

    String getPhotoUri() {
        return photoUri;
    }
}
