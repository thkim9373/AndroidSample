package com.hoony.androidsample.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hoony.androidsample.db.AppDatabase;
import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.user.User;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private final AppDatabase appDatabase;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
    }

    private MutableLiveData<List<User>> userList = new MutableLiveData<>();

    MutableLiveData<List<User>> getUserList() {
        return userList;
    }

    void loadUserList() {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeAsync(new UserLoadingTask(appDatabase), new TaskRunner.Callback<List<User>>() {

            @Override
            public void onComplete(List<User> result) {
                userList.setValue(result);
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }

    private MutableLiveData<List<Pet>> petList = new MutableLiveData<>();

    public MutableLiveData<List<Pet>> getPetList() {
        return petList;
    }

    void loadPetList(int index) {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.executeAsync(new PetLoadingTask(appDatabase, index), new TaskRunner.Callback<List<Pet>>() {
            @Override
            public void onComplete(List<Pet> result) {
                petList.setValue(result);
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }
}
