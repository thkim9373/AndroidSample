package com.hoony.androidsample.room;

import android.app.Application;

import androidx.annotation.NonNull;

import com.hoony.androidsample.db.AppDatabase;
import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;
import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

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

    /*****************************************************
     ******************* User Task ***********************
     *****************************************************/

    void getAllUserListTask(@NonNull final UserTask.UserTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeUserTaskAsync(new UserTask(mUserDao, UserTask.GET_ALL), callback);
    }

    void insertUserTask(@NonNull final User user, @NonNull final UserTask.UserTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeUserTaskAsync(new UserTask(mUserDao, UserTask.INSERT, user), callback);
    }

    void deleteUserTask(@NonNull final User user, @NonNull final UserTask.UserTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeUserTaskAsync(new UserTask(mUserDao, UserTask.DELETE, user), callback);
    }

    /*****************************************************
     ******************* Pet Task ************************
     *****************************************************/

    void getAllPetListTask(@NonNull final PetTask.PetTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executePetTaskAsync(new PetTask(mPetDao, PetTask.GET_ALL), callback);
    }

    void insertPetTask(@NonNull Pet pet, @NonNull final PetTask.PetTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executePetTaskAsync(new PetTask(mPetDao, PetTask.INSERT, pet), callback);
    }

    void deletePetTask(@NonNull Pet pet, @NonNull final PetTask.PetTaskCallback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executePetTaskAsync(new PetTask(mPetDao, PetTask.DELETE, pet), callback);
    }
}
