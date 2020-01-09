package com.hoony.androidsample.room;

import android.app.Application;

import com.hoony.androidsample.db.AppDatabase;
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

    void getUserList(UserLoadingTask.Callback<List<User>> callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeUserTaskAsync(new UserLoadingTask(mUserDao), callback);
    }

    void getPetList(final int index, final PetLoadingTask.callback callback) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executePetTaskAsync(new PetLoadingTask(mPetDao, index), callback);
    }
}
