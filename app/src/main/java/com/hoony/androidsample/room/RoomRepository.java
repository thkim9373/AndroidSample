package com.hoony.androidsample.room;

import android.app.Application;

import com.hoony.androidsample.db.AppDatabase;
import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;
import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

import java.util.List;

class RoomRepository {

    private static RoomRepository INSTANCE;

    private UserDao mUserDao;

    private PetDao mPetDao;

    static RoomRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RoomRepository(application);
        }
        return INSTANCE;
    }

    private RoomRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        mUserDao = database.userDao();
        mPetDao = database.petDao();
    }

    void getUserList(GetUserListTask.Callback<List<User>> callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeUserTaskAsync(new GetUserListTask(mUserDao), callback);
    }

    void getPetList(final int index, final GetPetListTask.GetPetListTaskCallback GetPetListTaskCallback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executePetTaskAsync(new GetPetListTask(mPetDao, index), GetPetListTaskCallback);
    }

    void getAllPetList(final GetAllPetListTask.GetAllPetListCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeGetAllPetListAsync(new GetAllPetListTask(mPetDao), callback);
    }

    void insertPet(Pet pet, final PetInsertTask.PetInsertTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executePetInsertTaskAsync(new PetInsertTask(mPetDao, pet), callback);
    }

    void deletePet(Pet pet, final PetDeleteTask.PetDeleteTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executePetDeleteTaskAsync(new PetDeleteTask(mPetDao, pet), callback);
    }
}
