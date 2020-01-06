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

    public Pet(int index, String name, String kind) {
        this.index = index;
        this.name = name;
        this.kind = kind;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }
}
