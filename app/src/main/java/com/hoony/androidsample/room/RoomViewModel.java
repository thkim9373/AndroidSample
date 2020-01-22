package com.hoony.androidsample.room;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.room.POJO.CheckableUser;

import java.util.ArrayList;
import java.util.List;

public class RoomViewModel extends AndroidViewModel
        implements UserTask.UserTaskCallback, PetTask.PetTaskCallback {

    public RoomViewModel(@NonNull Application application) {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
    }

    private final RoomRepository roomRepository;

    private MutableLiveData<List<CheckableUser>> userList = new MutableLiveData<>();
    private MutableLiveData<List<Pet>> selectedPetList = new MutableLiveData<>();
    private MutableLiveData<List<Pet>> allPetList = new MutableLiveData<>();

    MutableLiveData<List<CheckableUser>> getUserList() {
        return userList;
    }

    MutableLiveData<List<Pet>> getSelectedPetList() {
        return selectedPetList;
    }

    MutableLiveData<List<Pet>> getAllPetList() {
        return allPetList;
    }

    void changeSelectedUser(int position) {
        if (isNull(userList.getValue())) return;
        if (getCheckedUser() != null) getCheckedUser().setChecked(false);
        List<CheckableUser> checkableUserList = userList.getValue();
        checkableUserList.get(position).setChecked(true);
        userList.setValue(checkableUserList);

        setSelectedPetList();
    }

    /*****************************************************
     ******************* User Task ***********************
     *****************************************************/

    void loadAllUserList() {
        roomRepository.getAllUserListTask(RoomViewModel.this);
    }

    void insertUser(String name) {
        roomRepository.insertUserTask(new User(name), RoomViewModel.this);
    }

    void deleteUser() {
        if (isNull(getCheckedUser())) return;
        roomRepository.deleteUserTask(new User(getCheckedUser().getName()), RoomViewModel.this);
    }

    /*****************************************************
     ******************* Pet Task ************************
     *****************************************************/

    void loadAllPetList() {
        roomRepository.getAllPetListTask(RoomViewModel.this);
    }

    void insertPet(String petName) {
        if (isNull(getCheckedUser())) return;
        roomRepository.insertPetTask(new Pet(getCheckedUser().getName(), petName), RoomViewModel.this);
    }

    void deletePet(String petName) {
        if (isNull(getCheckedUser())) return;
        roomRepository.deletePetTask(new Pet(getCheckedUser().getName(), petName), RoomViewModel.this);
    }

    @Override
    public void onPetTaskComplete(List<Pet> petList) {
        allPetList.setValue(petList);

        setSelectedPetList();
    }

    private void setSelectedPetList() {
        List<Pet> selectedPetList = new ArrayList<>();
        if (!isNull(getCheckedUser()) && !isNull(allPetList.getValue())) {
            for (Pet pet : allPetList.getValue()) {
                if (TextUtils.equals(pet.getUserName(), getCheckedUser().getName())) {
                    selectedPetList.add(pet);
                }
            }
        }
        this.selectedPetList.setValue(selectedPetList);
    }

    @Override
    public void onPetTaskFail(Exception e) {

    }

    @Override
    public void onUserTaskComplete(List<User> userList) {
        List<CheckableUser> checkableUserList = convertCheckableUserList(userList);
        this.userList.setValue(checkableUserList);

        loadAllPetList();
    }

    @Override
    public void onUserTaskFail(Exception e) {

    }

    private List<CheckableUser> convertCheckableUserList(List<User> userList) {
        List<CheckableUser> result = new ArrayList<>();

        CheckableUser checkedUser = getCheckedUser();
        if (!isNull(checkedUser)) {
            for (User user : userList) {
                CheckableUser checkableUser = new CheckableUser(user.getName());

                if (TextUtils.equals(checkedUser.getName(), checkableUser.getName())) {
                    checkableUser.setChecked(true);
                }

                result.add(checkableUser);
            }
        } else {
            for (User user : userList) {
                result.add(new CheckableUser(user.getName()));
            }
        }

        return result;
    }

    private CheckableUser getCheckedUser() {
        if (isNull(userList.getValue())) return null;

        for (CheckableUser checkableUser : this.userList.getValue()) {
            if (checkableUser.isChecked()) {
                return checkableUser;
            }
        }
        return null;
    }

    private boolean isNull(Object o) {
        return o == null;
    }
}
