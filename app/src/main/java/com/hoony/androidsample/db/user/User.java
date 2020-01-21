package com.hoony.androidsample.db.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "USER")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "name")
    private String name;

    public User(String name) {
        this.name = name;
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
