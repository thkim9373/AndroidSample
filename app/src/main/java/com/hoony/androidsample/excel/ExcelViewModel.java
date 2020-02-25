package com.hoony.androidsample.excel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hoony.androidsample.excel.pojo.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExcelViewModel extends AndroidViewModel {
    public ExcelViewModel(@NonNull Application application) {
        super(application);
        userListLiveData = new MutableLiveData<>();
        userListLiveData.setValue(new ArrayList<>());

        userLiveData = new MutableLiveData<>();
        userLiveData.setValue(new User());
    }

    private MutableLiveData<List<User>> userListLiveData;

    private MutableLiveData<User> userLiveData;

    MutableLiveData<List<User>> getUserListLiveData() {
        return userListLiveData;
    }

    MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    void setName(String name) {
        User user = this.userLiveData.getValue();
        if (user == null) return;
        user.setName(name);
    }

    User getUser() {
        User user = this.userLiveData.getValue();
        if (user == null || user.getName().equals("")) return null;

        String time;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd E a hh:mm:ss", Locale.getDefault());
        time = dateFormat.format(new Date());

        user.setInputTime(time);

        this.userLiveData.setValue(new User());

        return user;
    }
}
