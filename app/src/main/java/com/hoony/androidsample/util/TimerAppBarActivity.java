package com.hoony.androidsample.util;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.hoony.androidsample.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("Registered")
public abstract class TimerAppBarActivity extends AppCompatActivity {

    private Object binding;
    private AppBarTimerHandler appBarTimerHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(TimerAppBarActivity.this, getLayoutResource());
        initAppBar();
    }

    public abstract int getLayoutResource();

    private void initAppBar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar ab = getSupportActionBar();
            if (ab == null) return;

            ab.setDisplayUseLogoEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayShowTitleEnabled(false);

            appBarTimerHandler = new AppBarTimerHandler(TimerAppBarActivity.this);
            appBarTimerHandler.sendEmptyMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (appBarTimerHandler != null) {
            appBarTimerHandler.removeMessages(0);
            appBarTimerHandler = null;
        }
    }

    public Object getBinding() {
        return binding;
    }

    private static class AppBarTimerHandler extends Handler {

        private final WeakReference<TimerAppBarActivity> reference;

        AppBarTimerHandler(TimerAppBarActivity activity) {
            this.reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            TimerAppBarActivity activity = reference.get();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (E) a hh:mm", Locale.getDefault());
            String date = dateFormat.format(new Date());

            try {
                TextView textView = activity.findViewById(R.id.tv_app_bar_time);
                textView.setText(date);
            } catch (Exception e) {
                e.printStackTrace();
            }

            sendEmptyMessageDelayed(0, 1000);
        }
    }
}
