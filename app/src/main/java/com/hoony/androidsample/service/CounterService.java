package com.hoony.androidsample.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

public class CounterService extends IntentService {

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    interface CounterServiceCallback {
        void onAdded();
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CounterService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
