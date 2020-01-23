package com.hoony.androidsample.media_data_list;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityAlbumDataListBinding;

import java.util.ArrayList;
import java.util.List;

public class MediaDataListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private final int LOADER_ID = "MUSIC_DATA_LOADER".hashCode();

    private ActivityAlbumDataListBinding binding;

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
        CursorLoader loader = (CursorLoader) LoaderManager.getInstance(MediaDataListActivity.this).initLoader(LOADER_ID, null, MediaDataListActivity.this);
        loader.loadInBackground();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(
                MediaDataListActivity.this,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
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
        if (cursor == null) return;

        @SuppressWarnings("deprecation")
        int idIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
        int albumIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);
        int artistIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID);
        int displayNameIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
        int titleIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
        int albumArtistIndex = cursor.getColumnIndexOrThrow("album_artist");
        List<MediaData> mediaDataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            mediaDataList.add(new MediaData(
                    cursor.getString(idIndex),
                    cursor.getString(albumIdIndex),
                    cursor.getString(artistIdIndex),
                    cursor.getString(displayNameIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(albumArtistIndex)
            ));
        }

        binding = DataBindingUtil.setContentView(MediaDataListActivity.this, R.layout.activity_album_data_list);

        binding.svAlbumData.setLayoutManager(new LinearLayoutManager(MediaDataListActivity.this));
        binding.svAlbumData.setAdapter(new MediaDataListAdapter(mediaDataList));
        binding.svAlbumData.addItemDecoration(new DividerItemDecoration(MediaDataListActivity.this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MediaDataListActivity.this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.svAlbumData.setAdapter(null);
    }
}
