package com.hoony.androidsample.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hoony.androidsample.BottomSheetDialogActivity;
import com.hoony.androidsample.R;
import com.hoony.androidsample.VideoPlayerActivity;
import com.hoony.androidsample.address_book.AddressBookListActivity;
import com.hoony.androidsample.databinding.ActivityMainBinding;
import com.hoony.androidsample.excel.ExcelActivity;
import com.hoony.androidsample.gallery.GalleryActivity;
import com.hoony.androidsample.album_data_list.AlbumDataListActivity;
import com.hoony.androidsample.media_data_list.MediaDataListActivity;
import com.hoony.androidsample.music_player.MusicPlayerActivity;
import com.hoony.androidsample.picture_loader.PictureLoaderActivity;
import com.hoony.androidsample.room.RoomActivity;
import com.hoony.androidsample.timer_app_bar.TimerAppBarActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final Class[] targetClassArray = {
            ExcelActivity.class,
            TimerAppBarActivity.class,
            PictureLoaderActivity.class,
            BottomSheetDialogActivity.class,
            AlbumDataListActivity.class,
            MediaDataListActivity.class,
            MusicPlayerActivity.class,
            VideoPlayerActivity.class,
            AddressBookListActivity.class,
            GalleryActivity.class,
            RoomActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        String[] titleArray = getResources().getStringArray(R.array.main_item_array);

        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < titleArray.length; i++) {
            itemList.add(new Item(titleArray[i], targetClassArray[i]));
        }
        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(itemList, MainActivity.this);

        binding.svMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.svMain.setAdapter(adapter);
        binding.svMain.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
    }

    private void goActivity(Class target) {
        Intent intent = new Intent(MainActivity.this, target);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Class targetClass = (Class) view.getTag();
        goActivity(targetClass);
    }
}
