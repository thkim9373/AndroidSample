package com.hoony.androidsample.room;

import androidx.lifecycle.LiveData;

import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

public class RoomRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUser;

    LiveData<List<User>> getAllUser() {
        return mAllUser;
    }
}
