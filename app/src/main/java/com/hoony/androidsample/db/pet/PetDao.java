package com.hoony.androidsample.db.pet;

import androidx.room.Dao;
import androidx.room.Query;

import com.hoony.androidsample.db.BaseDao;

import java.util.List;

@Dao
public interface PetDao extends BaseDao<Pet> {
    @Query("SELECT * FROM PET AS p WHERE p.`index` = :index")
    List<Pet> getAll(int index);
}
