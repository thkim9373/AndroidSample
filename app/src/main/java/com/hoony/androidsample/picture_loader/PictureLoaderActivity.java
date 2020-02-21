package com.hoony.androidsample.picture_loader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityPictureLoaderBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PictureLoaderActivity extends AppCompatActivity implements View.OnClickListener {

    private final int CAMERA = 1;
    private final int GALLERY = 2;

    ActivityPictureLoaderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(PictureLoaderActivity.this, R.layout.activity_picture_loader);

        setListener();
    }

    private void setListener() {
        binding.btCamera.setOnClickListener(PictureLoaderActivity.this);
        binding.btGallery.setOnClickListener(PictureLoaderActivity.this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_camera) {
//            Intent intent = new Intent();
//            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(intent, CAMERA);
//            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {
                sendTakePhotoIntent();
            }
        } else if (v.getId() == R.id.bt_gallery) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
//            if (data == null) return;
//            Bundle bundle = data.getExtras();
//            Bitmap bitmap = (Bitmap) bundle.get("data");

            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            ExifInterface exif = null;

            try {
                exif = new ExifInterface(currentPhotoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegrees(exifOrientation);
            } else {
                exifDegree = 0;
            }

            Glide.with(PictureLoaderActivity.this)
                    .load(rotate(bitmap, exifDegree))
                    .into(binding.ivPicture);

//            Uri uri = data.getData();
        } else if (requestCode == GALLERY) {
            if (data == null) return;
            Uri uri = data.getData();
            Glide.with(PictureLoaderActivity.this)
                    .load(uri)
                    .into(binding.ivPicture);
        }
    }

    private Uri photoUri;

    private void sendTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, getPackageName(), photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, CAMERA);
            }
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

//    private void sendTakePhotoIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        try {
//            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                photoUri = createImageUri();
//
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                startActivityForResult(takePictureIntent, CAMERA);
//            }
//        } catch (IOException e) {
//
//        }
//    }
//
//    private Uri createImageUri() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//
//        ContentResolver resolver = getContentResolver();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, imageFileName);
//        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
//        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
//
//        return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//    }
}
