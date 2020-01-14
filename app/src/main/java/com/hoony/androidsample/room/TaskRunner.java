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

    void executeUserTaskAsync(final Callable<List<User>> callable, final GetUserListTask.Callback<List<User>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<User> result;
                try {
                    result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onUserDataLoadingComplete(result);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onUserDataLoadingFail(e);
                }
            }
        });
    }

    void executePetTaskAsync(final Callable<List<Pet>> callable, final GetPetListTask.GetPetListTaskCallback GetPetListTaskCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<Pet> result;
                try {
                    result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            GetPetListTaskCallback.onPetListLoadingComplete(result);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    GetPetListTaskCallback.onPetListLoadingFail(e);
                }
            }
        });
    }

    void executeGetAllPetListAsync(final Callable<List<Pet>> callable, final GetAllPetListTask.GetAllPetListCallback GetPetListTaskCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<Pet> result;
                try {
                    result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            GetPetListTaskCallback.onAllPetListGetComplete(result);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    GetPetListTaskCallback.onAllPetListGetFail(e);
                }
            }
        });
    }


}
