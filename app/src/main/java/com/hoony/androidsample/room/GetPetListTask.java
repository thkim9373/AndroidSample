package com.hoony.androidsample.room;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;

import java.util.List;
import java.util.concurrent.Callable;

public class GetPetListTask implements Callable<List<Pet>> {

    private final PetDao petDao;
    private final int index;

    GetPetListTask(PetDao petDao, int index) {
        this.petDao = petDao;
        this.index = index;
    }

    @Override
    public List<Pet> call() {
        return petDao.getMatchingPetList(index);
    }

    interface GetPetListTaskCallback {
        void onPetListLoadingComplete(List<Pet> result);

        void onPetListLoadingFail(Exception e);
    }
}
