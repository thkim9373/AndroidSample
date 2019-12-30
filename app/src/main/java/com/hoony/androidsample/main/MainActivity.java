package com.hoony.androidsample.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hoony.androidsample.BottomSheetDialogActivity;
import com.hoony.androidsample.MusicPlayerActivity;
import com.hoony.androidsample.R;
import com.hoony.androidsample.VideoPlayerActivity;
import com.hoony.androidsample.content_provider.ContentProviderActivity;
import com.hoony.androidsample.databinding.ActivityMainBinding;
import com.hoony.androidsample.gallery.GalleryActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final Class[] targetClassArray = {BottomSheetDialogActivity.class,
            MusicPlayerActivity.class,
            VideoPlayerActivity.class,
            ContentProviderActivity.class,
            GalleryActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        String[] titleArray = getResources().getStringArray(R.array.main_item_array);

        List<Item> itemList = new ArrayList<>();
        for(int i = 0; i < titleArray.length; i++) {
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
