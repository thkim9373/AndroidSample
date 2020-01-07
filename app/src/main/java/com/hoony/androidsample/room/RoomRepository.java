package com.hoony.androidsample.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hoony.androidsample.db.AppDatabase;
import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

import java.util.List;

public class RoomRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mAllUser;

    public RoomRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        mUserDao = database.userDao();
//        mAllUser = mUserDao.getAll();
    }

    LiveData<List<User>> getAllUser() {
        return mAllUser;
    }
}
