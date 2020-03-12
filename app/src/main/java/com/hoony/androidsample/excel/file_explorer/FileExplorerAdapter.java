package com.hoony.androidsample.excel.file_explorer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemDirectoryBinding;

import java.io.File;
import java.util.List;

public class FileExplorerAdapter extends RecyclerView.Adapter {

    private List<File> fileList;
    private FileExplorerAdapterListener listener;

    FileExplorerAdapter(List<File> fileList) {
        this.fileList = fileList;
    }

    void setFileList(List<File> fileList) {
        this.fileList = fileList;
        notifyDataSetChanged();
    }

    interface FileExplorerAdapterListener {
        void onItemClick(File file);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (listener == null) listener = (FileExplorerAdapterListener) parent.getContext();
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_directory, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemDirectoryBinding binding = ((ItemHolder) holder).getBinding();
        File file = fileList.get(position);

        String name = file.getName();
        binding.tvContent.setText(name);
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private ItemDirectoryBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.clContainer.setOnClickListener(view -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        File file = fileList.get(position);
                        listener.onItemClick(file);
                    }
                });
            }
        }

        ItemDirectoryBinding getBinding() {
            return binding;
        }
    }
}
