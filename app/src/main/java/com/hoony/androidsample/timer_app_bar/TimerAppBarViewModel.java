package com.hoony.androidsample.timer_app_bar;

import android.app.Application;
import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimerAppBarViewModel extends AndroidViewModel {

    public TimerAppBarViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<String> dateMutableLiveData = new MutableLiveData<>();
    private int dateGap = 0;

    MutableLiveData<String> getDateMutableLiveData() {
        return dateMutableLiveData;
    }

    void setDate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, dateGap);

            String result = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(calendar.getTime());

            dateMutableLiveData.setValue(result);
        }
    }

    void setYesterday() {
        dateGap--;
        setDate();
    }

    void setTomorrow() {
        dateGap++;
        setDate();
    }
}
