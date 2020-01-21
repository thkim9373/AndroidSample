package com.hoony.androidsample.room;

import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

import java.util.List;
import java.util.concurrent.Callable;

public class UserTask implements Callable<List<User>> {
    public static final int GET_ALL = 989;
    public static final int INSERT = 129;
    public static final int DELETE = 671;

    private final UserDao mUserDao;
    private final int tag;
    private User user;

    public UserTask(UserDao mUserDao, int tag) {
        this.mUserDao = mUserDao;
        this.tag = tag;
    }

    public UserTask(UserDao mUserDao, int tag, User user) {
        this.mUserDao = mUserDao;
        this.tag = tag;
        this.user = user;
    }

    interface UserTaskCallback {
        void onUserTaskComplete(List<User> userList);

        void onUserTaskFail(Exception e);
    }

    @Override
    public List<User> call() {
        switch (tag) {
            case GET_ALL:
                return mUserDao.getAll();
            case INSERT:
                mUserDao.insert(user);
                return mUserDao.getAll();
            case DELETE:
                mUserDao.delete(user);
                return mUserDao.getAll();
        }
        return mUserDao.getAll();
    }
}
