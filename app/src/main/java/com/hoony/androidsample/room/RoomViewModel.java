package com.hoony.androidsample.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hoony.androidsample.db.AppDatabase;
import com.hoony.androidsample.db.user.User;

import java.util.List;
import java.util.concurrent.Executors;

public class RoomViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
    }

    private MutableLiveData<List<User>> userList = new MutableLiveData<>();

    MutableLiveData<List<User>> getUserList() {
        return userList;
    }

    void loadUserList() {
        Executors.newSingleThreadExecutor().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        List<User> userList = appDatabase.userDao().getAll();


                        RoomViewModel.this.userList.setValue(userList);
                    }
                }
        );
    }
}
