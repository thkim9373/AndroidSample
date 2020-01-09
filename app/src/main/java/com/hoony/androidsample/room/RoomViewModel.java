package com.hoony.androidsample.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.user.User;

import java.util.List;

public class RoomViewModel extends AndroidViewModel
        implements UserLoadingTask.Callback<List<User>>,
        PetLoadingTask.callback {

    private final RoomRepository roomRepository;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
    }

    private MutableLiveData<List<User>> userList = new MutableLiveData<>();

    MutableLiveData<List<User>> getUserList() {
        return userList;
    }

    void loadUserList() {
        roomRepository.getUserList(RoomViewModel.this);
    }

    private MutableLiveData<List<Pet>> petList = new MutableLiveData<>();

    MutableLiveData<List<Pet>> getPetList() {
        return petList;
    }

    void loadPetList(int index) {
        roomRepository.getPetList(index, RoomViewModel.this);
    }

    @Override
    public void onUserDataLoadingComplete(List<User> result) {
        userList.setValue(result);
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
