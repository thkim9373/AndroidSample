package com.hoony.androidsample.excel.file_explorer;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityFileExplorerBinding;

public class FileExplorerActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityFileExplorerBinding binding;
    private FileExplorerViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(FileExplorerActivity.this, R.layout.activity_file_explorer);
        viewModel = new ViewModelProvider(FileExplorerActivity.this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(FileExplorerViewModel.class);


    }

    @Override
    public void onClick(View view) {

    }
}
