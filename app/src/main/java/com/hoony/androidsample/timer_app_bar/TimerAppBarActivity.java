package com.hoony.androidsample.timer_app_bar;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityTimerAppBarBinding;

public class TimerAppBarActivity extends com.hoony.androidsample.util.TimerAppBarActivity implements View.OnClickListener {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_timer_app_bar;
    }

    ActivityTimerAppBarBinding binding;
    TimerAppBarViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityTimerAppBarBinding) getBinding();
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TimerAppBarViewModel.class);

        setObserve();
        viewModel.setDate();
        setListener();
    }

    private void setObserve() {
        viewModel.getDateMutableLiveData().observe(TimerAppBarActivity.this, date -> binding.tvDate.setText(date));
    }

    private void setListener() {
        binding.ibLeftArrow.setOnClickListener(TimerAppBarActivity.this);
        binding.ibRightArrow.setOnClickListener(TimerAppBarActivity.this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_left_arrow) {
            viewModel.setYesterday();
        } else if (v.getId() == R.id.ib_right_arrow) {
            viewModel.setTomorrow();
        }
    }
}
