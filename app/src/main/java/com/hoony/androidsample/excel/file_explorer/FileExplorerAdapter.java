package com.hoony.androidsample.excel.file_explorer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemSingleTextViewBinding;

import java.util.ArrayList;
import java.util.List;

public class FileExplorerAdapter extends RecyclerView.Adapter {

    private List<String> filePathList = new ArrayList<>();

    FileExplorerAdapter(List<String> filePathList) {
        this.filePathList = filePathList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemSingleTextViewBinding binding = ((ItemHolder) holder).getBinding();
        String filePath = filePathList.get(position);
    }

    @Override
    public int getItemCount() {
        return filePathList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private ItemSingleTextViewBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        ItemSingleTextViewBinding getBinding() {
            return binding;
        }
    }
}
