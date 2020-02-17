package com.hoony.androidsample.picture_loader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, CAMERA);
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
            if (data == null) return;
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            Glide.with(PictureLoaderActivity.this)
                    .load(bitmap)
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

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
