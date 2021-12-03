package com.example.doanquanlykhachsan;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LapPhieuThue_Gio extends AppCompatActivity {

    private Spinner spnGioBatDau,spnGioKetThuc;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setControl();
    }

    private void setControl() {
        spnGioBatDau = findViewById(R.id.spnGioBatDau);
        spnGioKetThuc = findViewById(R.id.spnGioKetThuc);
    }
}
