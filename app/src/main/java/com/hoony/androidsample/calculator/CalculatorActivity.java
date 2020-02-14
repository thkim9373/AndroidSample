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
    private String numericExpression;

    private final String OPERATOR_ADDITION = " + ";
    private final String OPERATOR_SUBTRACTION = " - ";
    private final String OPERATOR_DIVISION = " * ";
    private final String OPERATOR_MULTPLICATION = " / ";

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

        binding.btAddition.setOnClickListener(CalculatorActivity.this);
//        binding.btSubtration.setOnClickListener(CalculatorActivity.this);
        binding.btMultiple.setOnClickListener(CalculatorActivity.this);
        binding.btEqual.setOnClickListener(CalculatorActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                numericExpression += "1";
                break;
            case R.id.bt_2:
                numericExpression += "2";
                break;
            case R.id.bt_3:
                numericExpression += "3";
                break;
            case R.id.bt_4:
                numericExpression += "4";
                break;
            case R.id.bt_5:
                numericExpression += "5";
                break;
            case R.id.bt_6:
                numericExpression += "6";
                break;
            case R.id.bt_7:
                numericExpression += "7";
                break;
            case R.id.bt_8:
                numericExpression += "8";
                break;
            case R.id.bt_9:
                numericExpression += "9";
                break;
        }
    }
}
