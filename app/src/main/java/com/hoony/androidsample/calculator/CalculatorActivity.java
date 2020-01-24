package com.hoony.androidsample.calculator;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hoony.androidsample.R;
import com.hoony.androidsample.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityCalculatorBinding binding;
    private String a, b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(CalculatorActivity.this, R.layout.activity_calculator);
        
        setListener();
    }

    private void setListener() {
        binding.bt1.setOnClickListener(CalculatorActivity.this);
        binding.bt2.setOnClickListener(CalculatorActivity.this);
        binding.bt3.setOnClickListener(CalculatorActivity.this);
        binding.bt4.setOnClickListener(CalculatorActivity.this);
        binding.bt5.setOnClickListener(CalculatorActivity.this);
        binding.bt6.setOnClickListener(CalculatorActivity.this);
        binding.bt7.setOnClickListener(CalculatorActivity.this);
        binding.bt8.setOnClickListener(CalculatorActivity.this);
        binding.bt9.setOnClickListener(CalculatorActivity.this);

        binding.btPlus.setOnClickListener(CalculatorActivity.this);
        binding.btMinus.setOnClickListener(CalculatorActivity.this);
        binding.btMultiple.setOnClickListener(CalculatorActivity.this);
    }

    @Override
    public void onClick(View v) {

    }
}
