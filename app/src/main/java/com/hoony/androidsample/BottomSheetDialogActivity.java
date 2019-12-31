package com.hoony.androidsample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hoony.androidsample.databinding.ActivityBottomSheetDialogBinding;

public class BottomSheetDialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBottomSheetDialogBinding binding = DataBindingUtil.setContentView(BottomSheetDialogActivity.this, R.layout.activity_bottom_sheet_dialog);

        binding.btShowBottomSheet.setOnClickListener(BottomSheetDialogActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_show_bottom_sheet:

                break;
        }
    }
}
