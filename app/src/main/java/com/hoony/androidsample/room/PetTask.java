package com.hoony.androidsample.room;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;

import java.util.List;
import java.util.concurrent.Callable;

public class PetTask implements Callable<List<Pet>> {
    public static final int GET_ALL = 42;
    public static final int GET_SELECTED_PETS = 583;
    public static final int INSERT = 953;
    public static final int DELETE = 357;

    private final PetDao mPetDao;
    private final int flag;
    private Pet mPet;
    private String userName;

    public PetTask(PetDao mPetDao, int flag, Pet mPet) {
        this.mPetDao = mPetDao;
        this.mPet = mPet;
        this.flag = flag;
    }

    public PetTask(PetDao mPetDao, int flag) {
        this.mPetDao = mPetDao;
        this.flag = flag;
    }

    public PetTask(PetDao mPetDao, int flag, String userName) {
        this.mPetDao = mPetDao;
        this.userName = userName;
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
            case GET_SELECTED_PETS:
                return mPetDao.getMatchingPetList(mPet.getUserName());
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
