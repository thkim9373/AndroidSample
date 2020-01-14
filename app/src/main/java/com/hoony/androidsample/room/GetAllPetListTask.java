package com.hoony.androidsample.room;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public class GetAllPetListTask implements Callable<List<Pet>> {

    private final PetDao petDao;

    public GetAllPetListTask(PetDao petDao) {
        this.petDao = petDao;
    }

    @Override
    public List<Pet> call() throws Exception {
        return petDao.getAll();
    }

    interface GetAllPetListCallback {
        void onAllPetListGetComplete(List<Pet> petList);

        void onAllPetListGetFail(Exception e);
    }
}
