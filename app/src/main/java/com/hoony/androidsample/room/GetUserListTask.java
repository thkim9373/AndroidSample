package com.hoony.androidsample.room;

import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

import java.util.List;
import java.util.concurrent.Callable;

public class GetUserListTask implements Callable<List<User>> {

    private final UserDao userDao;

    GetUserListTask(UserDao userDao) {
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
//    GetUserListTask(AppDatabase appDatabase) {
//        this.appDatabase = appDatabase;
//    }
//
//    @Override
//    public List<User> call() {
//        return appDatabase.userDao().getMatchingPetList();
//    }
}
