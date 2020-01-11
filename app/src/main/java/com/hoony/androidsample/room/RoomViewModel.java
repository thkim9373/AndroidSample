package com.hoony.androidsample.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.room.POJO.CheckableUser;

import java.util.ArrayList;
import java.util.List;

public class RoomViewModel extends AndroidViewModel
        implements UserLoadingTask.Callback<List<User>>,
        PetLoadingTask.callback {

    public RoomViewModel(@NonNull Application application) {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
    }

    private final RoomRepository roomRepository;

    private MutableLiveData<List<CheckableUser>> userList = new MutableLiveData<>();
    private MutableLiveData<List<Pet>> petList = new MutableLiveData<>();

    MutableLiveData<List<CheckableUser>> getUserList() {
        return userList;
    }

    MutableLiveData<List<Pet>> getPetList() {
        return petList;
    }

    void loadUserList() {
        roomRepository.getUserList(RoomViewModel.this);
    }

    void loadPetList(int index) {
        roomRepository.getPetList(index, RoomViewModel.this);
    }

    void userCheck(int checkedIndex) {
        if (userList.getValue() == null) return;

        List<CheckableUser> checkableUserList = userList.getValue();

        for (CheckableUser user : checkableUserList) {
            if (user.isChecked()) {
                user.setChecked(false);
                break;
            }
        }

        checkableUserList.get(checkedIndex - 1).setChecked(true);
        userList.setValue(checkableUserList);
    }

    @Override
    public void onUserDataLoadingComplete(List<User> result) {

        List<CheckableUser> checkableUserList = new ArrayList<>();
        for (User user : result) {
            CheckableUser checkableUser = new CheckableUser(user.getName());
            checkableUser.setIndex(user.getIndex());
            checkableUser.setChecked(false);
            checkableUserList.add(checkableUser);
        }

        userList.setValue(checkableUserList);
    }

    @Override
    public void onUserDataLoadingFail(Exception e) {

    }

    @Override
    public void onPetListLoadingComplete(List<Pet> result) {
        petList.setValue(result);
    }

    @Override
    public void onPetListLoadingFail(Exception e) {

    }
}
