package com.hoony.androidsample.address_book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ItemSingleTextViewBinding;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter {

    private final List<AddressBookData> mList;

    AddressListAdapter(List<AddressBookData> addressBookDataList) {
        this.mList = addressBookDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemSingleTextViewBinding binding = ((ItemHolder) holder).getBinding();

        AddressBookData data = mList.get(position);

        String contents =
                "contactLastUpdatedTimestamp : " + data.getContactLastUpdatedTimestamp() + "\n" +
                        "displayName : " + data.getDisplayName() + "\n" +
                        "number : " + data.getNumber() + "\n" +
                        "hasPhoneNumber : " + data.getHasPhoneNumber() + "\n" +
                        "inDefaultDirectory : " + data.getInDefaultDirectory() + "\n" +
                        "inVisibleGroup : " + data.getInVisibleGroup() + "\n" +
                        "lookupKey : " + data.getLookupKey() + "\n" +
                        "nameRawContactId : " + data.getNameRawContactId() + "\n" +
                        "photoFileId : " + data.getPhotoFileId() + "\n" +
                        "photoId : " + data.getPhotoId() + "\n" +
                        "photoThumbnailUri : " + data.getPhotoThumbnailUri() + "\n" +
                        "photoUri : " + data.getPhotoUri();

        binding.tvContent.setText(contents);
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

        public ItemSingleTextViewBinding getBinding() {
            return binding;
        }
    }
}
