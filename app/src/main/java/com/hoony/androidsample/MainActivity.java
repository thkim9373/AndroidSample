package com.hoony.androidsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hoony.androidsample.content_provider.ContentProviderActivity;
import com.hoony.androidsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.btGoBottomSheetDialog.setOnClickListener(MainActivity.this);
        binding.btGoMusicPlayer.setOnClickListener(MainActivity.this);
        binding.btGoVideoPlayer.setOnClickListener(MainActivity.this);
        binding.btGoContentProvider.setOnClickListener(MainActivity.this);
    }

    private void goActivity(Class target) {
        Intent intent = new Intent(MainActivity.this, target);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_go_bottom_sheet_dialog:
                goActivity(BottomSheetDialogActivity.class);
                break;
            case R.id.bt_go_music_player:
                goActivity(MusicPlayerActivity.class);
                break;
            case R.id.bt_go_video_player:
                goActivity(VideoPlayerActivity.class);
                break;
            case R.id.bt_go_content_provider:
                goActivity(ContentProviderActivity.class);
                break;
        }
    }
}
