package com.hoony.androidsample.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemPhotoGridBinding;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter {

    private final List<ImageData> mList;
    private Context mContext;

    GalleryAdapter(List<ImageData> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemPhotoGridBinding binding = ((ItemHolder) holder).getBinding();

        ImageData imageData = mList.get(position);

//        binding.ivThumbnail.setImageURI(imageData.getUri());
        Glide.with(mContext)
                .load(imageData.getUri())
                .thumbnail(0.3f)
                .centerCrop()
                .into(binding.ivThumbnail);
        binding.tvMediaName.setText(imageData.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private ItemPhotoGridBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemPhotoGridBinding getBinding() {
            return binding;
        }
    }
}
