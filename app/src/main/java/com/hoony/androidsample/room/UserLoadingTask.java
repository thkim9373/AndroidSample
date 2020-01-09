package com.hoony.androidsample.room;

import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

import java.util.List;
import java.util.concurrent.Callable;

public class UserLoadingTask implements Callable<List<User>> {

    private final UserDao userDao;

    UserLoadingTask(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> call() {
        return userDao.getAll();
    }

    interface Callback<R> {
        void onUserDataLoadingComplete(R result);

        void onUserDataLoadingFail(Exception e);
    }

//    private final AppDatabase appDatabase;
//
//    UserLoadingTask(AppDatabase appDatabase) {
//        this.appDatabase = appDatabase;
//    }
//
//    @Override
//    public List<User> call() {
//        return appDatabase.userDao().getAll();
//    }
}
