package com.hoony.androidsample.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemMainListBinding;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter {

    private final List<Item> itemList;
    private final View.OnClickListener onClickListener;

    MainRecyclerViewAdapter(List<Item> itemList, View.OnClickListener onClickListener) {
        this.itemList = itemList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        ItemMainListBinding binding = itemHolder.getBinding();
        Item item = itemList.get(position);
        binding.tvTitle.setText(item.getTitle());
        binding.clContainer.setTag(item.getTarget());
        binding.clContainer.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private ItemMainListBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        ItemMainListBinding getBinding() {
            return binding;
        }
    }
}
