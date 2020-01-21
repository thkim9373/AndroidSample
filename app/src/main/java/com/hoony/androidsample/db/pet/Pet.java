package com.hoony.androidsample.db.pet;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.hoony.androidsample.db.user.User;

@Entity(tableName = "PET",
        primaryKeys = {"user_name", "name"},
        indices = {@Index(value = "index")},
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "name",
                childColumns = "user_name",
                onDelete = ForeignKey.CASCADE))
public class Pet {
    @NonNull
    @ColumnInfo(name = "user_name")
    private String userName;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Pet(String userName, @NonNull String name) {
        this.userName = userName;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public static Pet[] petData() {
        return new Pet[]{
                new Pet("Paul", "A"),
                new Pet("Jane", "B"),
                new Pet("Jane", "C"),
                new Pet("John", "D"),
                new Pet("John", "E"),
                new Pet("John", "F"),
                new Pet("Andrew", "G"),
                new Pet("Andrew", "H"),
                new Pet("Andrew", "I"),
                new Pet("Andrew", "J"),
                new Pet("Derk", "K"),
                new Pet("Derk", "L"),
                new Pet("Derk", "M"),
                new Pet("Derk", "N"),
                new Pet("Derk", "O"),
        };
    }
}
