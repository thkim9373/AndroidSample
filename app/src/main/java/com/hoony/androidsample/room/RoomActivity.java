package com.hoony.androidsample.room;

import android.os.Bundle;
import android.text.TextUtils;
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
        viewModel.getSelectedPetList().observe(RoomActivity.this, new Observer<List<Pet>>() {
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

        setupView();
        loadUserData();
        loadAllPetData();
        setListener();
    }

    private void setupView() {
        binding.svUser.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
        binding.svUser.addItemDecoration(new DividerItemDecoration(RoomActivity.this, LinearLayoutManager.VERTICAL));

        binding.svPet.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
        binding.svPet.addItemDecoration(new DividerItemDecoration(RoomActivity.this, LinearLayoutManager.VERTICAL));
    }

    private void loadUserData() {
        if (binding.svUser.getAdapter() == null) viewModel.loadAllUserList();
    }

    private void loadAllPetData() {
        viewModel.loadAllPetList();
    }

    private void setListener() {
        binding.btUserAction.setOnClickListener(RoomActivity.this);
        binding.btPetAction.setOnClickListener(RoomActivity.this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            //  User item.
            case R.id.cl_container:
                int position = (int) v.getTag();
                viewModel.changeSelectedUser(position);
                break;
            case R.id.bt_user_action:
                if (isUserActionAdd()) {
                    String userName = getUserName();
                    if (!TextUtils.equals(userName, "")) {
                        viewModel.insertUser(userName);
                    } else {
                        ToastViewer.showToast(RoomActivity.this, "Please enter a user name.");
                    }
                } else {
                    viewModel.deleteUser();
                }
                break;
            case R.id.bt_pet_action:
                if (isPetActionAdd()) {
                    String petName = getPetName();
                    if (!TextUtils.equals(petName, "")) {
                        viewModel.insertPet(petName);
                    } else {
                        ToastViewer.showToast(RoomActivity.this, "Please enter a pet name.");
                    }
                } else {
                    String petName = getPetName();
                    if (!TextUtils.equals(petName, "")) {
                        viewModel.deletePet(petName);
                    } else {
                        ToastViewer.showToast(RoomActivity.this, "Please enter a pet name.");
                    }
                }
                break;
        }
    }

    private boolean isUserActionAdd() {
        return binding.rgUser.getCheckedRadioButtonId() == R.id.rb_add;
    }

    private String getUserName() {
        CharSequence userName = binding.etUser.getText();
        if (userName != null && !TextUtils.equals(userName, "")) {
            binding.etUser.setText("");
            return String.valueOf(userName);
        } else {
            return null;
        }
    }

    private boolean isPetActionAdd() {
        return binding.rgPet.getCheckedRadioButtonId() == R.id.rb_pet_add;
    }

    private String getPetName() {
        CharSequence petName = binding.etPetName.getText();
        if (petName != null && !TextUtils.equals(petName, "")) {
            binding.etPetName.setText("");
            return String.valueOf(petName);
        } else {
            return null;
        }
    }

    @Override
    public boolean onLongClick(View view) {
//        int index = (int) view.getTag();

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.svUser.setAdapter(null);
        binding.svPet.setAdapter(null);
    }
}
