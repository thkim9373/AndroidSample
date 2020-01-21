package com.hoony.androidsample.room;

import android.os.Handler;
import android.os.Looper;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.user.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class TaskRunner {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    void executePetTaskAsync(final Callable<List<Pet>> callable, final PetTask.PetTaskCallback callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<Pet> result;
                try {
                    result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onPetTaskComplete(result);
                        }
                    });
                } catch (Exception e) {
                    callback.onPetTaskFail(e);
                }
            }
        });
    }

    void executeUserTaskAsync(final Callable<List<User>> callable, final UserTask.UserTaskCallback callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<User> result;
                try {
                    result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onUserTaskComplete(result);
                        }
                    });
                } catch (Exception e) {
                    callback.onUserTaskFail(e);
                }
            }
        });
    }
}
