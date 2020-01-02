package com.hoony.androidsample.media_data_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemSingleTextViewBinding;

import java.util.List;

public class MediaDataListAdapter extends RecyclerView.Adapter {

    private final List<MediaData> mList;

    MediaDataListAdapter(List<MediaData> mediaDataList) {
        this.mList = mediaDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemSingleTextViewBinding binding = ((ItemHolder) holder).getBinding();
        MediaData data = mList.get(position);

        String builder =
                "id : " + data.getId() + "\n" +
                        "albumId : " + data.getAlbumId() + "\n" +
                        "artistId : " + data.getArtistId() + "\n" +
                        "displayName : " + data.getDisplayName() + "\n" +
                        "title : " + data.getTitle() + "\n" +
                        "albumArtist : " + data.getAlbumArtist();

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
