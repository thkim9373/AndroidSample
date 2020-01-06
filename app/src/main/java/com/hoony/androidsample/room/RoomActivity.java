package com.hoony.androidsample.room;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityRoomBinding;
import com.hoony.androidsample.db.user.User;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private ActivityRoomBinding binding;
    private RoomViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(RoomActivity.this, R.layout.activity_room);

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RoomViewModel.class);

        viewModel.getUserList().observe(RoomActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                binding.svPet.setAdapter(new UserAdapter(users));
            }
        });

        setView();
        loadData();
    }

    private void setView() {
        binding.svUser.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
        binding.svUser.addItemDecoration(new DividerItemDecoration(RoomActivity.this, LinearLayoutManager.VERTICAL));

        binding.svPet.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
        binding.svPet.addItemDecoration(new DividerItemDecoration(RoomActivity.this, LinearLayoutManager.VERTICAL));
    }

    private void loadData() {
        if (binding.svUser.getAdapter() == null) viewModel.loadUserList();
    }
}
