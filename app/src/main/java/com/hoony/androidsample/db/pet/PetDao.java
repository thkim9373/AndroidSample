package com.hoony.androidsample.db.pet;

import androidx.room.Dao;
import androidx.room.Query;

import com.hoony.androidsample.db.BaseDao;

import java.util.List;

@Dao
public interface PetDao extends BaseDao<Pet> {
    @Query("SELECT * FROM PET")
    List<Pet> getAll();
}
