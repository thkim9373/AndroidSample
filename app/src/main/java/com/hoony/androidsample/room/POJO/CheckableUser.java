package com.hoony.androidsample.room.POJO;

import android.widget.Checkable;

import com.hoony.androidsample.db.user.User;

public class CheckableUser extends User implements Checkable {

    private boolean isCheck = false;

    public CheckableUser(String name) {
        super(name);
    }

    @Override
    public void setChecked(boolean checked) {
        this.isCheck = checked;
    }

    @Override
    public boolean isChecked() {
        return this.isCheck;
    }

    @Override
    public void toggle() {
        this.isCheck = !this.isCheck;
    }
}
