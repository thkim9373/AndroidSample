package com.hoony.androidsample.excel.file_explorer;

import android.app.Application;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class FileExplorerViewModel extends AndroidViewModel {

    public FileExplorerViewModel(@NonNull Application application) {
        super(application);

        filePathLiveData = new MutableLiveData<>();
        filePathLiveData.setValue(Environment.getRootDirectory().toString());
    }

    private MutableLiveData<String> filePathLiveData;

    public MutableLiveData<String> getFilePathLiveData() {
        return filePathLiveData;
    }

    public void setFilePath(String filePath) {
        this.filePathLiveData.setValue(filePath);
    }
}
