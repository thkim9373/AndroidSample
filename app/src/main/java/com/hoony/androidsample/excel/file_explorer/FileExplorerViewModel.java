package com.hoony.androidsample.excel.file_explorer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.Objects;

public class FileExplorerViewModel extends AndroidViewModel {

    public FileExplorerViewModel(@NonNull Application application) {
        super(application);

        fileMutableLiveData = new MutableLiveData<>();
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        fileMutableLiveData.setValue(application.getExternalFilesDir(null));
    }

    private MutableLiveData<File> fileMutableLiveData;

    void setFileMutableLiveData(File file) {
        this.fileMutableLiveData.setValue(file);
    }

    MutableLiveData<File> getFileMutableLiveData() {
        return fileMutableLiveData;
    }

    void setPrevDirectory() {
        File currentDirectory = Objects.requireNonNull(this.fileMutableLiveData.getValue());
        String filePath = currentDirectory.getAbsolutePath();

        String prevFilePath = filePath.substring(0, filePath.lastIndexOf("/"));
        File prevFile = new File(prevFilePath);
        this.fileMutableLiveData.setValue(prevFile);
    }
}
