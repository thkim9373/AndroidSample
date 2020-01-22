package com.hoony.androidsample.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemSingleTextViewBinding;
import com.hoony.androidsample.db.pet.Pet;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter {

    private final List<Pet> mList;

    PetAdapter(List<Pet> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemSingleTextViewBinding binding = ((ItemHolder) holder).getBinding();
        Pet pet = mList.get(position);

        String contents =
                "Name : " + pet.getName();

        binding.tvContent.setText(contents);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        ItemSingleTextViewBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemSingleTextViewBinding getBinding() {
            return binding;
        }
    }
}
