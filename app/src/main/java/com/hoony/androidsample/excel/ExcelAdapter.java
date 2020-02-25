package com.hoony.androidsample.excel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemExcelBinding;
import com.hoony.androidsample.excel.pojo.User;

import java.util.List;

public class ExcelAdapter extends RecyclerView.Adapter {

    private List<User> userList;

    public ExcelAdapter(List<User> userList) {
        this.userList = userList;
    }

    void addUser(User user) {
        userList.add(user);
        notifyItemInserted(userList.size() - 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_excel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemExcelBinding binding = ((ItemHolder) holder).getBinding();
        User user = userList.get(position);

        binding.tvName.setText(user.getName());
        binding.tvTime.setText(user.getInputTime());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private ItemExcelBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        ItemExcelBinding getBinding() {
            return binding;
        }
    }
}
