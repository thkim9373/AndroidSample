package com.hoony.androidsample.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.db.pet.PetDao;
import com.hoony.androidsample.db.user.User;
import com.hoony.androidsample.db.user.UserDao;

import java.util.concurrent.Executors;

@Database(entities = {User.class, Pet.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public abstract PetDao petDao();

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildAppDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase buildAppDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app_db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        getInstance(context).userDao().insertAll(User.userData());
                                        getInstance(context).petDao().insertAll(Pet.petData());
                                    }
                                }
                        );
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                    }
                })
                .addMigrations(new Migration(1, 1) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        getInstance(context).userDao().insertAll(User.userData());
                        getInstance(context).petDao().insertAll(Pet.petData());
                    }
                })
                .build();
    }
}
