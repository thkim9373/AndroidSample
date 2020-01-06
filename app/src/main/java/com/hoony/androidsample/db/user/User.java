package com.hoony.androidsample.db.user;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "USER")
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "index")
    private int index;

    @ColumnInfo(name = "name")
    private String name;

    public User(String name) {
        this.name = name;
    }

    protected User(Parcel in) {
        index = in.readInt();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String toString() {
        return "User{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }

    public static User[] userData() {
        return new User[]{
                new User("Paul"),
                new User("Jane"),
                new User("John"),
                new User("Andrew"),
                new User("Derk")
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(name);
    }
}
