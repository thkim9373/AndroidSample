package com.hoony.androidsample.db.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.hoony.androidsample.db.BaseDao;

import java.util.List;

@Dao
public interface UserDao extends BaseDao<User> {
    @Query("SELECT * FROM USER")
    List<User> getAll();
}
