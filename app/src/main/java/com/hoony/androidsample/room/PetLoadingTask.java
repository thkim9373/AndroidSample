package com.hoony.androidsample.room;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;

import java.util.List;
import java.util.concurrent.Callable;

public class PetLoadingTask implements Callable<List<Pet>> {

    private final PetDao petDao;
    private final int index;

    PetLoadingTask(PetDao petDao, int index) {
        this.petDao = petDao;
        this.index = index;
    }

    @Override
    public List<Pet> call() {
        return petDao.getAll(index);
    }

    interface callback {
        void onPetListLoadingComplete(List<Pet> result);

        void onPetListLoadingFail(Exception e);
    }

//    private final AppDatabase appDatabase;
//    private final int index;
//
//    PetLoadingTask(AppDatabase appDatabase, int index) {
//        this.appDatabase = appDatabase;
//        this.index = index;
//    }
//
//    @Override
//    public List<Pet> call() {
//        return appDatabase.petDao().getAll(index);
//    }
}
