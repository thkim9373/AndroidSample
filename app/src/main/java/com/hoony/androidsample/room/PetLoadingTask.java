package com.hoony.androidsample.room;

import com.hoony.androidsample.db.AppDatabase;
import com.hoony.androidsample.db.pet.Pet;

import java.util.List;
import java.util.concurrent.Callable;

public class PetLoadingTask implements Callable<List<Pet>> {

    private final AppDatabase appDatabase;
    private final int index;

    public PetLoadingTask(AppDatabase appDatabase, int index) {
        this.appDatabase = appDatabase;
        this.index = index;
    }

    @Override
    public List<Pet> call() {
        return appDatabase.petDao().getAll();
    }
}
