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

//    interface Callback<R> {
//        void onComplete(R result);
//
//        void onFail(Exception e);
//    }
//
//    <R> void executeAsync(final Callable<R> callable, final Callback<R> callback) {
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                final R result;
//                try {
//                    result = callable.call();
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            callback.onComplete(result);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    callback.onFail(e);
//                }
//            }
//        });
//    }

    void executeUserTaskAsync(final Callable<List<User>> callable, final UserLoadingTask.Callback<List<User>> callback) {
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

    void executePetTaskAsync(final Callable<List<Pet>> callable, final PetLoadingTask.callback callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<Pet> result;
                try {
                    result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onPetListLoadingComplete(result);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onPetListLoadingFail(e);
                }
            }
        });
    }
}
