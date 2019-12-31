package com.hoony.androidsample.music_player;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityMusicPlayerBinding;

import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private final int LOADER_ID = "MUSIC_DATA_LOADER".hashCode();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkPermissions()) return;

        loadData();
    }

    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : PERMISSIONS) {
                if (checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(PERMISSIONS, 0);
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private void loadData() {
        CursorLoader loader = (CursorLoader) LoaderManager.getInstance(MusicPlayerActivity.this).initLoader(LOADER_ID, null, MusicPlayerActivity.this);
        loader.loadInBackground();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(
                MusicPlayerActivity.this,
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        showContent(cursor);
    }

    private void showContent(Cursor cursor) {

        int albumIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID);
        int albumIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM);
        int albumKeyIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_KEY);

        while (cursor.moveToNext()) {
            String album = cursor.getString(albumIndex);
            String albumId = cursor.getString(albumIdIndex);
            String albumKey = cursor.getString(albumKeyIndex);
        }


        ActivityMusicPlayerBinding binding = DataBindingUtil.setContentView(MusicPlayerActivity.this, R.layout.activity_music_player);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int result : grantResults) {
            if(result == PackageManager.PERMISSION_DENIED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MusicPlayerActivity.this);
                dialog.setTitle(R.string.permission_dialog_title);
                dialog.setMessage(R.string.permission_dialog_msg);
                dialog.setPositiveButton(R.string.permission_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton(R.string.permission_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                return;
            }
        }

        loadData();
    }
}
