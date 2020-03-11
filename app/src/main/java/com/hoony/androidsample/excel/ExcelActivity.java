package com.hoony.androidsample.excel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityExcelBinding;
import com.hoony.androidsample.excel.file_explorer.FileExplorerActivity;
import com.hoony.androidsample.excel.pojo.User;
import com.hoony.androidsample.util.ToastPrinter;

public class ExcelActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityExcelBinding binding;
    private ExcelViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ExcelActivity.this, R.layout.activity_excel);
        viewModel = new ViewModelProvider(ExcelActivity.this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ExcelViewModel.class);

        setView();
        setListener();
        setObserve();
    }

    private void setView() {
        binding.rvUser.setLayoutManager(new LinearLayoutManager(ExcelActivity.this));
    }

    private void setListener() {
        binding.btAdd.setOnClickListener(ExcelActivity.this);
        binding.btCheckOut.setOnClickListener(ExcelActivity.this);
    }

    private void setObserve() {
        viewModel.getUserListLiveData().observe(ExcelActivity.this, userList -> binding.rvUser.setAdapter(new ExcelAdapter(userList)));
        viewModel.getUserLiveData().observe(ExcelActivity.this, user -> {
            binding.etName.setText(user.getName());

            binding.etName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    viewModel.setName(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_add) {
            ExcelAdapter excelAdapter = (ExcelAdapter) binding.rvUser.getAdapter();
            if (excelAdapter != null) {
                User user = viewModel.getUser();
                excelAdapter.addUser(user);
            }
        } else if (id == R.id.bt_check_out) {
            Intent intent = new Intent(ExcelActivity.this, FileExplorerActivity.class);
            startActivity(intent);
        }
    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                new AlertDialog.Builder(ExcelActivity.this)
                        .setMessage(getString(R.string.permission_dialog_msg))
                        .setPositiveButton(getString(R.string.permission_setting), (dialog, which) -> {
                            Intent intent = new Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", getPackageName(), null));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        })
                        .setNegativeButton(getString(R.string.permission_close), (dialog, which) -> ToastPrinter.showToast(ExcelActivity.this, "권한이 거부되었습니다."))
                        .show();
                return;
            }
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.rvUser.setAdapter(null);
    }
}
