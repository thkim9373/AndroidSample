package com.hoony.androidsample.room;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemSingleTextViewBinding;
import com.hoony.androidsample.room.POJO.CheckableUser;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {

    private List<CheckableUser> userList;
    private final View.OnClickListener onClickListener;
    private final View.OnLongClickListener onLongClickListener;

    UserAdapter(List<CheckableUser> userList,
                View.OnClickListener onClickListener,
                View.OnLongClickListener onLongClickListener) {
        this.userList = userList;
        this.onClickListener = onClickListener;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemSingleTextViewBinding binding = ((ItemHolder) holder).getBinding();
        CheckableUser user = userList.get(position);

        String content = "Name : " + user.getName();

        binding.tvContent.setText(content);
        binding.clContainer.setTag(position);
        binding.clContainer.setOnClickListener(onClickListener);
        binding.clContainer.setOnLongClickListener(onLongClickListener);

        if (user.isChecked()) {
            binding.clContainer.setBackgroundColor(Color.argb(255, 255, 255, 204));
        } else {
            binding.clContainer.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    void setUserList(List<CheckableUser> userList) {
        this.userList = userList;
        this.notifyDataSetChanged();
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
