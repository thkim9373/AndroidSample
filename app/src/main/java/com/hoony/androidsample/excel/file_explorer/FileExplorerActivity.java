package com.hoony.androidsample.excel.file_explorer;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityFileExplorerBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileExplorerActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFileExplorerBinding binding;
    private FileExplorerViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(FileExplorerActivity.this, R.layout.activity_file_explorer);
        viewModel = new ViewModelProvider(FileExplorerActivity.this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(FileExplorerViewModel.class);

        setObserve();
    }

    private void setObserve() {
        viewModel.getFileMutableLiveData().observe(FileExplorerActivity.this, file -> {
            binding.tvFilePath.setText(file.getAbsolutePath());

            List<File> fileList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(file.listFiles())));
            FileExplorerAdapter adapter;
            if (binding.rvFile.getAdapter() != null) {
                adapter = (FileExplorerAdapter) binding.rvFile.getAdapter();
                adapter.setFilePathList(fileList);
            } else {
                adapter = new FileExplorerAdapter(fileList);
                binding.rvFile.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
