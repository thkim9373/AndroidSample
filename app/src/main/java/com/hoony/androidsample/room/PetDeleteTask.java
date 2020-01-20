package com.hoony.androidsample.room;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;

import java.util.List;
import java.util.concurrent.Callable;

public class PetDeleteTask implements Callable<List<Pet>> {

    private final PetDao petDao;
    private final Pet pet;

    PetDeleteTask(PetDao petDao, Pet pet) {
        this.petDao = petDao;
        this.pet = pet;
    }

    @Override
    public List<Pet> call() {
        petDao.delete(pet);
        return petDao.getAll();
    }

    interface PetDeleteTaskCallback {
        void onPetDeleteTaskComplete(List<Pet> petList);

        void onPetDeleteTaskFail(Exception e);
    }
}

