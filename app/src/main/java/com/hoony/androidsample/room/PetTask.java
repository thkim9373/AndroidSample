package com.hoony.androidsample.room;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;

import java.util.List;
import java.util.concurrent.Callable;

public class PetTask implements Callable<List<Pet>> {
    static final int GET_ALL = 42;
    static final int INSERT = 953;
    static final int DELETE = 357;

    private final PetDao mPetDao;
    private final int flag;
    private Pet mPet;

    PetTask(PetDao mPetDao, int flag, Pet mPet) {
        this.mPetDao = mPetDao;
        this.mPet = mPet;
        this.flag = flag;
    }

    PetTask(PetDao mPetDao, int flag) {
        this.mPetDao = mPetDao;
        this.flag = flag;
    }

    interface PetTaskCallback {
        void onPetTaskComplete(List<Pet> petList);

        void onPetTaskFail(Exception e);
    }

    @Override
    public List<Pet> call() {
        switch (flag) {
            case GET_ALL:
                return mPetDao.getAll();
            case INSERT:
                mPetDao.insert(mPet);
                return mPetDao.getAll();
            case DELETE:
                mPetDao.delete(mPet);
                return mPetDao.getAll();
        }

        return null;
    }
}
