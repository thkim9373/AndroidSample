package com.hoony.androidsample.room.POJO;

import android.widget.Checkable;

import com.hoony.androidsample.db.user.User;

public class CheckableUser extends User implements Checkable {

    public CheckableUser(String name) {
        super(name);
    }

    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}
