package com.hoony.androidsample.room;

import com.hoony.androidsample.db.AppDatabase;
import com.hoony.androidsample.db.user.User;

import java.util.List;
import java.util.concurrent.Callable;

public class UserLoadingTask implements Callable<List<User>> {

    private final AppDatabase appDatabase;

    UserLoadingTask(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public List<User> call() {
        return appDatabase.userDao().getAll();
    }
}
