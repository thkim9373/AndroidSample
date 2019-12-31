package com.hoony.androidsample;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hoony.androidsample.databinding.ActivityVideoPlayerBinding;

public class VideoPlayerActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVideoPlayerBinding binding = DataBindingUtil.setContentView(VideoPlayerActivity.this, R.layout.activity_video_player);
    }
}
