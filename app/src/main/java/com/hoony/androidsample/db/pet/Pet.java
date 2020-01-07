package com.hoony.androidsample.db.pet;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.hoony.androidsample.db.user.User;

@Entity(tableName = "PET",
        primaryKeys = {"index", "name", "kind"},
        indices = {@Index(value = "index")},
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "index",
                childColumns = "index",
                onDelete = ForeignKey.CASCADE))
public class Pet {
    @ColumnInfo(name = "index")
    private int index;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "kind")
    private String kind;

    Pet(int index, String name, String kind) {
        this.index = index;
        this.name = name;
        this.kind = kind;
    }

    int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public static Pet[] petData() {
        return new Pet[] {
                new Pet(0, "A", "Cat"),
                new Pet(1, "B", "Dog"),
                new Pet(1, "C", "Cat"),
                new Pet(2, "D", "Dog"),
                new Pet(2, "E", "Dog"),
                new Pet(2, "F", "Cat"),
                new Pet(3, "G", "Camel"),
                new Pet(3, "H", "Dog"),
                new Pet(3, "I", "Fish"),
                new Pet(3, "J", "Cat"),
                new Pet(4, "K", "Cat"),
                new Pet(4, "L", "Dog"),
                new Pet(4, "M", "Fish"),
                new Pet(4, "N", "Bird"),
                new Pet(4, "O", "Horse"),
        };
    }
}
