package com.hoony.androidsample.room;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class TaskRunner {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    interface Callback<R> {
        void onComplete(R result);

        void onFail(Exception e);
    }

    <R> void executeAsync(final Callable<R> callable, final Callback<R> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final R result;
                try {
                    result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onComplete(result);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFail(e);
                }
            }
        });
    }
}
