package com.hoony.androidsample.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@SuppressWarnings("unused")
@Dao
public interface BaseDao<T> {
    @Insert
    void insert(T obj);

    @Insert
    void insertAll(T[] obj);

    @Update
    void update(T obj);

    @Delete
    void delete(T obj);
}
