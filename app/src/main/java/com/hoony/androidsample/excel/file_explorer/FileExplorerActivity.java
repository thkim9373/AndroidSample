package com.hoony.androidsample.excel.file_explorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityFileExplorerBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileExplorerActivity extends AppCompatActivity
        implements View.OnClickListener
        , FileExplorerAdapter.FileExplorerAdapterListener {

    private ActivityFileExplorerBinding binding;
    private FileExplorerViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(FileExplorerActivity.this, R.layout.activity_file_explorer);
        viewModel = new ViewModelProvider(FileExplorerActivity.this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(FileExplorerViewModel.class);

        setView();
        setObserve();
    }

    private void setView() {
        binding.rvFile.addItemDecoration(new DividerItemDecoration(FileExplorerActivity.this, RecyclerView.VERTICAL));
        binding.rvFile.setLayoutManager(new LinearLayoutManager(FileExplorerActivity.this));
    }

    private void setObserve() {
        viewModel.getFileMutableLiveData().observe(FileExplorerActivity.this, file -> {
            binding.tvFilePath.setText(file.getAbsolutePath());

            List<File> directoryList = getDirectoryList(file);
            FileExplorerAdapter adapter;
            if (binding.rvFile.getAdapter() != null) {
                adapter = (FileExplorerAdapter) binding.rvFile.getAdapter();
                adapter.setFileList(directoryList);
            } else {
                adapter = new FileExplorerAdapter(directoryList);
                binding.rvFile.setAdapter(adapter);
            }
        });
    }

    private List<File> getDirectoryList(File file) {
        List<File> directoryList = new ArrayList<>();

        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory())
                directoryList.add(f);
        }

        return directoryList;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.btConfirm.getId()) {
            File file = viewModel.getFileMutableLiveData().getValue();
            if (file != null) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("file_path", viewModel.getFileMutableLiveData().getValue().getAbsolutePath());
                setResult(0, resultIntent);
            }
            finish();
        }
    }

    @Override
    public void onItemClick(File file) {
        viewModel.setFileMutableLiveData(file);
    }

    @Override
    public void onBackPressed() {
        viewModel.setPrevDirectory();
    }
}
