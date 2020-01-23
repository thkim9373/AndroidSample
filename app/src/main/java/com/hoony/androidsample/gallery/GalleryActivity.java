package com.hoony.androidsample.gallery;

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
import com.hoony.androidsample.databinding.ActivityPhotoGridViewBinding;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int IMAGE_URI_LOADER = "image_uri_loader".hashCode();

    private final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};

    private ActivityPhotoGridViewBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isPermissionsDenied()) return;

        loadData();
    }

    private void loadData() {
        CursorLoader cursorLoader = (CursorLoader) LoaderManager.getInstance(GalleryActivity.this).initLoader(IMAGE_URI_LOADER, null, GalleryActivity.this);
        cursorLoader.loadInBackground();
    }

    private boolean isPermissionsDenied() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(PERMISSIONS[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(PERMISSIONS, 0);
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(
                GalleryActivity.this,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.DISPLAY_NAME
                },
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
        binding = DataBindingUtil.setContentView(GalleryActivity.this, R.layout.activity_photo_grid_view);

        List<ImageData> imageDataList = new ArrayList<>();
        int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
        while (cursor.moveToNext()) {
            imageDataList.add(
                    new ImageData(
                            Uri.withAppendedPath(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    String.valueOf(cursor.getLong(idColumn))
                            ),
                            cursor.getString(nameColumn)
                    )
            );
        }

        binding.rvGrid.setLayoutManager(new GridLayoutManager(GalleryActivity.this, 3));
        binding.rvGrid.setAdapter(new GalleryAdapter(imageDataList));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.rvGrid.setAdapter(null);
        binding = null;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(GalleryActivity.this);
                dialog.setTitle("Need permissions");
                dialog.setMessage("Please allow the permissions.");
                dialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.show();
                return;
            }
        }

        loadData();
    }
}
