package com.hoony.androidsample.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemSingleTextViewBinding;
import com.hoony.androidsample.db.user.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {

    private final List<User> userList;

    UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemSingleTextViewBinding binding = ((ItemHolder) holder).getBinding();
        User user = userList.get(position);

        binding.tvContent.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private ItemSingleTextViewBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemSingleTextViewBinding getBinding() {
            return binding;
        }
    }
}
