package com.hoony.androidsample.music_player;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import androidx.recyclerview.widget.GridLayoutManager;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityMusicPlayerBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int ALBUM_DATA_LOADER = 559;

    private ActivityMusicPlayerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isDeniedPermissions()) return;

        loadData();
    }

    private boolean isDeniedPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : PERMISSIONS) {
                if (checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(PERMISSIONS, 1);
                    return true;
                }
            }
        }
        return false;
    }

    private void loadData() {
        CursorLoader albumDataLoader = (CursorLoader) LoaderManager.getInstance(MusicPlayerActivity.this).initLoader(ALBUM_DATA_LOADER, null, MusicPlayerActivity.this);
        albumDataLoader.loadInBackground();
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
        showContents(cursor);
    }

    private void showContents(Cursor cursor) {
        if (cursor == null) return;

        int idIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART);
        int artistIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST);
        int numOfSongIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.NUMBER_OF_SONGS);

        List<AlbumData> albumDataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            albumDataList.add(
                    new AlbumData(
                            cursor.getString(idIndex) !=  null ?
                            Uri.fromFile(new File(cursor.getString(idIndex))) : null,
                            cursor.getString(artistIndex),
                            cursor.getInt(numOfSongIndex)
                    )
            );
        }

        cursor.close();

        binding = DataBindingUtil.setContentView(MusicPlayerActivity.this, R.layout.activity_music_player);

        binding.svAlbum.setLayoutManager(new GridLayoutManager(MusicPlayerActivity.this, 2));
        binding.svAlbum.setAdapter(new AlbumListAdapter(albumDataList));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MusicPlayerActivity.this);
                builder.setTitle(getString(R.string.permission_dialog_title))
                        .setMessage(getString(R.string.permission_dialog_msg))
                        .setNegativeButton(getString(R.string.permission_close), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .setPositiveButton(getString(R.string.permission_setting), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .show();
            }
        }

        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.svAlbum.setAdapter(null);
    }
}
