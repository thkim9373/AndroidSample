package com.hoony.androidsample.music_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemAlbumBinding;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter {

    private final List<AlbumData> mList;
    private Context mContext;

    AlbumListAdapter(List<AlbumData> albumDataList) {
        this.mList = albumDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemAlbumBinding binding = ((ItemViewHolder) holder).getBinding();
        AlbumData data = mList.get(position);

        if (data.getAlbumArtUri() != null) {
            binding.ivAlbumArt.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(data.getAlbumArtUri())
//                    .loadFromMediaStore(data.getAlbumArtUri())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .into(binding.ivAlbumArt);
        } else {
            binding.ivAlbumArt.setVisibility(View.INVISIBLE);
        }

        binding.tvArtist.setText(data.getArtist());
        binding.tvNumOfSong.setText(String.valueOf(data.getNumOfSong()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemAlbumBinding binding;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemAlbumBinding getBinding() {
            return binding;
        }
    }
}
