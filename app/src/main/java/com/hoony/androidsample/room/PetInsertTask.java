package com.hoony.androidsample.room;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;

import java.util.List;
import java.util.concurrent.Callable;

public class PetInsertTask implements Callable<List<Pet>> {

    private final PetDao petDao;
    private final Pet pet;

    PetInsertTask(PetDao petDao, Pet pet) {
        this.petDao = petDao;
        this.pet = pet;
    }

    @Override
    public List<Pet> call() {
        petDao.insert(pet);
        return petDao.getAll();
    }

    interface PetInsertTaskCallback {
        void onPetInsertTaskComplete(List<Pet> petList);

        void onPetInsertTaskFail(Exception e);
    }
}

