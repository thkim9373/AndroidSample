package com.hoony.androidsample.db.user;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "USER")
public class User {
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
}
