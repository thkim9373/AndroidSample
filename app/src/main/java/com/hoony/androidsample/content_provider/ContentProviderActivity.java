package com.hoony.androidsample.content_provider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityContentProviderBinding;

public class ContentProviderActivity extends AppCompatActivity {

    private final String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(PERMISSIONS[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(PERMISSIONS, 1);
                return;
            }
        }

        showContents();
    }

    private void showContents() {
        ActivityContentProviderBinding binding = DataBindingUtil.setContentView(ContentProviderActivity.this, R.layout.activity_content_provider);

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        if (cursor == null) return;

        StringBuilder builder = new StringBuilder();
        builder.append("================================\n\n");
        while (cursor.moveToNext()) {
            builder.append("Display name: ").append(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))).append("\n")
                    .append("Phone number: ").append(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))).append("\n\n")
                    .append("================================\n\n");
        }

        binding.tvAddressBookContents.setText(builder.toString());

        cursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ContentProviderActivity.this);
                builder.setTitle("Permission denied.");
                builder.setCancelable(false);
                builder.setMessage("Please allow permission");
                builder.setNegativeButton("닫기", null);
                builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                    }
                });
                builder.show();
                return;
            }
        }

        showContents();
    }
}
