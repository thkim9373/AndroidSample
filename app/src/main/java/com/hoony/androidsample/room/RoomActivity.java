package com.hoony.androidsample.room;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityRoomBinding;
import com.hoony.androidsample.db.pet.Pet;
import com.hoony.androidsample.room.POJO.CheckableUser;
import com.hoony.androidsample.util.ToastViewer;

import java.util.List;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private ActivityRoomBinding binding;
    private RoomViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(RoomActivity.this, R.layout.activity_room);

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RoomViewModel.class);

        viewModel.getUserList().observe(RoomActivity.this, new Observer<List<CheckableUser>>() {
            @Override
            public void onChanged(List<CheckableUser> users) {
                if (binding.svUser.getAdapter() == null) {
                    binding.svUser.setAdapter(new UserAdapter(users, RoomActivity.this, RoomActivity.this));
                } else {
                    ((UserAdapter) binding.svUser.getAdapter()).setUserList(users);
                }

                binding.tvUserContent.setText(users.toString());
            }
        });
        viewModel.getPetList().observe(RoomActivity.this, new Observer<List<Pet>>() {
            @Override
            public void onChanged(List<Pet> pets) {
                binding.svPet.setAdapter(new PetAdapter(pets));
            }
        });
        viewModel.getAllPetList().observe(RoomActivity.this, new Observer<List<Pet>>() {
            @Override
            public void onChanged(List<Pet> pets) {
                binding.tvPetContent.setText(pets.toString());
            }
        });

        setView();
        loadUSerData();
        loadAllPetData();
    }

    private void setView() {
        binding.svUser.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
        binding.svUser.addItemDecoration(new DividerItemDecoration(RoomActivity.this, LinearLayoutManager.VERTICAL));

        binding.svPet.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
        binding.svPet.addItemDecoration(new DividerItemDecoration(RoomActivity.this, LinearLayoutManager.VERTICAL));
    }

    private void loadUSerData() {
        if (binding.svUser.getAdapter() == null) viewModel.loadUserList();
    }

    private void loadAllPetData() {
        viewModel.loadAllPetList();
    }

    private void loadPetData(int index) {
        viewModel.loadPetList(index);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.bt_user_action) {
            if (binding.etUser.getText() != null) {
                if (binding.rgUser.getCheckedRadioButtonId() == R.id.rb_add) {

                } else {

                }
            } else {

            }
        } else if (id == R.id.bt_pet_action) {
            if (binding.etPetIndex.getText() != null &&
                    binding.etPetName.getText() != null &&
                    binding.etPetKind.getText() != null) {
                int petIndex = Integer.parseInt(binding.etPetIndex.getText().toString());
                String petName = binding.etPetName.getText().toString();
                String petKind = binding.etPetKind.getText().toString();
                Pet pet = new Pet(petIndex, petName, petKind);
                if (binding.rgPet.getCheckedRadioButtonId() == R.id.rb_pet_add) {
                    viewModel.insertPet(pet);
                } else {
                    viewModel.deletePet(pet);
                }
            } else {
                ToastViewer.showToast(RoomActivity.this, "Pet attributes is null!!!");
            }
        } else {
            int index = (int) v.getTag();

            loadPetData(index);

            viewModel.userCheck(index);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int index = (int) view.getTag();

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.svUser.setAdapter(null);
        binding.svPet.setAdapter(null);
    }
}
