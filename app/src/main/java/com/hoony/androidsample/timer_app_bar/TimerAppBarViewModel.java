package com.hoony.androidsample.timer_app_bar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TimerAppBarViewModel extends AndroidViewModel {

    public TimerAppBarViewModel(@NonNull Application application) {
        super(application);
        dateGapLiveData.setValue(0);
    }

    private MutableLiveData<Integer> dateGapLiveData = new MutableLiveData<>();

    MutableLiveData<Integer> getDateGapLiveData() {
        return dateGapLiveData;
    }

    void setYesterday() {
        int dateGap = this.dateGapLiveData.getValue() != null ?
                this.dateGapLiveData.getValue() : 0;
        this.dateGapLiveData.setValue(--dateGap);
    }

    void setTomorrow() {
        int dateGap = this.dateGapLiveData.getValue() != null ?
                this.dateGapLiveData.getValue() : 0;
        this.dateGapLiveData.setValue(++dateGap);
    }
}
