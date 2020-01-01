package com.hoony.androidsample.album_data_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemSingleTextViewBinding;

import java.util.List;

public class AlbumDataListAdapter extends RecyclerView.Adapter {

    private final List<AlbumData> mList;

    AlbumDataListAdapter(List<AlbumData> albumDataList) {
        this.mList = albumDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemSingleTextViewBinding binding = ((ItemHolder) holder).getBinding();
        AlbumData data = mList.get(position);

        String builder = "albumArt : " + data.getAlbumArt() + "\n" +
                "id : " + data.getId() + "\n" +
                "album : " + data.getAlbum() + "\n" +
                "albumKey : " + data.getAlbumKey() + "\n" +
                "artist : " + data.getArtist() + "\n" +
                "artistId : " + data.getArtistId() + "\n" +
                "artistKey : " + data.getArtistKey() + "\n" +
                "firstYear : " + data.getFirstYear() + "\n" +
                "lastYear : " + data.getLastYear() + "\n" +
                "numberOfSongs : " + data.getNumberOfSongs();

        binding.tvContent.setText(builder);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private ItemSingleTextViewBinding binding;

        ItemHolder(@NonNull View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }

        ItemSingleTextViewBinding getBinding() {
            return binding;
        }
    }
}
