package com.hoony.androidsample.address_book;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.hoony.androidsample.databinding.ActivityAddressBookListBinding;

import java.util.ArrayList;
import java.util.List;

public class AddressBookListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ADDRESS_BOOK_LOADER = "address_book_loader".hashCode();

    private final String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS};

    private ActivityAddressBookListBinding binding;
    private CursorLoader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isDeniedPermissions()) return;

        loadData();
    }

    private boolean isDeniedPermissions() {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
            if (checkSelfPermission(PERMISSIONS[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(PERMISSIONS, 1);
                return true;
            }
        }
        return false;
    }

    private void loadData() {
        loader = (CursorLoader) LoaderManager.getInstance(AddressBookListActivity.this).initLoader(ADDRESS_BOOK_LOADER, null, AddressBookListActivity.this);
        loader.loadInBackground();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.svAddressBook.setAdapter(null);
        if (loader != null) loader.abandon();
    }

    @NonNull
    @Override
    public CursorLoader onCreateLoader(int id, @Nullable Bundle args) {
        //  Available columns: [creation_time, phonetic_name, status_res_package, custom_ringtone,
        //  contact_status_ts, account_type, data_version, photo_file_id, contact_status_res_package,
        //  group_sourceid, display_name_alt, sort_key_alt, mode, last_time_used, starred,
        //  contact_status_label, has_phone_number, chat_capability, raw_contact_id, carrier_presence,
        //  contact_last_updated_timestamp, res_package, sec_custom_vibration, photo_uri, data_sync4,
        //  phonebook_bucket, times_used, display_name, sort_key, data_sync1, version, data_sync2,
        //  data_sync3, photo_thumb_uri, status_label, contact_presence, sec_custom_alert,
        //  in_default_directory, times_contacted, _id, account_type_and_data_set,
        //  name_raw_contact_id, status, phonebook_bucket_alt, is_private, last_time_contacted,
        //  pinned, is_primary, photo_id, contact_id, contact_chat_capability, contact_status_icon,
        //  in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty,
        //  sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6,
        //  phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set,
        //  contact_status, is_sim, backup_id, preferred_phone_account_component_name,
        //  raw_contact_is_user_profile, status_ts, display_name_reverse, data10,
        //  preferred_phone_account_id, sec_led, data12, mimetype, status_icon, data11,
        //  data14, data13, hash_id, data15]
        return new CursorLoader(AddressBookListActivity.this,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
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

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        showContents(null);
    }

    private void showContents(Cursor cursor) {

        if (cursor == null) return;

        int contactLastUpdatedTimestampIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP);
        int displayNameIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int numberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int hasPhoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER);
        int inDefaultDirectoryIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.IN_DEFAULT_DIRECTORY);
        int inVisibleGroupIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.IN_VISIBLE_GROUP);
        int lookupKeyIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY);
        int nameRawContactIdIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NAME_RAW_CONTACT_ID);
        int photoFileIdIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.PHOTO_FILE_ID);
        int photoIdIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.PHOTO_ID);
        int photoThumbnailUriIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI);
        int photoUriIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.PHOTO_URI);

        List<AddressBookData> addressBookDataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            addressBookDataList.add(
                    new AddressBookData(
                            cursor.getString(contactLastUpdatedTimestampIndex),
                            cursor.getString(displayNameIndex),
                            cursor.getString(numberIndex),
                            cursor.getString(hasPhoneNumberIndex),
                            cursor.getString(inDefaultDirectoryIndex),
                            cursor.getString(inVisibleGroupIndex),
                            cursor.getString(lookupKeyIndex),
                            cursor.getString(nameRawContactIdIndex),
                            cursor.getString(photoFileIdIndex),
                            cursor.getString(photoIdIndex),
                            cursor.getString(photoThumbnailUriIndex),
                            cursor.getString(photoUriIndex)
                    )
            );
        }

        binding = DataBindingUtil.setContentView(AddressBookListActivity.this, R.layout.activity_address_book_list);

        binding.svAddressBook.setLayoutManager(new LinearLayoutManager(AddressBookListActivity.this));
        binding.svAddressBook.setAdapter(new AddressListAdapter(addressBookDataList));
        binding.svAddressBook.addItemDecoration(new DividerItemDecoration(AddressBookListActivity.this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddressBookListActivity.this);
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

        loadData();
    }
}
