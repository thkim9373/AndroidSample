package com.hoony.androidsample.album_data_list;

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

public class AlbumDataListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

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
        CursorLoader loader = (CursorLoader) LoaderManager.getInstance(AlbumDataListActivity.this).initLoader(LOADER_ID, null, AlbumDataListActivity.this);
        loader.loadInBackground();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(
                AlbumDataListActivity.this,
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
        if (cursor == null) return;

        @SuppressWarnings("deprecation")
        int albumArtIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART);
        int idIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID);
        int albumIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM);
        int albumKeyIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_KEY);
        int artistIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST);
        int artistIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST_ID);
        int artistKeyIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST_KEY);
        int firstYearIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.FIRST_YEAR);
        int lastYearIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.LAST_YEAR);
        int numOfSongsIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.NUMBER_OF_SONGS);

        List<AlbumData> albumDataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            albumDataList.add(new AlbumData(
                    cursor.getString(albumArtIndex),
                    cursor.getString(idIndex),
                    cursor.getString(albumIndex),
                    cursor.getString(albumKeyIndex),
                    cursor.getString(artistIndex),
                    cursor.getString(artistIdIndex),
                    cursor.getString(artistKeyIndex),
                    cursor.getString(firstYearIndex),
                    cursor.getString(lastYearIndex),
                    cursor.getString(numOfSongsIndex)
            ));
        }

        binding = DataBindingUtil.setContentView(AlbumDataListActivity.this, R.layout.activity_album_data_list);

        binding.svAlbumData.setLayoutManager(new LinearLayoutManager(AlbumDataListActivity.this));
        binding.svAlbumData.setAdapter(new AlbumDataListAdapter(albumDataList));
        binding.svAlbumData.addItemDecoration(new DividerItemDecoration(AlbumDataListActivity.this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlbumDataListActivity.this);
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
